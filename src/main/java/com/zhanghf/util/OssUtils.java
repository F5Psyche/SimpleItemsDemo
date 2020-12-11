package com.zhanghf.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhanghf.constant.CommonParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author zhanghf
 * @version 1.0
 * @date 9:30 2020/8/7
 */
@Slf4j
public class OssUtils {

    public static boolean downloadImageByHttp(String uuid, File file, String httpUrl) {
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            return downloadImageByHttp(uuid, conn, file);
        } catch (IOException e) {
            log.error(CommonParams.COMMON_LOGGER_ERROR_INFO_PARAM, uuid, CommonUtils.getStackTraceString(e));
            return false;
        }
    }

    private static boolean downloadImageByHttp(String uuid, HttpURLConnection conn, File file) {
        try (InputStream inputStream = conn.getInputStream()) {
            byte[] buffer = new byte[1024];
            int len;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            byte[] getData = bos.toByteArray();
            return downloadImageByHttp(uuid, file, getData);
        } catch (Exception e) {
            log.error(CommonParams.COMMON_LOGGER_ERROR_INFO_PARAM, uuid, CommonUtils.getStackTraceString(e));
            return false;
        }
    }

    private static boolean downloadImageByHttp(String uuid, File file, byte[] getData) {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(getData);
            return true;
        } catch (IOException e) {
            log.error(CommonParams.COMMON_LOGGER_ERROR_INFO_PARAM, uuid, CommonUtils.getStackTraceString(e));
            return false;
        }
    }

    private static String upload(String uuid, File file) {
        PostMethod filePost = new PostMethod(CommonParams.OSS_REQUEST_URL);
        HttpClient client = new HttpClient();
        try {
            Part[] parts = {new FilePart("file", file), new StringPart("isPublic", "0")};
            filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            int status = client.executeMethod(filePost);
            if (status == HttpStatus.SC_OK) {
                String response = new String(filePost.getResponseBodyAsString().getBytes(StandardCharsets.UTF_8));
                JSONObject responseJson = JSON.parseObject(response);
                String key = responseJson.getJSONObject("result").getString("key");
                return CommonParams.OUT_PROCESS_IMAGE_URL + key;
            } else {
                log.error(CommonParams.COMMON_LOGGER_ERROR_INFO_PARAM, uuid, "上传失败");
            }
        } catch (Exception e) {
            log.error(CommonParams.COMMON_LOGGER_ERROR_INFO_PARAM, uuid, CommonUtils.getStackTraceString(e));
        } finally {
            filePost.releaseConnection();
            file.delete();
        }
        return "";
    }


    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        String imageUrl = "http://xd-sxkxtoss.oss-cn-hangzhou-zwynet-d01-a.internet.cloud.zj.gov.cn/1/d/d89e5706-a5a4-4202-8f77-4c042fbaf2c6.jpg";
        String imageType = imageUrl.substring(imageUrl.lastIndexOf("."));

        File file = new File("C:\\photo\\" + uuid + imageType);
        boolean flag = downloadImageByHttp(uuid, file, imageUrl);
        if (flag) {
            String key = upload(uuid, file);
            log.info("uuid={}, imageType={}, file={}, key={}", uuid, imageType, file, key);
        }

    }
}
