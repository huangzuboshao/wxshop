package com.liao.wxshop.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 购物车..
 *
 * @author liao
 * @date 2019/3/1
 */
@Data
public class CartDTO {

    /**
     * 商品id.
     */
    @NotNull
    private String productId;

    /**
     * 商品数量.
     */
    @NotNull
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
