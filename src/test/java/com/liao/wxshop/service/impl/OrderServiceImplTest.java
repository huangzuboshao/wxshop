package com.liao.wxshop.service.impl;

import com.liao.wxshop.dataobject.OrderDetail;
import com.liao.wxshop.dto.OrderDTO;
import com.liao.wxshop.enums.OrderStatusEnum;
import com.liao.wxshop.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * TODO..
 *
 * @author liao
 * @date 2019/3/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {


    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "99999999999";
    private final String ORDER_ID = "1552280345485722679";

    @Test
    public void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("房山");
        orderDTO.setBuyerName("liaoliaoliao");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setBuyerPhone("15855555555");

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(2);
        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(50);

        orderDetailList.add(o1);
        orderDetailList.add(o2);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单....result = {}】", result);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        log.info("orderDTO={}", orderDTO);

    }

    @Test
    public void findList() {
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, new PageRequest(0, 10));
        System.out.println(orderDTOPage);
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        orderService.cancel(orderDTO);
    }

    @Test
    public void finished() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.finished(orderDTO);
        Assert.assertEquals(result.getOrderStatus(), OrderStatusEnum.FINISHED.getCode());
    }

    @Test
    public void paied() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.paied(orderDTO);
        Assert.assertEquals(result.getPayStatus(), PayStatusEnum.SUCCESS.getCode());
    }
}