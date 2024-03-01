package com.bbshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CrosConfig {
//    private CorsConfiguration corsBuild(){
//        CorsConfiguration cors=new CorsConfiguration();
//        //设置能进行跨域请求的源地址
//        cors.addAllowedOrigin("*");
//        //设置能进行跨域请求的携带请求头参数
//        cors.addAllowedHeader("*");
//        //设置能进行跨域请求的方法
//        cors.addAllowedMethod("*");
//        //60分钟的跨域请求时间
//        cors.setMaxAge(3600L);
//        //4 是否允许用户发送、处理 cookie
//        cors.setAllowCredentials(true);
//        return cors;
//    }

//    注册为bean
    @Bean
    public CorsFilter corsFilter(){

        UrlBasedCorsConfigurationSource  source=new UrlBasedCorsConfigurationSource ();
        CorsConfiguration cors=new CorsConfiguration();
        //设置能进行跨域请求的源地址
        cors.addAllowedOriginPattern("*");
        //设置能进行跨域请求的携带请求头参数
        cors.addAllowedHeader("*");
        //设置能进行跨域请求的方法
        cors.addAllowedMethod("*");
        //4 是否允许用户发送、处理 cookie
        cors.setAllowCredentials(true);
        source.registerCorsConfiguration("/**",cors);
        return new CorsFilter(source);
    }
}
