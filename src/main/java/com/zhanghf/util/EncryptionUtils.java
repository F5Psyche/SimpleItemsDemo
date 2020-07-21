package com.zhanghf.util;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

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
        if (encryptionText != null && encryptionText.length() > 0) {
            try {
                //加密开始
                //创建加密对象,传入加密类型
                MessageDigest messageDigest = MessageDigest.getInstance(encryptionType);
                //传入要加密的字符串
                messageDigest.update(encryptionText.getBytes(StandardCharsets.UTF_8));
                //得到byte数组
                byte[] byteBuffer = messageDigest.digest();
                StringBuilder builder = new StringBuilder();
                //遍历byte数组
                for (byte b : byteBuffer) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) {
                        builder.append("0");
                    }
                    builder.append(hex);
                }
                return builder.toString();
            } catch (Exception e) {
                log.error("uuid={}, errMsg={}", uuid, CommonUtils.exceptionToString(e));
            }
        }
        return encryptionText;
    }

    /**
     * step2
     * 字符串Md5加密
     *
     * @param uuid 唯一识别码
     * @param s    需加密的字符串
     * @return 返回二进制
     */
    private static byte[] md5(String uuid, String s) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes(StandardCharsets.UTF_8));
            return algorithm.digest();
        } catch (Exception e) {
            log.error("uuid={}, errMsg={}", uuid, CommonUtils.exceptionToString(e));
        }
        return null;
    }

    /**
     * step3
     * 字符串Md5加密
     *
     * @param uuid 唯一识别码
     * @param hash hash
     * @return 加密字符串
     */
    private static final String toHex(String uuid, byte[] hash) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = null;
        try {
            buf = new StringBuffer(hash.length * 2);
            for (int i = 0; i < hash.length; i++) {
                if ((hash[i] & 0xff) < 0x10) {
                    buf.append("0");
                }
                buf.append(Long.toString(hash[i] & 0xff, 16));
            }
        } catch (Exception e) {
            log.error("uuid={}, errMsg={}", uuid, CommonUtils.exceptionToString(e));
        }
        return buf.toString();
    }

    /**
     * step1
     * 字符串Md5加密
     *
     * @param uuid 唯一识别码
     * @param s    需要加密的字符串
     * @return 加密后的字符串
     */
    public static String hash(String uuid, String s) {
        byte[] bytes = md5(uuid, s);
        String hex = toHex(uuid, bytes);
        if (hex != null) {
            return new String(hex.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        } else {
            return s;
        }
    }

    public static void main(String[] args) {
        log.info("hash={}", encryption("", "jiayou", "MD5"));
        log.info("hash={}", hash("", "jiayou"));
    }
}
