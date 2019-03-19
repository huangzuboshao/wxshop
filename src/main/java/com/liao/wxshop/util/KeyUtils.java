package com.liao.wxshop.util;

import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * Key生成器
 *
 * @author liao
 * @date 2019/3/1
 */
public class KeyUtils {

    /**
     * 城市编码长度
     */
    public static int CITY_CODE_LENGTH = 6;

    /**
     * 生成唯一主键
     * 格式: 时间戳 + 6位随机值
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer randomNumber = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(randomNumber);
    }

    /**
     * 生成唯一主键
     * 格式: 区县的6位编码 + 时间戳 + 6位随机值
     */
    public static synchronized String genUniqueKeyByPosition(String cityCode) {
        if (StringUtils.isEmpty(cityCode) || cityCode.length() > CITY_CODE_LENGTH) {
            cityCode = "000000";
        }
        Random random = new Random();
        Integer randomNumber = random.nextInt(900000) + 100000;
        return cityCode + System.currentTimeMillis() + String.valueOf(randomNumber);
    }
}
