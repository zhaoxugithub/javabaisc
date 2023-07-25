package com.serendipity.learn.c_027;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/11 12:32 下午
 * FileName: ThreadLocal2
 * Description: com.java.thread.c_027
 */
public class ThreadLocal2 {

    static ThreadLocal<Person> personThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + personThreadLocal.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //set方法在当前线程的一个map （ThreadLocalMap）对象中，map的key是ThreadLocal
            personThreadLocal.set(new Person());
            System.out.println(Thread.currentThread().getName() + ":" + personThreadLocal.get());
        }).start();
    }

}
