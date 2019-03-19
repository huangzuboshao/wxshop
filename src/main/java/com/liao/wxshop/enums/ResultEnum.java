package com.liao.wxshop.enums;


import lombok.Getter;

/**
 * 错误码
 *
 * @author liao
 * @date 2019/3/1
 */
@Getter
public enum ResultEnum {

    /**
     * 商品不存在
     */
    PRODUCT_NOT_EXIST(10001, "商品不存在"),
    PRODUCT_STOCK_ERROR(10002, "商品库存不足"),
    PRODUCT_ALREADY_DOWN(10003, "商品已经下架"),

    ORDER_NOT_EXIST(20001, "订单不存在"),
    ORDER_DETAIL_NOT_EXIST(20002, "订单详情不存在"),
    ORDER_STATUS_ERROR(20003, "订单状态不正确"),
    ORDER_UPDATE_FAIL(20004, "订单更新失败 "),

    ORDER_PAY_STATUS_ERROR(20005, "订单支付状态不正确"),

    PARAM_ERROR(30005, "参数不正确"),
    CART_IS_EMPTY(30006, "购物车为空"),;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
