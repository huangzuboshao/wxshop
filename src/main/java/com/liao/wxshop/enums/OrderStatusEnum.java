package com.liao.wxshop.enums;

import lombok.Getter;

/**
 * 订单状态.
 *
 * @author liao
 * @date 2019/2/28
 */
@Getter
public enum OrderStatusEnum {
    /**
     * 新订单.
     */
    NEW(0, "新订单"),
    /**
     * 订单完结.
     */
    FINISHED(1, "完结订单"),
    /**
     * 订单取消.
     */
    CANCEL(2, "订单取消"),;
    private Integer code;
    private String messge;

    OrderStatusEnum(Integer code, String messge) {
        this.code = code;
        this.messge = messge;
    }
}
