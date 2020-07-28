package com.zhanghf;

import com.zhanghf.po.ClaimRecord;
import com.zhanghf.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhanghf
 * @version 1.0
 * @date 10:44 2020/7/20
 */
@Slf4j
public class BytesStudy {

    public static void main(String[] args) {
        ClaimRecord claimRecord = new ClaimRecord();
        claimRecord.setClaimAmount("30");
        claimRecord.setEInvoiceId("123");
        byte[] bytes = CommonUtils.convertBytes("", claimRecord);
        log.info("bytes={}", bytes);
    }
}
