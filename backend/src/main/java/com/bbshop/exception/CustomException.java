package com.bbshop.exception;


import com.bbshop.common.constant.CodeEnum;

//自定义异常：封装为状态+异常信息
public class CustomException extends RuntimeException {
    private String code;
    private String message;

    public CustomException(CodeEnum codeEnum) {
        this.code = codeEnum.code;
        this.message = codeEnum.message;
    }

    public CustomException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
