package com.liao.wxshop.repository;

import com.liao.wxshop.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品
 *
 * @author liao
 * @date 2019/2/20
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    /**
     * 根据商品状态来查商品
     * @param productStatus 商品状态
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

}
