package com.bbshop.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户
*/
@Data
public class User extends Account implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 昵称 */
    private String name;
    /** 电话 */
    private String phone;
    /** 邮箱 */
    private String email;
    /** 头像 */
    private String photo;
    /** 角色标识 */
    private String role;

}