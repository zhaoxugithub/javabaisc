package com.serendipity.learn.c_003;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/9 9:49 下午
 * FileName: T1
 * Description: com.java.thread.c_003
 */
@SuppressWarnings("all")
public class T1 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                Thread.yield();
                // 响应中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("青秧线程被中断，程序退出。");
                    return;
                }
            }
        });
        thread.start();

        // 执行这个方法是让当前线程和thread进行通信
        // 由主线程去触发 thread线程的中断，这个时候Thread.currentThread().isInterrupted()才能能够判断出是否发生了中断
        thread.interrupt();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("stop 1->  ----" + Thread.currentThread().isInterrupted());
        // 表示的调用thread.interrupted方法的这个线程是否是中断状态，而不是thread这个线程
        /*
            Thread.currentThread().isInterrupted()) 与   thread.interrupted() 表示同一个线程
         */
        // 表示的是main线程是否被中断了
        System.out.println("stop 1->" + thread.interrupted());

        // 表示的是thread线程是否被中断了
        System.out.println("thread->" + thread.isInterrupted());
    }
}
