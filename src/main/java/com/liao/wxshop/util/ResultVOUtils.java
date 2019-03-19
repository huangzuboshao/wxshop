package com.liao.wxshop.util;

import com.liao.wxshop.vo.ResultBean;

/**
 * TODO..
 *
 * @author liao
 * @date 2019/2/20
 */
public class ResultVOUtils {

    @SuppressWarnings("unchecked")
    public static ResultBean success(Object obj) {
        ResultBean resultBean = new ResultBean();
        resultBean.setData(obj);
        return resultBean;
    }

    @SuppressWarnings("unchecked")
    public static ResultBean success() {
        ResultBean resultBean = new ResultBean();
        resultBean.setData(null);
        return resultBean;
    }

}