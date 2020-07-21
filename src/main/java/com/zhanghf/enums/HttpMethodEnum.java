package com.zhanghf.enums;

import lombok.Getter;

/**
 * @author zhanghf
 * @version 1.0
 * @date 13:58 2020/6/15
 */
@Getter
public enum HttpMethodEnum {
    GET("GET"),
    POST("POST");

    String code;

    HttpMethodEnum(String code) {
        this.code = code;
    }


}
