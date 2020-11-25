package com.zhanghf;

import com.alibaba.druid.filter.config.ConfigTools;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author zhanghf
 * @version 1.0
 * @date 9:43 2020/9/4
 */
@Slf4j
public class DruidPasswordTest {

    private final static String PASSWORD = "BizPortal38";

    private final static String ENCRYPTPASSWORD = "gGQ3g9ZnOHGVics9xwUNTv3dk/G8qcV/ufRDlvh9ZgLUAp5KVbealaZ3Sxg9AUYcIkAKIa4WlbfC/Q/bvC7fTw==";

    @Test
    public void encryptPassword() {
        try {
            String osName = System.getProperty("os.name");
            String encryptPassword = ConfigTools.encrypt(PASSWORD);

            log.info("osName={}, encryptPassword={}", osName, encryptPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decryptPassword() {
        try {
            String decryptPassword = ConfigTools.decrypt(ENCRYPTPASSWORD);
            log.info("decryptPassword={}", decryptPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        String areaId = "330199";
        System.out.println(areaId.substring(0, 4) + ":" + areaId.substring(4));
    }
}
