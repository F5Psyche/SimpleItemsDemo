package com.zhanghf;

import com.zhanghf.annotation.LoginInfo;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanghf
 * @version 1.0
 * @date 15:21 2020/4/15
 */
@Slf4j
public class AnnotationStudy {

    @LoginInfo(loginName = "add", passWord = "123")
    public boolean login(Map<String,String> map) throws Exception {
//        Method method = AnnotationStudy.class.getMethod("login",Map.class);
//        LoginInfo loginInfo = method.getAnnotation(LoginInfo.class);
//        String uname = loginInfo.loginName();
//        String upwd = loginInfo.passWord();
//        System.out.println(uname);
//        System.out.println(upwd);
        return false;
    }

    public static void main(String[] args) {
        try {
            Map<String,String> map = new HashMap<>();
            map.put("name","123");
            new AnnotationStudy().login(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
