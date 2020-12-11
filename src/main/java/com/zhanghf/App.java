package com.zhanghf;

import com.zhanghf.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author zhanghf
 * @version 1.0
 * @date 21:20 2020/3/6
 */
@Slf4j
public class App {

    private static final int ONE = 1;
    private static final int TWO = 2;

    private static String step1(int x, int y) {
        try {
            if (x == ONE) {
                return step2(y);
            } else {
                return step1(1, y);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private static String step2(int y) {
        if (y == TWO) {
            log.info("if");
            return "ok";
        } else {
            log.info("else");
            throw new RuntimeException("exception");
        }
    }

    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            int x = input.nextInt();
            int y = input.nextInt();
            String z = step1(x, y);
            log.info("x={}, y={}, z={}", x, y, z);
        } catch (Exception e) {
            log.error("errMsg={}", CommonUtils.getStackTraceString(e));
        }
    }


}
