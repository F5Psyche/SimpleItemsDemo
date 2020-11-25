package com.zhanghf;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:35 2020/4/23
 */
@Slf4j
public class PropertyUtilsTest {

    @Test
    public void test() throws Exception {
        int x = 1;
        int y = 2;
        Scanner input = new Scanner(System.in);
        int z = input.nextInt();
        if (x == z) {
            throw new SQLException("xx");
        } else {
            throw new SQLException("zz");
        }
    }
}
