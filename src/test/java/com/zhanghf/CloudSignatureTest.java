package com.zhanghf;

import com.alibaba.fastjson.JSONObject;
import com.zhanghf.constant.InterfaceInfoDMO;
import com.zhanghf.util.HttpConnectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * @author zhanghf
 * @version 1.0
 * @date 14:24 2020/3/12
 */
@Slf4j
public class CloudSignatureTest {

    @Test
    public void mutualCode8301() {
        String uuid = UUID.randomUUID().toString();
        String url = InterfaceInfoDMO.MASTER_EXTERNAL_SHARING_PLATFORM_URL + 8301;
        System.out.println(url);
        JSONObject params = new JSONObject();
        File file = new File("D:\\photo\\cbzmInfo_comp.pdf");
        try (
                FileInputStream fis = new FileInputStream(file)
        ) {
            // 字节数据 available()
            byte[] byt = new byte[fis.available()];
            // 从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中。以整数形式返回实际读取的字节数。
            Integer nums = fis.read(byt);
            String base64Code = Base64.encodeBase64String(byt);
            //log.info("nums={}, base64Code={}", nums, base64Code);
            params.put("srcpdffile", byt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        params.put("srcpdffile", "http://ybj.zjzwfw.gov.cn:10540/openapiApp/download?key=bizamt/rdm/1569210553684tLK.pdf");
        //params.put("srcpdffile", "http://10.85.159.203:10540/openapiApp/download?key=bizamt/rdm/159134552206211J.pdf");
        params.put("keyword", "签章");
        params.put("typepdffile", "1");
        params.put("signType", "4");
        //params.put("sealid", "25538");
        //params.put("signatureAppKey","33000000031835");
        //log.info("params={}", params);
        for (int i = 0; i < 200; i++) {
            String data = HttpConnectionUtils.httpConnectionPost(uuid, url, params);
            System.out.println(data);
            if (data.contains("外部系统繁忙,请稍后再试")) {
                break;
            }
        }


    }


    @Test
    public void getImageUrl() {
        String ossUrl = "http://10.85.159.203:10540/fileUpload/upload";
        File file = new File("D:\\photo\\cbzmInfo_comp.pdf");
        PostMethod filePost = new PostMethod(ossUrl);
        HttpClient client = new HttpClient();
        try {
            Part[] parts = {new FilePart("file", file), new StringPart("isPublic", "0")};
            filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            int status = client.executeMethod(filePost);
            if (status == HttpStatus.SC_OK) {
                String response = new String(filePost.getResponseBodyAsString().getBytes("utf-8"));
                JSONObject responJSON = JSONObject.parseObject(response);
                String imageUlr = "http://10.85.159.203:10540/openapiApp/download?key=" + responJSON.getJSONObject("result").getString("key");
                log.info("url = {}", imageUlr);
            } else {
                System.out.println("上传失败");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            filePost.releaseConnection();
        }
    }

    @Test
    public void downLoadFileByHttp() {
        String uuid = UUID.randomUUID().toString();
        log.info("uuid={}", uuid);
        String httpURL = "http://10.85.159.203:10540/openapiApp/download?key=bizamt/rdm/159134552206211J.pdf";
        String fileName = "D:\\photo\\" + uuid + ".pdf";
        File file = new File(fileName);
        InputStream inputStream = null;
        FileOutputStream fos = null;
        try {
            URL url = new URL(httpURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            inputStream = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int len;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.close();
            byte[] getData = bos.toByteArray();
            fos = new FileOutputStream(file);
            fos.write(getData);
        } catch (Exception e) {
            log.error("uuid={}, errMsg={}", uuid, e.toString());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("uuid={}, errMsg={}", uuid, e.toString());
            }
        }

    }
}
