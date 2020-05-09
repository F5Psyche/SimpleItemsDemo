package com.zhanghf;

import com.alibaba.fastjson.JSONObject;
import com.zhanghf.constant.InterfaceInfoDMO;
import com.zhanghf.util.HttpConnectionUtils;
import com.zhanghf.util.TreeFormUtils;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhanghf
 * @version 1.0
 * @date 10:12 2020/4/21
 */
@Slf4j
public class GovPlatform2Test {

    @Test
    public void mutualCode8050() {
        String url = InterfaceInfoDMO.FEATURE_EXTERNAL_SHARING_PLATFORM_IP + "/mutual/gov/api/share/8050";
        JSONObject params = new JSONObject();
        params.put("AAB303", "1");
        Object data = HttpConnectionUtils.httpConnectionPost("", url, params);
        System.out.println(data);

        String a = "杭州";
        String b = "嘉兴市南湖区";
        log.info("a={}, b={}", a, b);
        b = a + b;
        a = b.substring(a.length());
        b = b.substring(0, b.length() - a.length());
        log.info("a={}, b={}", a, b);

        int x = 10;
        int y = 20;
        log.info("x={}, y={}", x, y);
        y = x + y;
        x = y - x;
        y = y - x;
        log.info("x={}, y={}", x, y);

    }

    @Test
    public void mutualTest(){
        String url = InterfaceInfoDMO.FEATURE_EXTERNAL_SHARING_PLATFORM_IP + "/mutual/gov/api/share/8050";
        JSONObject params = new JSONObject();
        JSONObject data = (JSONObject)HttpConnectionUtils.httpConnectionPost("", url, params);
        System.out.println(TreeFormUtils.listToTreeUtils(data.getJSONArray("result"), "AAB301", "AAB304", "children"));
    }
}
