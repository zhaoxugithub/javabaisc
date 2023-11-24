package com.serendipity.learn.c_000;

import lombok.extern.slf4j.Slf4j;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/8/31 12:12 上午
 * FileName: T02_HowToCreateThread
 * Description: com.java.thread.c_001
 */
@Slf4j
@SuppressWarnings("all")
public class T02_HowToCreateThread {
    public static class MyThread extends Thread {
        @Override
        public void run() {
            log.info("MyThread start ...");
        }
    }

    public static class MyRun implements Runnable {
        @Override
        public void run() {
            log.info("MyRun start ....");
        }
    }

    public static void method() {
        log.info("thread main={}", Thread.currentThread().getName());
    }

    // new Thread(方法 / 实现线程runnable,继承Thread 的类   )
    public static void main(String[] args) {
        // 第一种方法
        new MyThread().start();
        // 第二种方法
        new Thread(new MyRun()).start();
        // 第三种方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("myRun start ........");
            }
        }).start();
        // 第四种方法
        new Thread(() -> log.info("my start...")).start();
        new Thread(T02_HowToCreateThread::method).start();
    }
}
