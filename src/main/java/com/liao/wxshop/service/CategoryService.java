package com.liao.wxshop.service;

import com.liao.wxshop.dataobject.ProductCategory;

import java.util.List;

/**
 * 类目服务
 *
 * @author liao
 * @date 2019/2/20
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
