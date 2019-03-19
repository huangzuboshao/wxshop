package com.liao.wxshop.repository;

import com.liao.wxshop.config.CustomizedSave;
import com.liao.wxshop.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品
 *
 * @author liao
 * @date 2019/2/20
 */
@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String>,CustomizedSave<ProductInfo> {

    /**
     * 根据商品状态来查商品
     *
     * @param productStatus 商品状态
     * @return 商品列表
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

    /**
     * 根据商品id查商品列表
     * @param productIds 商品id集合
     * @return 商品列表
     */
    @Query("select p  from ProductInfo as p where p.productId in (:productIds)")
    List<ProductInfo> findByProductIdIn(@Param("productIds")List<String> productIds);


}
