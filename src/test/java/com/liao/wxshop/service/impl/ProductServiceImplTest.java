package com.liao.wxshop.service.impl;

import com.liao.wxshop.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * TODO..
 *
 * @author liao
 * @date 2019/2/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("123456");
        System.out.println(productInfo);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        System.out.println(productInfoList);

    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0,10);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        System.out.println(productInfoPage);
    }

    @Test
    public void save() {
    }
}