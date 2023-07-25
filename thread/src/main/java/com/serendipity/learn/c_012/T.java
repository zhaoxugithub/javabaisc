package com.serendipity.learn.c_012;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/1 7:20 下午
 * FileName: T
 * Description: com.java.thread.c_012
 */
public class T {

    public void m() {
        // 线程私有
        int a = 10;
        for (int i = 0; i < 1000; i++) {
            a++;
        }
        System.out.println(Thread.currentThread().getName() + ":" + a);
    }

    public void m2() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        System.out.println(Thread.currentThread().getName() + ":" + Arrays.toString(list.toArray()));
    }

    // 线程共享
    List<Integer> list3 = new ArrayList<>();

    public void m3() {
        for (int i = 0; i < 100; i++) {
            list3.add(i);
        }
        System.out.println(Thread.currentThread().getName() + ":" + Arrays.toString(list3.toArray()));
    }

    // 线程私有
    List<Integer> list4 = null;

    public void m4() {
        list4 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list4.add(i);
        }
        System.out.println(Thread.currentThread().getName() + ":" + Arrays.toString(list4.toArray()));
    }


    public static void main(String[] args) {
        T t = new T();
        Thread t1 = new Thread(t::m2, "t1");
        t1.start();
        try {
            // main 线程阻塞
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m2, "t2").start();


        new Thread(t::m, "tm1").start();
        new Thread(t::m, "tm2").start();

        new Thread(t::m3, "tm3_1").start();
        new Thread(t::m3, "tm3_2").start();

        new Thread(t::m4, "tm4_1").start();
        new Thread(t::m4, "tm4_2").start();
    }
}
