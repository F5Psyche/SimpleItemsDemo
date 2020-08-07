package com.zhanghf;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:20 2020/7/30
 */
@Slf4j
public class bizPortal {

    @Test
    public void bizTargetSave() {
        String url = "http://xd-sxkxtoss.oss-cn-hangzhou-zwynet-d01-a.internet.cloud.zj.gov.cn/4/8/81ff2b7a-43f7-4934-93ac-3e2f7dd2cc7c.png";
        int index = url.lastIndexOf(".");
        String type = url.substring(index + 1);
        log.info("index={}, type={}", index, type);
    }
}
