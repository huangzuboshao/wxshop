package com.liao.wxshop.repository;

import com.liao.wxshop.config.CustomizedSaveImplp;
import com.liao.wxshop.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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
@Slf4j
@ComponentScan(basePackages = {"com.liao.wxshop.config.CustomizedSaveImplp"})
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("111111");
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
        List<ProductInfo> productInfoList = repository.findByProductStatus(0);
        System.out.println(productInfoList);
    }

    @Test
    public void findByProductIdIn() {
        List<String> productIds = new ArrayList<>();
        productIds.add("123456");
        productIds.add("123457");

        List<ProductInfo> productInfoList = repository.findByProductIdIn(productIds);
        log.info("【列表:】{}",productInfoList);
        Assert.assertNotNull(productInfoList);
    }
}