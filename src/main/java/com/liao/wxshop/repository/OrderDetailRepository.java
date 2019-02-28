package com.liao.wxshop.repository;

import com.liao.wxshop.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单详情..
 *
 * @author liao
 * @date 2019/2/28
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * 通过订单id查订单详情
     *
     * @param orderId 订单id
     * @return 订单详情
     */
    List<OrderDetail> findByOrderId(String orderId);
}
