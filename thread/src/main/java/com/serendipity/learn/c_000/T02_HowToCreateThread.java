package com.serendipity.learn.c_000;

import lombok.extern.slf4j.Slf4j;

import static com.serendipity.learn.MyLogUtils.record;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/8/31 12:12 上午
 * FileName: T02_HowToCreateThread
 * Description: com.java.thread.c_001
 * <p>
 * Runnable接口将任务逻辑与线程分离，提供更好的代码组织和可复用性，而Thread类则提供了更多的线程控制和状态管理功能。
 */
@Slf4j
@SuppressWarnings("all")
public class T02_HowToCreateThread {
    /*
        Thread: 一个Thread实例代表一个线程，线程之间资源不共享, 一个线程独享
        Runnable: 一个Runnable实例代表一个任务，多个线程可以共享同一个Runnable实例，线程之间资源共享
        可以简单理解：  runnable 任务逻辑
                      Thread   线程
     */
    public static class MyThread extends Thread {
        @Override
        public void run() {
            record("MyThread start...");
        }
    }

    public static class MyRun implements Runnable {
        int num = 0;

        @Override
        public void run() {
            log.info(Thread.currentThread() + ":MyRun start ....");
            for (int i = 0; i < 10000; i++) {
                num++;
            }
        }
    }

    public static void method() {
        log.info("thread main={}", Thread.currentThread().getName());
    }


    public static void test() throws InterruptedException {
        // 通过线程池创建线程
        MyRun myRun = new MyRun();
        Thread t1 = new Thread(myRun);
        Thread t2 = new Thread(myRun);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        record(myRun.num);
    }

    // new Thread(方法 / 实现线程runnable,继承Thread 的类   )
    public static void main(String[] args) throws InterruptedException {
        // 第一种方法
        new MyThread().start();
        // 第二种方法
        new Thread(new MyRun()).start();
        // 第三种方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                record("myRun start ........");
            }
        }).start();
        // 第四种方法
        new Thread(() -> record("my start...")).start();
        new Thread(T02_HowToCreateThread::method).start();
    }
}
