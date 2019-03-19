package com.liao.wxshop.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Validator验证器
 *
 * @author liao
 * @date 2019/3/14
 */
@Configuration
public class ValidatorConfiguration {

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                /* .failFast(false)*/
                //false 普通模式(会校验完所有的属性，然后返回所有的验证失败信息)
                //true 快速失败返回模式(只要有一个验证失败，则返回)
                .addProperty("hibernate.validator.fail_fast", "false")
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        postProcessor.setValidator(validator());
        return postProcessor;
    }
}
