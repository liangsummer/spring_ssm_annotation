package com.hml.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Description: spring-mvc替代类
 * @ClassName: SpringMvcConfig
 * @Author: 25507
 * @Date: 2022-08-14 11:05
 * @Version: 1.0
 */
@Configuration

//<!--    开启包扫描
//        别忘记让spring排除加载mvc注解
//        -->
//<context:component-scan base-package="com.hml.controller"/>
@ComponentScan("com.hml.controller")

//<!--    开启注解-->
//<mvc:annotation-driven/>  但是有所差别
@EnableWebMvc
public class SpringMvcConfig {
}




