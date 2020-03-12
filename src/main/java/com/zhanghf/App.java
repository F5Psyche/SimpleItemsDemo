package com.zhanghf;

import com.alibaba.fastjson.JSON;
import com.zhanghf.po.AddressInfo;
import com.zhanghf.po.ExtMatTab;
import org.springframework.util.CollectionUtils;

/**
 * @author zhanghf
 * @version 1.0
 * @date 21:20 2020/3/6
 */
public class App {
    public void extMatInfoSave(ExtMatTab info) {
        System.out.println(info.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        AddressInfo addressInfo = new AddressInfo();
        boolean flag = CollectionUtils.isEmpty(JSON.parseObject(JSON.toJSONString(addressInfo)));
        System.out.println(flag);
        addressInfo.setAddress("地址");
        flag = CollectionUtils.isEmpty(JSON.parseObject(JSON.toJSONString(addressInfo)));
        System.out.println(flag);
    }
}
