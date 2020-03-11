package com.zhanghf.po;

import lombok.Data;

@Data
public class AdressInfo {
    private Long id;

    private String localInnerCode;

    private String address;

    private String addressKind;

    private String acceptTimedesc;

    private String phone;

    private String uuid;

    private String isInner;
}