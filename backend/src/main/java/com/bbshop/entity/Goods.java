package com.bbshop.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品信息表
*/
@Data
public class Goods implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 商品名称 */
    private String name;
    /** 商品描述 */
    private String description;
    /** 商品主图 */
    private String img;
    private Double price;
    private String unit;
    private Integer count;
    private Integer typeId;
    private Integer shopId;

    private String typeName;
    private String shopName;

}