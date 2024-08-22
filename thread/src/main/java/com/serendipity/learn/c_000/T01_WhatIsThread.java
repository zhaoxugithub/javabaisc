package com.serendipity.learn.c_000;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import static com.serendipity.learn.MyLogUtils.record;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/8/31 12:12 上午
 * FileName: T01_WhatIsThread
 * Description: com.java.thread.c_001
 */
@Slf4j
public class T01_WhatIsThread {
    // 这个对象可以被多个线程访问
    private static class T1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    record("exception= %s", e.getMessage());
                }
                record("thread name = %s", Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        // new T1().run();
        new T1().start();
        new T1().start();
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                record("exception= %s", e.getMessage());
            }
            record("main thread = %s", Thread.currentThread().getName());
        }
    }
}
