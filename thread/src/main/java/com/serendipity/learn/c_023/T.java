package com.serendipity.learn.c_023;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/5 5:12 下午
 * FileName: T
 * Description: com.java.thread.c_023
 * LockSupport是用来创建locks的基本线程阻塞基元，比如AQS中实现线程挂起的方法，就是park,对应唤醒就是unpark
 */
public class T {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    //阻塞线程执行,当前线程不会释放锁资源，Contition.wait()方法底层调用的是LockSupport.park()
                    LockSupport.park();
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());

        LockSupport.unpark(thread);
    }
}
