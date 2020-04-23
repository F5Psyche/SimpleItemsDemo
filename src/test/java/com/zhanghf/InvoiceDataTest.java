package com.zhanghf;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhanghf.constant.InterfaceInfoDMO;
import com.zhanghf.po.ClaimRecord;
import com.zhanghf.util.HttpConnectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.zhanghf.constant.InterfaceInfoDMO.MASTER_EXTERNAL_SHARING_PLATFORM_URL;
import static com.zhanghf.constant.InterfaceInfoDMO.RELEASE_EXTERNAL_SHARING_PLATFORM_URL;

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
        long start_time = System.currentTimeMillis();
        String url = InterfaceInfoDMO.RELEASE_EXTERNAL_SHARING_PLATFORM_URL + 7206;
        System.out.println(url);
        JSONObject params = new JSONObject();
        params.put("payerId", "330502201812094928");//330102195605240080
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
        params.put("eInvoiceIds", list);
        params.put("payerId", "725705683346688243");
        params.put("sequenceId", "123");
        Object object = HttpConnectionUtils.httpConnectionPost(uuid, url, params);
        System.out.println(object);
    }


    @Test
    public void mutualCode7208() {
        String uuid = UUID.randomUUID().toString();
        String url = RELEASE_EXTERNAL_SHARING_PLATFORM_URL + 7208;
        JSONObject params = new JSONObject();
        ClaimRecord claimRecord = new ClaimRecord();
        claimRecord.setEInvoiceId("3502847785-68269698");
        claimRecord.setClaimAmount("0");
        List<ClaimRecord> list = new ArrayList<>();
        list.add(claimRecord);
        params.put("claimRecords", list);
        params.put("caseId", "123");
        Object object = HttpConnectionUtils.httpConnectionPost(uuid, url, params);
        log.info("params={}, object={}",params,object);
    }



    @Test
    public void mutualCode7207Past() {
        String uuid = UUID.randomUUID().toString();
        String url = MASTER_EXTERNAL_SHARING_PLATFORM_URL + 7207;
        JSONObject params = new JSONObject();
        List<String> list = new ArrayList<>();
        list.add("330601202324590319");
        //list.add("330601202324588882");
        //list.add("330601202324588514");
        params.put("eInvoiceIDs", list);
        params.put("personID", "330103197903280721");//身份证号
        params.put("corpID", "330800100005");//医疗机构代码
        params.put("sequenceID", "123");//业务流水号
        System.out.println(url);
        System.out.println(params);
        Object data = HttpConnectionUtils.httpConnectionPost(uuid, url, params);
        System.out.println(data);
    }

    @Test
    public void mutualCode7208Past() {
        String uuid = UUID.randomUUID().toString();
        String url = MASTER_EXTERNAL_SHARING_PLATFORM_URL + 7208;
        JSONObject params = new JSONObject();
        List<String> list = new ArrayList<>();
        list.add("330601202324590319");
        //list.add("330601202324588882");
        //list.add("330601202324588514");
        List<String> list2 = new ArrayList<>();
        list2.add("0");
        //list2.add("0");
        //list2.add("0");
        params.put("eInvoiceIds", list);
        params.put("claimAmounts", list2);
        params.put("insuranceCoId", "330800100005");//医疗机构代码
        params.put("caseId", "123");//业务流水号
        System.out.println(params);
        Object data = HttpConnectionUtils.httpConnectionPost(uuid, url, params);
        System.out.println(data);
    }

}
