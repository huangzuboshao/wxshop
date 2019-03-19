package com.liao.wxshop.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 未登录
     */
    public static final int NO_LOGIN = -1;
    /**
     * 成功
     */
    public static final int SUCCESS = 0;
    /**
     * 失败
     */
    public static final int FAIL = 1;
    /**
     * 没权限
     */
    public static final int NO_PERMISSION = 2;

    public int code = SUCCESS;

    public String msg = "success";

    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.code = FAIL;
        this.msg = e.toString();
    }
}