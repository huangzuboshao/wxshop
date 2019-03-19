package com.liao.wxshop.converter;

import com.google.common.base.Converter;
import com.liao.wxshop.dataobject.OrderMaster;
import com.liao.wxshop.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A->B或者B->A
 *
 * @author liao
 * @date 2019/3/11
 */
public class A2BConverter extends Converter<OrderMaster, OrderDTO> {

    @Override
    public OrderDTO doForward(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    @Override
    protected OrderMaster doBackward(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        return orderMaster;
    }

    public List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e -> doForward(e)).collect(Collectors.toList());
    }
}
