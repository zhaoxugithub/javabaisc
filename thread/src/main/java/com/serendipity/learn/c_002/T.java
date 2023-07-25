package com.serendipity.learn.c_002;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/1 12:46 下午
 * FileName: T
 * Description: com.java.thread.c_002
 */
public class T {

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + "m1   start ..");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void m2() {
        System.out.println(Thread.currentThread().getName() + "m2   start...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        T t = new T();
        /*new Thread(() -> t.m1(), "t1").start();
        new Thread(() -> t.m2(), "t2").start();*/
        new Thread(t::m1, "t1").start();
        new Thread(t::m2, "t2").start();
    }
}
