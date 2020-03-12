package com.zhanghf;

/**
 * 计算内存
 *
 * @author zhanghf
 * @version 1.0
 * @date 14:21 2020/3/12
 */
public class CalculationMemory {
    public static void main(String[] args) {
        //有效内存
        long totalMemory = Runtime.getRuntime().totalMemory();

        //最大内存
        long maxMemory = Runtime.getRuntime().maxMemory();

        //你本地物理内存的1/16,误差在百分之十之内。
        System.out.println("最小内存" + totalMemory / (1024 * 1024) + "兆");

        //你本地物理内存的四分之一，误差在百分之十之内。
        System.out.println("最大内存" + maxMemory / (1024 * 1024) + "兆");
    }
}
