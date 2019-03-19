package com.liao.wxshop.service;

import com.liao.wxshop.dataobject.OrderMaster;
import com.liao.wxshop.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 订单服务..
 *
 * @author liao
 * @date 2019/2/28
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单.
     */
    OrderDTO findOne(String orderId);

    /**
     * 查询订单列表
     */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单.
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完结订单.
     */
    OrderDTO finished(OrderDTO orderDTO);

    /**
     * 支付订单.
     */
    OrderDTO paied(OrderDTO orderDTO);
}
