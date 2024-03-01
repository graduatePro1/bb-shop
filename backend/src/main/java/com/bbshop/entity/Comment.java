package com.bbshop.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 评论信息表
*/
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    private Integer userId;
    private Integer shopId;
    private Integer goodsId;

    private String shopName;
    private String goodsName;
    private String userPhoto;
    private String userName;

    private String time;
    private String content;
}