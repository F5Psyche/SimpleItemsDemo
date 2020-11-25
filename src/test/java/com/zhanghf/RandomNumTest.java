package com.zhanghf;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:36 2020/3/31
 */
@Slf4j
public class RandomNumTest {

    @Test
    public void tenRandomNum() {
        JSONObject par = new JSONObject();
        Integer isPrint = par.getInteger("isPrint");
        System.out.println(isPrint);
    }
}

