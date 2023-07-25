package com.serendipity.learn.c_016;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/4 11:10 上午
 * FileName: T
 * Description: com.java.thread.c_016
 */
public class T {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (Exception e) {
                System.out.println("t1 interrupted");
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
//                lock.lock();
                boolean b = lock.tryLock();
                if (!b) {
                    System.out.println("fdfsafsadfdf");
                    lock.lockInterruptibly();
                } else {
                    lock.lock();
                }
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("t2 interrupted!");
            } finally {
                if(lock.tryLock()){
                    lock.unlock();
                }
            }
        });

        t2.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();

    }
}
