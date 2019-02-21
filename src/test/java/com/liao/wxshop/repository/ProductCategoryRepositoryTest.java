package com.liao.wxshop.repository;


import com.liao.wxshop.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * TODO..
 *
 * @author liao
 * @date 2019/2/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest() {
        ProductCategory category = repository.findOne(1);
        System.out.println(category);
    }

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("粤菜", 2);
        ProductCategory cate = repository.save(productCategory);
        Assert.assertNotEquals(null, cate);
    }

    @Test
    public void findBy() {
        List<Integer> list = Arrays.asList(2, 3);
        List<ProductCategory> productCategoryList = repository.findByCategoryTypeIn(list);
        System.out.println(productCategoryList);
    }
}