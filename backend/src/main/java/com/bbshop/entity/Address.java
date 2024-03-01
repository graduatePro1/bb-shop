package com.bbshop.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 地址信息表
*/
@Data
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    private Integer userId;
    private String username;
    private String useraddress;
    private String phone;

}