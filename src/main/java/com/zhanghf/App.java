package com.zhanghf;

import com.alibaba.fastjson.JSON;
import com.zhanghf.po.AddressInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

/**
 * @author zhanghf
 * @version 1.0
 * @date 21:20 2020/3/6
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        AddressInfo addressInfo = new AddressInfo();
        boolean flag = CollectionUtils.isEmpty(JSON.parseObject(JSON.toJSONString(addressInfo)));
        log.info("flag={}", flag);
        addressInfo.setAddress("地址");
        flag = CollectionUtils.isEmpty(JSON.parseObject(JSON.toJSONString(addressInfo)));
        log.info("flag={}", flag);
    }
}
