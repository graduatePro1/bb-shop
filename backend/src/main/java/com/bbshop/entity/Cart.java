package com.bbshop.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 购物车信息表
*/
@Data
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    private Integer userId;
    private Integer shopId;
    private Integer goodsId;
    private Integer num;

    private String shopName;
    private String goodsName;
    private String goodsImg;
    private String goodUnit;
    private Double goodsPrice;
}