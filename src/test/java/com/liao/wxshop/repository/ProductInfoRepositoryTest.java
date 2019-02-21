package com.liao.wxshop.repository;

import com.liao.wxshop.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * TODO..
 *
 * @author liao
 * @date 2019/2/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setCategoryType(1);
        productInfo.setProductName("麻婆豆腐");
        productInfo.setProductDescription("很有名的一道川菜,据说是麻婆的豆腐");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductPrice(new BigDecimal(18.0));
        productInfo.setProductStatus(0);
        productInfo.setProductStock(100);
        repository.save(productInfo);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList =  repository.findByProductStatus(0);
        System.out.println(productInfoList);
    }
}