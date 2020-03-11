package com.zhanghf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhanghf.util.HttpConnectionUtils;
import com.zhanghf.util.XmlMapUtils;
import org.junit.Test;

import java.util.Map;

public class PowerMatterInfoListTest {

    private static final String initUrl = "http://10.85.159.203:10420/mutual/partsCenter/serviceEntrance/";

    @Test
    public void getOrganInfoTest() {
        String url = "http://localhost:8080/ext/matter";
        System.out.println(url);
        JSONObject params = new JSONObject();
        params.put("organId", "001008001026246");
        params.put("organArea", "330101");
        //Object data = HttpConnectionUtils.httpConnectionPost("",url,params);
        Object data = HttpConnectionUtils.httpClientPost("", url, params);
        System.out.println(data);
    }


    @Test
    public void getOrganAreaCodeTest() {
        for (int i = 331000; i < 339999; i++) {
            String url = initUrl + "8205";
            JSONObject params = new JSONObject();
            params.put("jurisCode", i);
            params.put("ouGuid", "001008010006109");
            Object object = HttpConnectionUtils.httpConnectionPost("", url, params);
            System.out.println(object);
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(object)).getJSONArray("result").getJSONObject(0);
            int items = jsonObject.getInteger("items");
            if (items > 0) {
                System.out.println(i);
                break;
            }
        }
    }

    @Test
    public void getPowerMatterDetailInfoTest() {
        String url = initUrl + "8203";
        JSONObject params = new JSONObject();
        params.put("localInnerCode", "e54a1225-5531-464c-b040-5b1639f49440");
        Object obj = HttpConnectionUtils.httpConnectionPost("", url, params);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(obj)).getJSONArray("result").getJSONObject(0);
        JSONObject json = jsonObject.getJSONObject("implementDetailDTO");
        json.keySet().forEach(key -> {
//            System.out.println("key = " + key);
//            System.out.println(json.get(key).getClass().getSimpleName());
//            System.out.println(json.get(key));
//            System.out.println("******************");
            if ("basicInfoDTO".equals(key)) {
                String acceptAddress = json.getJSONObject("basicInfoDTO").getString("acceptAddress");
                System.out.println(acceptAddress);
                Map<String, Object> map = XmlMapUtils.xmlToMap(acceptAddress);
                System.out.println(map);
            }
        });
    }

    @Test
    public void getSinglePowerMatterInfoTest() {
        String url = initUrl + "8205";
        JSONObject params = new JSONObject();
        params.put("ouGuid", "001008001026246");
        params.put("jurisCode", "330101");
        Object obj = HttpConnectionUtils.httpConnectionPost("", url, params);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(obj)).getJSONArray("result").getJSONObject(0);
        String data = jsonObject.getString("basicInfoDTOs");
        JSONObject json = JSON.parseArray(data).getJSONObject(0);
        json.keySet().forEach(key -> {
            System.out.println("key = " + key);
            System.out.println(json.get(key));
            System.out.println("******************");
        });
        //atg.biz.matterquery.matter.querydetail
        //AtgBizMatterqueryMatterQuerydetailResponse
//        List<PowerMatterInfo> basicInfoDTOS = JSON.parseArray(data, PowerMatterInfo.class);
//        JSONObject json = (JSONObject) JSON.toJSON(basicInfoDTOS.get(5));
//        String xmlString = json.getString("acceptAddress");
//        Map<String, Object> map = XmlMapUtils.xmlToMap(xmlString);
//        System.out.println(json);
    }

    @Test
    public void matterDetailQuery() {
        String url = "http://localhost:8080/matterDetail/query";
        System.out.println(url);
        JSONObject params = new JSONObject();
        params.put("localInnerCode", "");
        Object data = HttpConnectionUtils.httpClientPost("", url, params);
        System.out.println(data);
    }
}
