package com.serendipity.learn.c_031;

import java.util.concurrent.locks.LockSupport;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/4 16:53
 * FileName: LockSupport
 * Description: com.java.thread.c_031
 * <p>
 * 给定两个字符串：1234567,ABCDEFG
 * 运用多线程的方式输出1A2B3C4D..
 * <p>
 * LockSupport 的好处是可以唤醒指定的线程
 */
public class T001_LockSupportTest {
    static Thread t2 = null;
    static Thread t1 = null;

    public static void main(String[] args) {
        String nums = "1234567";
        String str = "ABCDEFG";
        char[] numsChar = nums.toCharArray();
        char[] chars = str.toCharArray();
        t1 = new Thread(() -> {
            for (char c : numsChar) {
                System.out.print(c);
                // 先把自己阻塞
                LockSupport.park();
                // 唤醒具体的某一个线程
                LockSupport.unpark(t2);
            }
        });
        t2 = new Thread(() -> {
            for (char aChar : chars) {
                System.out.print(aChar);
                // 唤醒t1
                LockSupport.unpark(t1);
                // 自己阻塞
                LockSupport.park();
            }
        });
        t1.start();
        t2.start();
    }
}
