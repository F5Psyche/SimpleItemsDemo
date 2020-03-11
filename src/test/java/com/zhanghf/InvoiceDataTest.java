package com.zhanghf;

import com.alibaba.fastjson.JSONObject;
import com.zhanghf.util.HttpConnectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author zhanghf
 * @version 1.0
 * @date 21:20 2020/3/6
 */
@Slf4j
public class InvoiceDataTest {

    private static final String initUrl = "http://10.85.159.203:10420/mutual/partsCenter/serviceEntrance/";

    @Test
    public void mutualCode7207() {
        String uuid = UUID.randomUUID().toString();
        String url = initUrl + 7207;
        JSONObject params = new JSONObject();
        List<String> list = new ArrayList<>();
        list.add("3502847785-68269698");
        params.put("corpId", "11330000MB18470516");
        params.put("eInvoiceIds", list);
        params.put("payerId", "330411199403303638");
        params.put("sequenceId", "123");
        Object object = HttpConnectionUtils.httpConnectionPost(uuid, url, params);
        System.out.println(object);

    }

}
