package com.zhanghf;

import com.alibaba.fastjson.JSONObject;
import com.zhanghf.util.HttpConnectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

import static com.zhanghf.dto.InterfaceInfoDTO.RELEASE_EXTERNAL_SHARING_PLATFORM_URL;

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
        String url = RELEASE_EXTERNAL_SHARING_PLATFORM_URL + 8301;
        FileInputStream fis;
        JSONObject params = new JSONObject();
        try {
            File file = new File("D:\\photo\\cbzmInfo.pdf");
            fis = new FileInputStream(file);
            byte[] byt = new byte[fis.available()]; // 字节数据 available()
            fis.read(byt); // 从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中。以整数形式返回实际读取的字节数。
            //System.out.println(new BASE64Encoder().encode(byt));//base64码,每76个字符增加一个换行符
            params.put("srcpdffile", byt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        params.put("srcpdffile", "http://ybj.zjzwfw.gov.cn:10540/openapiApp/download?key=bizamt/rdm/1569210553684tLK.pdf");
        params.put("keyword", "盖章");
        params.put("typepdffile", "1");
        params.put("signType", "4");
        params.put("sealid", "25538");
        Object data = HttpConnectionUtils.httpConnectionPost(uuid, url, params);
        System.out.println(data);
    }
}
