package com.zhanghf.dto;

import org.apache.http.client.config.RequestConfig;

public class CommonDTO {

    public static RequestConfig REQUET_TIMEOUT_CONFIG = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000)
            .setConnectionRequestTimeout(30000).build();

    public static final String CHARSET_NAME = "UTF-8";
    public static final String HEADER_NAME = "Content-Type";
    public static final String HEADER_VALUE = "application/json";
}
