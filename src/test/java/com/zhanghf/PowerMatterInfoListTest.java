package com.zhanghf;

import com.alibaba.fastjson.JSONObject;
import com.zhanghf.util.HttpConnectionUtils;
import org.junit.Test;

public class PowerMatterInfoListTest {

    @Test
    public void chainTest() {
        String url = "http://localhost:8080/organ";
        System.out.println(url);
        JSONObject params = new JSONObject();
        params.put("organId", "001008001026246");
        params.put("organArea", "330101");
        //Object data = HttpConnectionUtils.httpConnectionPost("",url,params);
        Object data = HttpConnectionUtils.httpClientPost("", url, params);
        System.out.println(data);
    }
}
