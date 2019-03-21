package com.liao.wxshop.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.liao.wxshop.dataobject.OrderDetail;
import com.liao.wxshop.util.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单DTO..
 *
 * @author liao
 * @date 2019/2/28
 */
@Data
//@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OrderDTO {

    /**
     * 订单id.
     */
    private String orderId;
    /**
     * 买家姓名.
     */
    private String buyerName;
    /**
     * 买家电话.
     */
    private String buyerPhone;

    /**
     * 城市编号.
     */
    private String cityCode;
    /**
     * 买家地址.
     */
    private String buyerAddress;
    /**
     * 买家openid.
     */
    private String buyerOpenid;
    /**
     * 订单数量.
     */
    private BigDecimal orderAmount;
    /**
     * 订单状态,默认为0,新订单.
     */
    private Integer orderStatus;
    /**
     * 支付状态,默认为0未支付.
     */
    private Integer payStatus;
    /**
     * 创建时间.
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    /**
     * 修改时间.
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
