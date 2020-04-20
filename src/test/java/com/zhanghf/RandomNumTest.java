package com.zhanghf;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:36 2020/3/31
 */
@Slf4j
public class RandomNumTest {

    @Test
    public void tenRandomNum() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int a = (int) ((Math.random() * 9 + 1) * 100000);

        log.info("a={}", a);
        Long sysdate = System.currentTimeMillis();
        Long mouldId = Long.valueOf(sysdate + "" + (int) ((Math.random() * 9 + 1) * 100));
        log.info("mouldId={}, length={}, random={}", mouldId, mouldId.toString().length(), new Random().nextInt());


        long startTime = System.currentTimeMillis();
        long endTime = startTime + 7776000000L;
        log.info("endTime={}, startTime={}", dateFormat.format(new Date(endTime)), dateFormat.format(new Date(startTime)));

    }
}

