package com.bbshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements  WebMvcConfigurer{

    @Resource
    private JwtInterceptor jwtInterceptor;

    // 加自定义拦截器JwtInterceptor，设置拦截规则
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/login","/register","/sendCode","/compareCode","/modifyPassword","/forgetPassword")
                .excludePathPatterns("/files/**")
                .excludePathPatterns("/type/**")
                .excludePathPatterns("/notice/selectAll")
                .excludePathPatterns("/goods/**")
                .excludePathPatterns("/goods/**")
                .excludePathPatterns("/comment/selectByGoodsId/**")
        ;
    }
}