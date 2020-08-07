package com.zhanghf.constant;

import org.apache.http.client.config.RequestConfig;

/**
 * @author zhanghf
 * @version 1.0
 * @date 18:16 2020/7/28
 */
public final class CommonParams {

    public static final RequestConfig REQUEST_TIMEOUT_CONFIG = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000)
            .setConnectionRequestTimeout(30000).build();

    public static final String HEADER_NAME = "Content-Type";

    public static final String HEADER_VALUE = "application/json";

    public static final String MATTER_MAX_TONG_ID_URL = "http://10.85.159.203:10420/power/maxTongId";

    public static final String MATTER_INFO_URL = "http://10.85.159.203:10420/power/matterInfo/andBetween";

    public static final String OSS_REQUEST_URL = "http://10.85.159.203:10540/fileUpload/upload";
    public static final String OUT_PROCESS_IMAGE_URL = "http://10.85.159.203:10540/openapiApp/download?key=";
    public static final String OUT_PROCESS_IMAGE_TYPE = "&imgType=1";

    public static final Long MATTER_INFO_NUM = 500L;

    public static final String SUCCESS_KEY = "success";

    public static final Integer INITIAL_CAPACITY = 16;

    public static final String COMMON_LOGGER_ERROR_INFO_PARAM = "uuid={}, errMsg={}";

    public static final String COMMON_INPUT_PARAM_LOG_INFO_WITH_VO = "uuid={}, vo={}, resultVo={}";

    private CommonParams() {
    }
}
