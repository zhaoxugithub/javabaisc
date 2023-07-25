package com.serendipity.learn.c_008;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/1 1:17 下午
 * FileName: T
 * Description: com.java.thread.c_008
 *
 * 面试题：模拟银行账户
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 这样行不行？
 *
 * 容易产生脏读问题（dirtyRead）

 */
public class T {

    String name;
    double balance;

    public synchronized void set(String name, double balance) {

        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }


    //这里的synchronized 必须要加，不然会引起脏读,两次获得的值不一样
    public synchronized double getBalance(String name) {
        return this.balance;
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(() -> t.set("zx", 222.99)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t.getBalance("zx"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t.getBalance("zx"));

    }
}
