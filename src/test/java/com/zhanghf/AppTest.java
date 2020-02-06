package com.zhanghf;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class AppTest {

    @Test
    public void httpTest() {
        String url = "https://www.baidu.com/";
        JSONObject params = new JSONObject();
        log.info("url={}, params={}", url, params);
    }
}
