package com.zhanghf.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

import static com.zhanghf.constant.CommonDMO.CHARSET_NAME;

/**
 * 加密工具类
 *
 * @author zhanghf
 * @version 1.0
 * @date 9:07 2020/3/12
 */
@Slf4j
public class EncryptionUtils {

    /**
     * 加密方法
     *
     * @param encryptionText 需要加密的内容
     * @param encryptionType 加密类型（SHA-256;SHA-512;MD5）
     * @return 加密后的内容
     */
    public static String encryption(String uuid, final String encryptionText, final String encryptionType) {
        String result = null;
        if (encryptionText != null && encryptionText.length() > 0) {
            try {
                //加密开始
                //创建加密对象,传入加密类型
                MessageDigest messageDigest = MessageDigest.getInstance(encryptionType);
                //传入要加密的字符串
                messageDigest.update(encryptionText.getBytes(CHARSET_NAME));
                //得到byte数组
                byte[] byteBuffer = messageDigest.digest();
                //将byte数组转成String类型
                StringBuffer buffer = new StringBuffer();
                //遍历byte数组
                int size = byteBuffer.length;
                for (int i = 0; i < size; i++) {
                    //转换成16进制并存储在字符串中
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    //如果结果小于16,则在前面加上一个0填满两位的十六进制
                    if (hex.length() == 1) {
                        buffer.append("0");
                    }
                    buffer.append(hex);
                }
                result = buffer.toString();
            } catch (Exception e) {
                log.error("uuid={}, errMsg={}", uuid, CommonUtils.exceptionToString(e));
            }

        }
        return result;
    }
}
