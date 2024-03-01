package com.bbshop.common.constant;

//状态类型的枚举类
public enum StatusEnum {
    CHECKING("审核中"),
    CHECK_OK("通过"),
    CHECK_NO("不通过");

    public String status;

    StatusEnum(String status) {
        this.status = status;
    }
}
