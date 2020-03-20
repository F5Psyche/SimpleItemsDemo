package com.zhanghf;

import com.alibaba.fastjson.JSON;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author zhanghf
 * @version 1.0
 * @date 21:20 2020/3/6
 */
@Slf4j
public class App {

    public static boolean entityEmpty(String uuid, Object object) {
        if (StringUtils.isEmpty(object)) {
            return true;
        } else {
            String data = JSON.toJSONString(object);
            String simpleName = object.getClass().getSimpleName();
            switch (simpleName) {
                case "String":
                    return StringUtils.isEmpty(data);
                case "ArrayList":
                case "JSONArray":
                    return CollectionUtils.isEmpty(JSON.parseArray(data));
                default:
                    log.info("uuid={}, simpleName={}, object={}", uuid, simpleName, object);
                    return CollectionUtils.isEmpty(JSON.parseObject(data));

            }
        }
    }

    public static void main(String[] args) {
        Long matProcId = -9L;
        System.out.println(matProcId);
    }
}
