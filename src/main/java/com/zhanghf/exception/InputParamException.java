package com.zhanghf.exception;

/**
 * 自定义入参异常
 *
 * @author zhanghf
 * @version 1.0
 * @date 10:19 2020/12/8
 */
public class InputParamException extends RuntimeException {

    public InputParamException() {

    }

    public InputParamException(String message) {
        super("参数<" + message + ">内容不符合规范");
    }
}
