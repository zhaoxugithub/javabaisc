package com.serendipity.learn.c_024_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/5 5:49 下午
 * FileName: T01_WithoutVolatile_syn
 * Description: com.java.thread.c_024_interview
 */
public class T01_WithoutVolatile_syn {
    private static final List<Object> list = new ArrayList<>();

    public synchronized void add(Object obj) {
        try {
            System.out.println("put" + obj);
            list.add(obj);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized int size() {
        int len = 0;
        try {
            len = list.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return len;
    }

    public static void main(String[] args) {
        T01_WithoutVolatile_syn wv = new T01_WithoutVolatile_syn();
        new Thread(() -> {
            // 每执行一次都会释放锁
            while (wv.size() < 5) {
            }
            System.out.println(Thread.currentThread()
                                     .getName() + "5 end");
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++, wv.add(i)) ;
        }).start();
    }
}

