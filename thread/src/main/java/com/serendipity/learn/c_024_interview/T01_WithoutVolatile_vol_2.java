package com.serendipity.learn.c_024_interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/5 6:01 下午
 * FileName: T01_WithoutVolatile_vol_2
 * Description: com.java.thread.c_024_interview
 */
public class T01_WithoutVolatile_vol_2 {

    private volatile List<Object> list = Collections.synchronizedList(new ArrayList<>());

    public void add(Object obj) {
        list.add(obj);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        T01_WithoutVolatile_read_write wv = new T01_WithoutVolatile_read_write();

        new Thread(() -> {
            while (wv.size() < 5) {
            }
            System.out.println("5 end");
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("put" + i);
                wv.add(i);
            }
        }).start();
    }
}
