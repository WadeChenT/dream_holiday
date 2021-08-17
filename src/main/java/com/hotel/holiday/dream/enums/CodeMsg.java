package com.hotel.holiday.dream.enums;

import lombok.Getter;

@Getter
public enum CodeMsg {
    COMMON_OK("1", "success"),
    COMMON_FAIL("0", "fail");

    private String code;
    private String msg;

    CodeMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
