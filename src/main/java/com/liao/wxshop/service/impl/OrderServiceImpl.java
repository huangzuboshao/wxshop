package com.liao.wxshop.service.impl;

import com.liao.wxshop.converter.A2BConverter;
import com.liao.wxshop.dataobject.OrderDetail;
import com.liao.wxshop.dataobject.OrderMaster;
import com.liao.wxshop.dataobject.ProductInfo;
import com.liao.wxshop.dto.CartDTO;
import com.liao.wxshop.dto.OrderDTO;
import com.liao.wxshop.enums.OrderStatusEnum;
import com.liao.wxshop.enums.PayStatusEnum;
import com.liao.wxshop.enums.ProductStatusEnum;
import com.liao.wxshop.enums.ResultEnum;
import com.liao.wxshop.exception.OrderException;
import com.liao.wxshop.exception.ProductInfoException;
import com.liao.wxshop.repository.OrderDetailRepository;
import com.liao.wxshop.repository.OrderMasterRepository;
import com.liao.wxshop.service.OrderService;
import com.liao.wxshop.service.ProductService;
import com.liao.wxshop.util.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ..
 *
 * @author liao
 * @date 2019/3/1
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtils.genUniqueKeyByPosition(orderDTO.getCityCode());
        //订单总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        //1.查询商品(数量.价格)
        for (OrderDetail orderDetail : orderDetailList) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new ProductInfoException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            if (productInfo.getProductStatus().equals(ProductStatusEnum.DOWN.getCode())) {
                log.error("【创建订单】 商品已下架");
                throw new ProductInfoException(ResultEnum.PRODUCT_ALREADY_DOWN);
            }
            //2.计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtils.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }

        //3.入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        //买家的信息
        BeanUtils.copyProperties(orderDTO, orderMaster);
        //重赋值
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setCityCode("000000");

        orderMasterRepository.save(orderMaster);

        //减库存
        List<CartDTO> cartDTOList = orderDetailList.stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null) {
            log.error("【查询单个订单】 orderId ={}", orderId);
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = new A2BConverter().convert(orderMasterPage.getContent());
        return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    @Transactional
    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        //1.查询订单状态
        OrderMaster orderMaster = new OrderMaster();
        if (!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())) {
            log.error("【取消订单】 订单状态不正确,orderId={},orderStatus={}", orderMaster.getOrderId(), orderMaster.getOrderStatus());
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改订单为已取消
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【取消订单】 更新失败,orderMaster={}", orderMaster);
            throw new OrderException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //3.撤回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】 订单无商品详情,orderDTO={}", orderDTO);
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        //4.若已支付,完成退款
        if (PayStatusEnum.SUCCESS.getCode().equals(orderMaster.getPayStatus())) {
            //TODO 完成退款
        }
        return orderDTO;
    }

    @Transactional
    @Override
    public OrderDTO finished(OrderDTO orderDTO) {
        //1.查询订单状态
        if (!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())) {
            log.error("【完结订单】 订单状态不正确，orderStatus={},orderId={}", orderDTO.getOrderStatus(), orderDTO.getOrderId());
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改订单为已完结
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】 更新失败，orderMaster={}", orderMaster);
            throw new OrderException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;

    }

    @Transactional
    @Override
    public OrderDTO paied(OrderDTO orderDTO) {
        //判断订单状态
        if (!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())) {
            log.error("【订单支付成功】 订单状态不正确，orderStatus={},orderId={}", orderDTO.getOrderStatus(), orderDTO.getOrderId());
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付成功】 支付状态不正确，orderDTO={}", orderDTO);
            throw new OrderException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【订单支付成功】 更新失败，OrderMaster={}", orderMaster);
            throw new OrderException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
