package com.liao.wxshop.service;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO..
 *
 * @author liao
 * @date 2019/2/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {


    @Test
    public  void test1(){
        String name = "123456";
        log.debug("test1 debug {}",name);
        log.info("test1{}",name);
    }

}