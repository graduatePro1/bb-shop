package com.bbshop.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 收藏信息表
*/
@Data
public class Collect implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    private Integer userId;
    private Integer shopId;
    private Integer goodsId;

    private String shopName;
    private String goodsName;
    private String goodsImg;
    private String goodUnit;
    private Double goodsPrice;

}