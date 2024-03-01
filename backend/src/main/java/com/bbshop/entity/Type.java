package com.bbshop.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品分类信息表
*/
@Data
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 分类名称 */
    private String name;
    /** 分类描述 */
    private String description;
    /** 分类图标 */
    private String img;
}