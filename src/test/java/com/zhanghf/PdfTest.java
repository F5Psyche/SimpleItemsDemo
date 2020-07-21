package com.zhanghf;

import com.zhanghf.util.CommonUtils;
import com.zhanghf.util.ImageUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhanghf
 * @version 1.0
 * @date 9:27 2020/6/11
 */
@Slf4j
public class PdfTest {

    @Test
    public void updatePDFType() {
        String uuid = UUID.randomUUID().toString();
        boolean flag = CommonUtils.updateImageType(uuid, "D:\\photo\\cbzmInfo.pdf", new File("D:\\photo\\tt.jpg"));
        log.info("flag={}", flag);
    }

    @Test
    public void imageTest() {
        ImageUtils.imageDownloadWithUrl("", "http://ybj.zjzwfw.gov.cn:10540/openapiApp/download?key=bizamt/rdm/1592532943654pyI.pdf&pdfToImg=1", new File("D:\\photo\\tt.jpeg"));
    }

    @Test
    public void test() {
        Map<String, String> map = new HashMap<>();
        map.put("2", "22");
        log.info("size={}", map.size());
        String idCardType = "";
        switch (idCardType) {
            case "ID_CARD":
                map.put("text", "身份证");
                map.put("value", "31");
                break;
            default:
                break;
        }
    }
}
