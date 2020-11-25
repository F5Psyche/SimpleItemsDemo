package com.zhanghf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhanghf.enums.BusinessCodeEnum;
import com.zhanghf.po.ExtMatTab;
import com.zhanghf.util.HttpConnectionUtils;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:02 2020/9/3
 */
@Slf4j
public class Demo {

    public static void main(String[] args) {
        try {
//            Map<String, String> bizMapTemp = new HashMap<>(16);
//            bizMapTemp.put("workorderno", "8df56f81-c0bc-480d-a883-145f2bd8e8a3");
//            bizMapTemp.put("nodeid","xcbdj");
//            ResultVo<String> resultVo = HttpConnectionUtils.managePost("http://10.85.94.57:9099/insiis/interfaceGmApi.do", bizMapTemp, "6014");
//            System.out.println(resultVo);
            long startTime = System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            JSONObject params = new JSONObject();
            params.put("AAC002", "33028119841105492X");
            params.put("trade", "8054");
            params.put("AAB301", "330212");
            JSONObject header = new JSONObject();
            header.put("signature", "CF7F5CCCBE28470307596BACCE3F4FDA");
            header.put("appKey", "test6");
            header.put("nonceStr", "9a0459037d6d46628e71cad68b2714d5");
            System.out.println(JSON.toJSONString(params));

            header.put("time", simpleDateFormat.format(new Date()));
            Object obj = HttpConnectionUtils.httpClientPost("", "http://10.87.0.68:10500/frontInterface/interface/callBackService-8054-shengbenji-339900-pro", params, header);

            System.out.println(System.currentTimeMillis() - startTime + ":" + obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResultVo<List<ExtMatTab>> resultVo = ResultVo.instance();
        resultVo.setResultDes(BusinessCodeEnum.COL_MUST_FILL.getMsg());
        System.out.println(resultVo);

    }
}
