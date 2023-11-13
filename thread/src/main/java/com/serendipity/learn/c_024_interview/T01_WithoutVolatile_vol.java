package com.serendipity.learn.c_024_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/5 5:53 下午
 * FileName: T01_WithoutVolatile_vol
 * Description: com.java.thread.c_024_interview
 */


// 可以得
public class T01_WithoutVolatile_vol {
    private volatile List<Object> list = new ArrayList<>();

    public void add(Object obj) {
        try {
            System.out.println("put" + obj);
            list.add(obj);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int size() {
        int len = 0;
        try {
            len = list.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return len;
    }

    public static void main(String[] args) {
        T01_WithoutVolatile_vol wv = new T01_WithoutVolatile_vol();
        new Thread(() -> {
            while (wv.size() < 20) {
            }
            System.out.println("5 end");
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++, wv.add(i)) ;
        }).start();
    }
}
