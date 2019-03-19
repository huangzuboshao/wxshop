package com.liao.wxshop.service;

import com.liao.wxshop.dto.OrderDTO;

/**
 * 买家
 *
 * @author liao
 * @date 2019/3/19
 */
public interface BuyerService {
    /**
     * 查询一个订单
     */
    OrderDTO findOrderOne(String openid, String orderId);

    /**
     * 取消订单
     */
    OrderDTO cancel(String openid, String orderId);
}
