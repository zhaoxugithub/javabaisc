package com.serendipity.learn.c_022;

import java.util.concurrent.Exchanger;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/5 4:22 下午
 * FileName: T
 * Description: com.java.thread.c_022
 */
public class T {

    //只适用于两个线程之间交换数据
    private static Exchanger<String> exchanger = new Exchanger();

    public static void main(String[] args) {
        new Thread(() -> {
            String s = "T1";
            try {
                //如果只有一个新线程启动，当前线程阻塞
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "t1").start();

        new Thread(() -> {
            String s = "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "t2").start();
    }
}
