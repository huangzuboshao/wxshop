package com.liao.wxshop.service.impl;

import com.liao.wxshop.dto.OrderDTO;
import com.liao.wxshop.enums.ResultEnum;
import com.liao.wxshop.exception.OrderException;
import com.liao.wxshop.service.BuyerService;
import com.liao.wxshop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liao
 * @date 2019/3/19
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancel(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】 未查到该订单,orderId={}", orderId);
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        //判断是否是自己的订单
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】 订单的openid不一致,orderId={},orderDTO={}", openid, orderDTO);
            throw new OrderException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
