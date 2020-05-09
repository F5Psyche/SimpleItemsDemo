package com.zhanghf;

import com.alibaba.fastjson.JSONObject;
import com.zhanghf.constant.InterfaceInfoDMO;
import com.zhanghf.util.HttpConnectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
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
        String url = InterfaceInfoDMO.RELEASE_EXTERNAL_SHARING_PLATFORM_URL + 8301;
        System.out.println(url);
        JSONObject params = new JSONObject();
        File file = new File("D:\\photo\\xcbjfjsdzd.pdf");
        try (
                FileInputStream fis = new FileInputStream(file)
        ) {
            // 字节数据 available()
            byte[] byt = new byte[fis.available()];
            // 从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中。以整数形式返回实际读取的字节数。
            Integer nums = fis.read(byt);
            String base64Code = Base64.encodeBase64String(byt);
            log.info("nums={}, base64Code={}", nums, base64Code);
            params.put("srcpdffile", byt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //params.put("srcpdffile", "http://ybj.zjzwfw.gov.cn:10540/openapiApp/download?key=bizamt/rdm/1569210553684tLK.pdf");
        params.put("keyword", "省医疗保障");
        params.put("typepdffile", "2");
        params.put("signType", "4");
        //params.put("sealid", "25538");
        //params.put("signatureAppKey","33000000031835");
        Object data = HttpConnectionUtils.httpConnectionPost(uuid, url, params);
        System.out.println(data);
    }
}
