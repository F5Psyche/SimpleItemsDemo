package com.zhanghf.util;

import com.alibaba.fastjson.JSON;
import com.zhanghf.dto.CommonDTO;
import com.zhanghf.enums.BusinessCodeEnum;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 公共工具类
 *
 * @author zhanghf
 * @version 1.0
 * @date 9:07 2020/3/12
 */
@Slf4j
public class CommonUtils {

    private CommonUtils() {
        throw new IllegalStateException("CommonUtils");
    }

    /**
     * 将异常信息转换为字符串
     *
     * @param e 异常信息
     * @return 字符串
     */
    public static String exceptionToString(Exception e) {
        PrintWriter pw = null;
        try {
            StringWriter sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        } catch (Exception ex) {
            return "ExceptionToString is error";
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }


    public static boolean entityEmpty(String uuid, Object object) {
        if (StringUtils.isEmpty(object)) {
            return true;
        } else {
            String data = JSON.toJSONString(object);
            String simpleName = object.getClass().getSimpleName();
            switch (simpleName) {
                case "String":
                case "Long":
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

    public static boolean entityNotEmpty(String uuid, Object object) {
        if (StringUtils.isEmpty(object)) {
            return false;
        } else {
            String data = JSON.toJSONString(object);
            String simpleName = object.getClass().getSimpleName();
            switch (simpleName) {
                case "String":
                case "Long":
                    return !StringUtils.isEmpty(data);
                case "ArrayList":
                case "JSONArray":
                    return !CollectionUtils.isEmpty(JSON.parseArray(data));
                default:
                    log.info("uuid={}, simpleName={}, object={}", uuid, simpleName, object);
                    return !CollectionUtils.isEmpty(JSON.parseObject(data));

            }
        }
    }

    public static ResultVo<Map<String, Object>> entityEmptyResultVo(String uuid, Object object) {
        ResultVo<Map<String, Object>> resultVo = new ResultVo<>(uuid);
        if (entityEmpty(uuid, object)) {
            resultVo.setResult(Collections.emptyMap());
            resultVo.setResultDes(BusinessCodeEnum.CONTENT_EMPTY.getMsg());
            resultVo.setCode(BusinessCodeEnum.CONTENT_EMPTY.getCode());
            resultVo.setSuccess(true);
            return resultVo;
        }
        Map<String, Object> map = entityToMap(uuid, object);
        Set<String> keys = map.keySet();
        for (String key : keys) {
            if (entityEmpty(uuid, map.get(key))) {
                resultVo.setResult(Collections.emptyMap());
                resultVo.setResultDes(BusinessCodeEnum.CONTENT_EMPTY.getMsg());
                resultVo.setCode(BusinessCodeEnum.CONTENT_EMPTY.getCode());
                resultVo.setSuccess(true);
                return resultVo;
            }
        }
        return resultVo;
    }

    public static Map<String, Object> entityToMap(String uuid, Object object) {
        Map<String, Object> map = new HashMap<>(16);
        try {
            Class<?> clazz = object.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(object);
                map.put(fieldName, value);
            }
        } catch (IllegalAccessException e) {
            log.error("uuid={}, errMsg={}", uuid, exceptionToString(e));
        }
        return map;
    }

    /**
     * @param uuid        唯一识别码
     * @param inputStream InputStream
     * @return ResultVo
     */
    public static ResultVo<String> inputStreamToString(String uuid, InputStream inputStream) {
        ResultVo<String> resultVo = new ResultVo<>();
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            inputStreamReader = new InputStreamReader(inputStream, CommonDTO.CHARSET_NAME);
            bufferedReader = new BufferedReader(inputStreamReader);
            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                buffer.append(lines);
            }
            resultVo.setResult(buffer.toString());
            resultVo.setSuccess(true);
        } catch (Exception e) {
            resultVo.setResult("");
            resultVo.setCode("8099");
            resultVo.setResultDes(e.toString());
            log.error("<inputStreamToString.Exception>uuid={}, errMsg={}", uuid, exceptionToString(e));
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                resultVo.setResult("");
                resultVo.setCode("8099");
                resultVo.setResultDes(e.toString());
                log.error("<inputStreamToString.IOException>uuid={}, errMsg={}", uuid, exceptionToString(e));
            }
        }
        return resultVo;
    }
}
