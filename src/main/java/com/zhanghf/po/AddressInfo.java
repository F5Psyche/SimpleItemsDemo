package com.zhanghf.po;

import lombok.Data;

/**
 * @author zhanghf
 * @version 1.0
 * @date 21:20 2020/3/6
 */
@Data
public class AddressInfo {

    private Long id;

    private String localInnerCode;

    private String address;

    private String addressKind;

    private String acceptTimedesc;

    private String phone;

    private String uuid;

    private String isInner;
}