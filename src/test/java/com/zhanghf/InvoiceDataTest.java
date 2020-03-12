package com.zhanghf;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhanghf.util.HttpConnectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.zhanghf.dto.InterfaceInfoDTO.RELEASE_EXTERNAL_SHARING_PLATFORM_URL;

/**
 * @author zhanghf
 * @version 1.0
 * @date 21:20 2020/3/6
 */
@Slf4j
public class InvoiceDataTest {

    @Test
    public void mutualCode7206() {
        String uuid = UUID.randomUUID().toString();
        Long start_time = System.currentTimeMillis();
        String url = RELEASE_EXTERNAL_SHARING_PLATFORM_URL + 7206;
        System.out.println(url);
        JSONObject params = new JSONObject();
        params.put("payerId", "33080219910102361X");//330102195605240080
        //params.put("invoicePartyCode", "330000102001");//
        //params.put("invoicePartyName", "义乌市中心医院");//
        //params.put("beginDate", "20190101");
        //params.put("endDate", "20191122");
        params.put("page", "1");
        params.put("pageSize", "30");
        Object data = HttpConnectionUtils.httpConnectionPost(uuid, url, params);
        System.out.println(data);
        System.out.println(System.currentTimeMillis() - start_time);
        for (Object object : JSONArray.parseArray(JSONObject.parseObject(JSONObject.toJSONString(data)).getString("result"))) {
            System.out.println(object);
        }
    }

    @Test
    public void mutualCode7207() {
        String uuid = UUID.randomUUID().toString();
        String url = RELEASE_EXTERNAL_SHARING_PLATFORM_URL + 7207;
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
