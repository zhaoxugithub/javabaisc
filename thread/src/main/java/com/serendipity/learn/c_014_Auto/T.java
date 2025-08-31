package com.serendipity.learn.c_014_Auto;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/3 7:24 下午
 * FileName: T
 * Description: com.java.thread.c_014_Auto
 */
public class T {

    // 底层实现是CAS自旋锁
    private final AtomicInteger integer = new AtomicInteger(0);
    private int count = 0;

    public void m() {
        for (int i = 0; i < 1000000; i++) {
            integer.incrementAndGet();
        }
        System.out.println(Thread.currentThread().getName() + ":" + integer);
    }

    public synchronized void m2() {
        for (int i = 0; i < 1000000; i++) {
            count++;
        }
        System.out.println(Thread.currentThread().getName() + ":" + count);
    }

    public static void main(String[] args) {
        T t = new T();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            new Thread(t::m, "t" + i).start();
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
