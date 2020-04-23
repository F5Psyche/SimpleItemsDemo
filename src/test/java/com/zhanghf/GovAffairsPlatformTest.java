package com.zhanghf;

import com.alibaba.fastjson.JSONObject;
import com.zhanghf.constant.InterfaceInfoDMO;
import com.zhanghf.util.HttpConnectionUtils;
import org.junit.Test;

import java.util.UUID;

/**
 * @author zhanghf
 * @version 1.0
 * @date 14:19 2020/4/14
 */
public class GovAffairsPlatformTest {

    @Test
    public void mutualCode8001() {
        String uuid = UUID.randomUUID().toString();
        String url = InterfaceInfoDMO.RELEASE_EXTERNAL_SHARING_PLATFORM_URL + 8001;
        String param = "{\"serviceCodeId\":\"08-00122-002\",\"applyCardNumber\":\"610402197104261249\",\"applyCardType\":\"31\",\"deptId\":\"001003148\",\"areaCode\":\"330000\",\"applyFrom\":\"0\",\"applyName\":\"王秦英\",\"busType\":\"0\"}";
        JSONObject params = JSONObject.parseObject(param);
        System.out.println(params);
        Object data = HttpConnectionUtils.httpConnectionPost(uuid, url, params);
        System.out.println(data);
    }
}
