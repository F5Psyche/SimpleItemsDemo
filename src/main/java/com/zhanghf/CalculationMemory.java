package com.zhanghf;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhanghf.constant.CommonDMO.SHA_256_TYPE;
import static com.zhanghf.constant.CommonDMO.SHA_512_TYPE;

/**
 * 计算内存
 *
 * @author zhanghf
 * @version 1.0
 * @date 14:21 2020/3/12
 */
@Slf4j
public class CalculationMemory {

    public static void main(String[] args) {
        //有效内存
        long totalMemory = Runtime.getRuntime().totalMemory();

        //最大内存
        long maxMemory = Runtime.getRuntime().maxMemory();

        //你本地物理内存的1/16,误差在百分之十之内。

        log.info("最小内存:{}", totalMemory / (1024 * 1024) + "兆");

        //你本地物理内存的四分之一，误差在百分之十之内。
        log.info("最大内存:{}", maxMemory / (1024 * 1024) + "兆");

        log.info("InterfaceInfoDMO{" + "REQUEST_TIMEOUT_CONFIG='" + SHA_256_TYPE + "'\tLOCALHOST_EXTERNAL_SHARING_PLATFORM_URL='" + SHA_512_TYPE + '\'' + '}');

        boolean flag = test1(1) || test2(2);

        for (long i = 0L; i < 300L; i += 100) {
            System.out.println(i);
        }


    }


    public static boolean test1(Integer param) {
        log.info("uuid={}, param={}", 1, param);
        return 1 == param;
    }

    public static boolean test2(Integer param) {
        log.info("uuid={}, param={}", 2, param);
        return 2 == param;
    }
}
