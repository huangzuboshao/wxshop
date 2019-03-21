package com.liao.wxshop.controller;

import com.liao.wxshop.util.ResultVOUtils;
import com.liao.wxshop.vo.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * TODO..
 *
 * @author liao
 * @date 2019/3/19
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {


    @Autowired
    private RestTemplate restTemplate;

    public static String APP_ID = "wxce0f7960881a2cac";
    public static String APP_SECRET = "fa27386e007ec04e023a619e41cea46e";

    @GetMapping("/auth")
    public ResultBean auth(@RequestParam("code") String code) {
        log.info("code={}", code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APP_ID + "&secret=" + APP_SECRET + "&code=" + code + "&grant_type=authorization_code";
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);

        String url2 = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + APP_ID + "&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
        restTemplate.getForObject(url2,String.class);
        return ResultVOUtils.success("auth方法");
    }
}
