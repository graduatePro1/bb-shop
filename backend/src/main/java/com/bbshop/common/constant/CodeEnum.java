package com.bbshop.common.constant;

public enum CodeEnum {

    SUCCESS("200", "成功"),

    PARAM_ERROR("400", "参数异常"),
    TOKEN_INVALID_ERROR("401", "无效的token"),
    TOKEN_CHECK_ERROR("401", "token验证失败，请重新登录"),
    PARAM_LOST_ERROR("4001", "参数缺失"),

    SYSTEM_ERROR("500", "系统异常"),
    USER_EXIST_ERROR("5001", "用户已存在"),
    EMAIL_EXIST_ERROR("5001","邮箱已存在" ),
    USER_NOT_LOGIN("5002", "用户未登录"),
    USER_ACCOUNT_ERROR("5003", "账号或密码错误"),
    USER_ROLE_ERROR("5004", "身份信息有误"),

    USER_NOT_EXIST_ERROR("5005", "用户不存在"),
    PARAM_PASSWORD_ERROR("5006", "原密码输入错误"),
    COLLECT_ALREADY_ERROR("5007", "您已收藏过该商品，请勿重复收藏"),
    PARAM_CODE_ERROR("5008", "验证码有误"),
    CODE_EXIST_ERROR("5008", "请先获取验证码"),
    CODE_BUSY_ERROR("5008", "请求过于频繁，请稍后重试"),
   ;

    public String code;
    public String message;


    CodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
