package com.zhanghf.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:05 2020/3/18
 */
@Data
@NoArgsConstructor
public class ResultVo<T> {

    private T result;
    private boolean isSuccess = false;

    private String resultDes;

    private String code;

    private String requestId;

    private String itemServerIp;

    public static <T> ResultVo<T> instance() {
        return new ResultVo<>();
    }

    public ResultVo(String requestId) {
        this.requestId = requestId;
    }

    public ResultVo(String resultDes, String code, String requestId) {
        this.resultDes = resultDes;
        this.code = code;
        this.requestId = requestId;
    }
}
