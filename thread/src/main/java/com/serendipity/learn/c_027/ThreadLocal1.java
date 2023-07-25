package com.serendipity.learn.c_027;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: Administrator
 * Date: 2021/9/11 12:24
 * FileName: ThreadLocal1
 * Description: com.java.thread.c_027
 */
public class ThreadLocal1 {

    private static Person person = new Person();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(person.name);
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            person.name = "lisi";
        }).start();
    }
}

class Person {
    public String name = "zhangsan";
}

