package com.liao.wxshop.repository;

import com.liao.wxshop.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品分类 Repository
 *
 * @author liao
 * @date 2019/2/19
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>, JpaSpecificationExecutor<ProductCategory> {

    /**
     *
     * @param categoryTypeList 分类id集合
     * @return 商品分类列表
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
