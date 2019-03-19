package com.liao.wxshop.exception;

import com.liao.wxshop.enums.ResultEnum;
import lombok.Getter;

/**
 * 订单异常
 *
 * @author liao
 * @date 2019/3/11
 */
@Getter
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException() {
    }
}
