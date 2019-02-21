package com.liao.wxshop.util;

import com.liao.wxshop.VO.ResultBean;

/**
 * TODO..
 *
 * @author liao
 * @date 2019/2/20
 */
public class ResultVOUtils {
    public static ResultBean success(Object obj) {
        ResultBean resultBean = new ResultBean();
        resultBean.setData(obj);
        return resultBean;
    }

    public static ResultBean success() {
        ResultBean resultBean = new ResultBean();
        resultBean.setData(null);
        return resultBean;
    }

}