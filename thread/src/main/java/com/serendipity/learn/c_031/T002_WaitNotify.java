package com.serendipity.learn.c_031;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/4 17:06
 * FileName: T002_wait_notify
 * Description: com.java.thread.c_031
 * <p>
 * 记住：notify、wait 一定要配合synchronized 使用
 */
@SuppressWarnings("all")
public class T002_WaitNotify {
    static Thread t2 = null;
    static Thread t1 = null;
    static final Object obj = new Object();

    public static void main(String[] args) {
        String nums = "1234567";
        String str = "ABCDEFG";
        char[] numsChar = nums.toCharArray();
        char[] chars = str.toCharArray();

        new Thread(() -> {
            synchronized (obj) {
                for (char c : numsChar) {
                    System.out.print(c);
                    try {
                        // 唤醒其他线程
                        obj.notify();
                        // 阻塞当前线程
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //唤醒线程,必须加上这个，不然会终止不了线程
                obj.notify();
            }
        }).start();

        new Thread(() -> {
            synchronized (obj) {
                for (char c : chars) {
                    System.out.print(c);
                    try {
                        // 唤醒其他线程
                        obj.notify();
                        // 阻塞当前线程
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                obj.notify();
            }

        }).start();
        System.out.println("over");
    }

}
