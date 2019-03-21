package com.liao.wxshop.converter;

import com.google.common.base.Converter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liao.wxshop.dataobject.OrderDetail;
import com.liao.wxshop.dto.OrderDTO;
import com.liao.wxshop.enums.ResultEnum;
import com.liao.wxshop.exception.OrderException;
import com.liao.wxshop.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO..
 *
 * @author liao
 * @date 2019/3/15
 */
@Slf4j
public class OrderForm2OrderDTOConverter extends Converter<OrderForm, OrderDTO> {
    @Override
    protected OrderDTO doForward(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setCityCode(orderForm.getCityCode());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        Gson gson = new Gson();
        List<OrderDetail> orderDetailList;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误,String={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    protected OrderForm doBackward(OrderDTO orderDTO) {
        return null;
    }
}
