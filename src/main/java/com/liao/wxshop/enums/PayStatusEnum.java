package com.liao.wxshop.enums;

import lombok.Getter;

/**
 * 支付状态.
 *
 * @author liao
 * @date 2019/2/28
 */
@Getter
public enum PayStatusEnum {
    /**
     * 未支付
     */
    WAIT(0, "等待支付"),
    /**
     * 支付成功
     */
    SUCCESS(1, "支付成功"),;
    private Integer code;
    private String messge;

    PayStatusEnum(Integer code, String messge) {
        this.code = code;
        this.messge = messge;
    }
}
