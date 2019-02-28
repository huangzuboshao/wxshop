package com.liao.wxshop.enums;

import lombok.Getter;

/**
 * 商品状态.
 *
 * @author liao
 * @date 2019/2/20
 */
@Getter
public enum ProductStatusEnum {
    /**
     * 在架.
     */
    UP(0, "在架"),
    /**
     * 下架.
     */
    DOWN(1, "下架");

    private Integer code;
    private String msg;

    ProductStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
