package com.liao.wxshop.repository;

import com.liao.wxshop.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 商品分类 Repository
 *
 * @author liao
 * @date 2019/2/19
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>, JpaSpecificationExecutor<ProductCategory> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
