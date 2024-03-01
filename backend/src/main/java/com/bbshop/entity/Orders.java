package com.bbshop.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 订单信息表
*/
@Data
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    private Integer userId;
    private Integer shopId;
    private Integer goodsId;
    private String orderId;
    private Integer addressId;
    private Integer num;
    private Double price;
    private String status;

    private List<Cart> cartData;


    private String shopName;
    private String goodsName;
    private String goodsImg;
    private String goodsUnit;
    private Double goodsPrice;
    private String username;
    private String useraddress;
    private String phone;

}