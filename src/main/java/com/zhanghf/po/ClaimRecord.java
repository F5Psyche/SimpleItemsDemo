package com.zhanghf.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhanghf
 * @version 1.0
 * @date 16:21 2020/3/23
 */
@Data
public class ClaimRecord implements Serializable {
    private String eInvoiceId;
    private String claimAmount;
}
