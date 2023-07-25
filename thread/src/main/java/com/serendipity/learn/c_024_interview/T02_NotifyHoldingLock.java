package com.serendipity.learn.c_024_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/5 6:15 下午
 * FileName: T02_NotifyHoldingLock
 * Description: com.java.thread.c_024_interview
 */
public class T02_NotifyHoldingLock {
    private volatile List<Object> list = new ArrayList<>();

    public void add(Object obj) {
        list.add(obj);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        T02_NotifyHoldingLock holdingLock = new T02_NotifyHoldingLock();
        Object obj = new Object();

        new Thread(() -> {
            synchronized (obj) {
                System.out.println("t2 启动");
                if (holdingLock.size() != 5) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("5 end");
                // 让线程继续执行
                obj.notify();
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            synchronized (obj) {
                System.out.println("t1 启动");
                for (int i = 0; i < 10; i++) {
                    holdingLock.add(new Object());
                    System.out.println("add " + i);
                    if (i == 5) {
                        // 唤起t2线程继续执行
                        obj.notify();
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();
    }
}

