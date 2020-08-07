package com.zhanghf;

import com.zhanghf.po.ClaimRecord;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author zhanghf
 * @version 1.0
 * @date 10:44 2020/7/20
 */
@Slf4j
public class BytesStudy {

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        ResultVo<ClaimRecord> resultVo = new ResultVo<>();
        log.info("resultVo={}", getExceptionResult(uuid));
        resultVo = getExceptionResult(uuid);
    }


    public static <T> ResultVo<T> getExceptionResult(String uuid) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setRequestId(uuid);
        log.info("uuid={}, clz={}", uuid, resultVo.getClass());
        return resultVo;
    }
}
