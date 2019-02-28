package com.liao.wxshop.repository;

import com.liao.wxshop.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 订单..
 *
 * @author liao
 * @date 2019/2/28
 */
@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * 按照买家openid查询
     *
     * @param buyerOpenid 买家openid
     * @param pageable    分页
     * @return 多个订单,分页
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
