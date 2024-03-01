package com.bbshop.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.bbshop.common.ResultBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = "com.bbshop.controller") //监听controller层的所有异常
public class GlobalException {

    //异常的日志对象
    private static final Log log=LogFactory.get();//日志对象

    //统一异常处理@ExceptionHandler,主要用于Exception
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回json串
    public ResultBean error(HttpServletRequest request, Exception e){
        log.error("异常信息：",e);
        return ResultBean.error();
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody//返回json串
    public ResultBean error(HttpServletRequest request, CustomException e){
        log.error("异常信息：",e);
        return ResultBean.error(e.getCode(),e.getMessage());
    }

}
