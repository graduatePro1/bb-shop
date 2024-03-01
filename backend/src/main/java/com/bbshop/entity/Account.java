package com.bbshop.entity;

import lombok.Data;
import lombok.Getter;

/**
 * 角色用户父类
 */
@Data
public class Account {
    private Integer id;
    /** 用户名 */
    private String username;
    /** 邮箱 */
    private String email;
    //    邮箱状态

    private boolean emailState;
    /** 验证码 */
    private String code;
    //手机号
    private String phone;
    /** 名称 */
    private String name;
    /** 密码 */
    private String password;
    /** 角色标识 */
    private String role;
    /** 新密码 */
    private String newPassword;
    /** 头像 */
    private String photo;

    private String token;

}
