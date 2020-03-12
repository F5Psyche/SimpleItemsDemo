package com.zhanghf.enums;

/**
 * @author zhanghf
 * @version 1.0
 * @date 17:50 2020/3/12
 */
public enum HTTPCodeEnum {

    /**
     * 请求已成功
     */
    OK(200),
    ;

    private int code;

    HTTPCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
