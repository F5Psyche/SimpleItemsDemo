package com.zhanghf.util;

import com.zhanghf.annotation.LoginInfo;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhanghf
 * @version 1.0
 * @date 15:37 2020/4/15
 */
@Slf4j
public class AnnotationUtils {

    /**
     * 获取符合条件的所有注解
     *
     * @return 注解集合
     */
    public static List<Annotation> getAllAnnotation() {
        String uuid = UUID.randomUUID().toString();
        List<Annotation> list = new ArrayList<>();
        //遍历路径不可忘记,不然扫描全部项目包,包括引用的jar
        Reflections reflections = new Reflections("com.zhanghf");
        //获取带RequestMapping注解的类
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(RequestMapping.class);
        for (Class clazz : typesAnnotatedWith) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                Annotation[] annotations = method.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    list.add(annotation);
                    log.info("uuid={}, annotation={}", uuid, annotation);
                }
            }
        }
        return list;
    }

    public ResultVo gateWay(Map<String, String> map) {
        ResultVo resultVo = new ResultVo();
        //包名且不可忘记，不然扫描全部项目包，包括引用的jar
        Reflections reflections = new Reflections("com.zhanghf");
        //获取带Service注解的类
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Service.class);
        for (Class clazz : typesAnnotatedWith) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                //判断带自定义注解LoginInfo的method
                if (method.isAnnotationPresent(LoginInfo.class)) {
                    LoginInfo annotation = method.getAnnotation(LoginInfo.class);
                    //根据入参WayCode比较method注解上的WayCode,两者值相同才执行该method
                    String uName = annotation.loginName();
                    String passWord = annotation.passWord();
                    log.info("uName={}, passWord={}", uName, passWord);
                    if (uName.equals(map.get("userName"))){
                        try {
                            method.invoke(clazz.newInstance(), map);
                        } catch (Exception e) {
                            log.error("errMsg={}", e.toString());
                        }
                    }
                }

            }
        }
        return resultVo;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("userName","admin");
        new AnnotationUtils().gateWay(map);
    }
}
