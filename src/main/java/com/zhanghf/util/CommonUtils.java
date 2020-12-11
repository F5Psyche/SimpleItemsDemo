package com.zhanghf.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.zhanghf.enums.BusinessCodeEnum;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

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
     * 将异常信息转换为字符串(不换行。如果要换行，将\t换成\n)
     * e.toString() 获取异常名称
     * stackTraceElements获取出现异常的行数、类名、方法名
     *
     * @param e 异常信息
     * @return 字符串
     */
    public static String getStackTraceString(Throwable e) {
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        StringBuilder builder = new StringBuilder();
        builder.append(e.toString());
        if (stackTraceElements != null && stackTraceElements.length > 0) {
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                builder.append("\t at ").append(stackTraceElement.toString());
            }
        }
        return builder.toString();
    }

    public static byte[] convertBytes(String uuid, Object obj) {
        try (
                ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream outputStream = new ObjectOutputStream(byteOutputStream)
        ) {
            outputStream.writeObject(obj);
            outputStream.flush();
            return byteOutputStream.toByteArray();
        } catch (Exception e) {
            log.error("uuid={}, errMsg={}", uuid, getStackTraceString(e));
            return new byte[0];
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

    /**
     * 实体对象转Map
     *
     * @param uuid   唯一识别码
     * @param object 对象
     * @return map
     */
    public static Map<String, Object> entityToMap(String uuid, Object object) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            Class<?> clazz = object.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                ReflectionUtils.makeAccessible(field);
                String fieldName = field.getName();
                Object value = field.get(object);
                map.put(fieldName, value);
            }
        } catch (IllegalAccessException e) {
            log.error("uuid={}, errMsg={}", uuid, getStackTraceString(e));
        }
        return map;
    }

    /**
     * 输入流转换成字符串
     *
     * @param uuid        唯一识别码
     * @param inputStream InputStream
     * @return 字符串
     */
    public static String inputStreamToString(String uuid, InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        try (
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                builder.append(lines);
            }
            return builder.toString();
        } catch (Exception e) {
            log.error("uuid={}, errMsg={}", uuid, getStackTraceString(e));
        }
        return null;
    }

    /**
     * 多页pdf进行转换
     *
     * @param uuid        唯一识别码
     * @param filePath    路径
     * @param pdfFileName 文件名称
     * @param jpgFileName 文件名称
     */
    public static void updateImageType(String uuid, String filePath, String pdfFileName, String jpgFileName) {
        Document document = new Document();
        try {
            document.setFile(filePath + pdfFileName);
            // 缩放比例
            float scale = 2.5f;
            // 旋转角度
            float rotation = 0f;
            int pageSize = document.getNumberOfPages();
            for (int i = 0; i < pageSize; i++) {
                BufferedImage image = (BufferedImage) document.getPageImage(i,
                        GraphicsRenderingHints.SCREEN,
                        org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation,
                        scale);
                File file = new File(filePath + i + jpgFileName);
                ImageIO.write(image, "jpg", file);
                image.flush();
            }
        } catch (Exception e) {
            log.info("uuid={}, filePath={}, pdfFileName={}, jpgFileName={}, errMsg={}", uuid, filePath, pdfFileName, jpgFileName, getStackTraceString(e));
        }
        document.dispose();
    }


    public static boolean updateImageType(String uuid, String filePath, File file) {
        boolean flag = false;
        Document document = new Document();
        try {
            document.setFile(filePath);
            // 缩放比例
            float scale = 2.5f;
            // 旋转角度
            float rotation = 0f;
            int pageSize = document.getNumberOfPages();
            if (pageSize == 1) {
                BufferedImage image = (BufferedImage) document.getPageImage(pageSize - 1,
                        GraphicsRenderingHints.SCREEN,
                        org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation,
                        scale);
                ImageIO.write(image, "jpg", file);
                image.flush();
                flag = true;
            }
        } catch (Exception e) {
            log.info("uuid={}, filePath={}, file={}, errMsg={}", uuid, filePath, file, getStackTraceString(e));
        }
        document.dispose();
        return flag;
    }

    /**
     * 获取系统时间
     *
     * @param pattern 时间格式
     * @return 返回时间
     */
    public static String getTime(String pattern) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static String getLocalhost(String uuid) {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            return ip.getHostAddress();
        } catch (UnknownHostException e) {
            log.error("uuid={}, errMsg={}", uuid, e.getMessage());
            return null;
        }
    }


    public static List<String> getLocalIpList(String uuid) {
        List<String> ipList = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> ipAddressEnum = networkInterface.getInetAddresses();
                while (ipAddressEnum.hasMoreElements()) {
                    InetAddress ipAddress = ipAddressEnum.nextElement();
                    String ip = ipAddress.getHostAddress();
                    log.debug("uuid={}, ipAddress={}, flag={}, ip={}, netWorkName={}", uuid, ipAddress, ipAddress.isLoopbackAddress(), ip, networkInterface.getName());
                    if (ipAddress.isLoopbackAddress() || ip.contains(":")) {
                        continue;
                    }
                    ipList.add(ip);
                }
            }
            Collections.sort(ipList);
        } catch (Exception e) {
            log.error("Failed to get local ip list. uuid={}, errMsg={}", uuid, CommonUtils.getStackTraceString(e));
        }
        return ipList;
    }
}
