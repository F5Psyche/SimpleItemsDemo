package com.zhanghf.dto;

import org.apache.http.client.config.RequestConfig;

/**
 * @author zhanghf
 * @version 1.0
 * @date 14:26 2020/3/12
 */
public class InterfaceInfoDTO {
    public static RequestConfig REQUEST_TIMEOUT_CONFIG = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000)
            .setConnectionRequestTimeout(3000000).build();

    public final static String LOCALHOST_EXTERNAL_SHARING_PLATFORM_URL = "http://localhost:10420/mutual/partsCenter/serviceEntrance/";

    public final static String FEATURE_EXTERNAL_SHARING_PLATFORM_URL = "http://10.85.159.203:10420/mutual/partsCenter/serviceEntrance/";

    public final static String RELEASE_EXTERNAL_SHARING_PLATFORM_URL = "http://10.85.159.206:10420/mutual/partsCenter/serviceEntrance/";

    /**
     * 10.85.159.249:10420
     */
    public final static String MASTER_EXTERNAL_SHARING_PLATFORM_URL = "http://10.85.159.206:9990/mutual/partsCenter/serviceEntrance/";

    @Override
    public String toString() {
        return "InterfaceInfoDTO{" + "REQUEST_TIMEOUT_CONFIG='" + REQUEST_TIMEOUT_CONFIG + '\'' + '}';
    }
}
