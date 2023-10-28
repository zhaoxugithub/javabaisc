package com.serendipity.learn.c_018;

import java.util.concurrent.CountDownLatch;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: Administrator
 * Date: 2021/9/4 16:41
 * FileName: T
 * Description: com.java.thread.c_018
 */
public class T {

    public static void main(String[] args) {
        T t = new T();
        t.usingCountDownLatch();
//        t.usingJoin();
    }
    public void usingCountDownLatch() {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length - 1; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result += j;
                }
                latch.countDown();
            });
        }
        for (int i = 0; i < threads.length - 1; i++) {
            threads[i].start();
        }
        try {
            //  latch.countDown(); 门栓变成0的时候才会执行后面的代码
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("usingCountDownLatch end");
    }

    private void usingJoin() {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) result += j;
            });
        }
        for (Thread value : threads) {
            value.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end join");
    }
}
