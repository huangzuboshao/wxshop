package com.liao.wxshop.service;

import com.liao.wxshop.dataobject.ProductInfo;
import com.liao.wxshop.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品服务
 *
 * @author liao
 * @date 2019/2/20
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    List<ProductInfo> findProductInfoListByIds(List<String> productIds);

    /**
     * 查询所有在架的商品
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减库存
     */
    void decreaseStock(List<CartDTO> cartDTOList);
}
