package com.zhanghf.vo;

import lombok.Data;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:05 2020/3/18
 */
@Data
public class ResultVo<T> {
    private T result;
    private boolean isSuccess = false;
    private String code;
    private String resultDes;
    private String requestId;
}
