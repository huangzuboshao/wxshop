package com.liao.wxshop.dataobject;

import com.liao.wxshop.enums.OrderStatusEnum;
import com.liao.wxshop.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 *
 * @author liao
 * @date 2019/2/28
 */
@Entity
@Data
@DynamicUpdate
@Table(name = "order_master")
public class OrderMaster {

    /**
     * 订单id.
     */
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /**
     * 支付状态,默认为0未支付.
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    /**
     * 创建时间.
     */
    private Date createTime;
    /**
     * 修改时间.
     */
    private Date updateTime;
}
