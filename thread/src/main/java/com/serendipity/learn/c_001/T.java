package com.serendipity.learn.c_001;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/8/31 1:10 上午
 * FileName: T
 * Description: com.java.thread.c_001
 */
public class T {
    private Object obj = new Object();
    private int count = 10;

    public void m() {
        synchronized (obj) {
            count--;
            System.out.println(Thread.currentThread().getName() + "count" + count);
        }
    }
}
