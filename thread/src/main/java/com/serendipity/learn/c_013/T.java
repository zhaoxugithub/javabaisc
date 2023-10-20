package com.serendipity.learn.c_013;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/3 5:54 下午
 * FileName: T
 * Description: com.java.thread.c_013
 */
public class T {
    // 无法保证原子性
    private volatile int count = 0;

    public void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
        System.out.println(Thread.currentThread().getName() + ":" + count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 3; i++) {
            new Thread(t::m, "t" + i).start();
        }
//        new Thread(t::m, "t2").start();
    }
}
