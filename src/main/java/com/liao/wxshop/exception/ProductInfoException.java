package com.liao.wxshop.exception;

import com.liao.wxshop.enums.ResultEnum;
import lombok.Getter;

/**
 * 商品异常
 *
 * @author liao
 * @date 2019/3/1
 */
@Getter
public class ProductInfoException extends RuntimeException {

    private Integer code;

    public ProductInfoException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public ProductInfoException() {
    }
}
