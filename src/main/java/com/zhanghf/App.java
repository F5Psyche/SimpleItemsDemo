package com.zhanghf;

import com.alibaba.fastjson.JSON;
import com.zhanghf.po.AdressInfo;
import com.zhanghf.po.ExtMatTab;
import org.springframework.util.CollectionUtils;

/**
 * Hello world!
 */
public class App {
    public void extMatInfoSave(ExtMatTab info) {
        System.out.println(info.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        AdressInfo adressInfo = new AdressInfo();
        boolean flag = CollectionUtils.isEmpty(JSON.parseObject(JSON.toJSONString(adressInfo)));
        System.out.println(flag);
        adressInfo.setAddress("地址");
        flag = CollectionUtils.isEmpty(JSON.parseObject(JSON.toJSONString(adressInfo)));
        System.out.println(flag);
    }
}
