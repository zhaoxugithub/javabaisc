package com.serendipity.learn.c_026;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: Administrator
 * Date: 2021/9/11 12:08
 * FileName: Main
 * Description: com.java.thread.c_026
 * <p>
 * 这段程序 实际上 就是 单线程跑的
 */
public class Main {

    private static int m = 0;
    private static MyLock lock = new MyLock();

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
//                lock.lock();
                for (int j = 0; j < 100; j++) {
                    m++;
                }
//                lock.unlock();
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(m);
    }
}
