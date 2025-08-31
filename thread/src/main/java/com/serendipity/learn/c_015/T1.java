package com.serendipity.learn.c_015;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/3 9:47 下午
 * FileName: T1
 * Description: com.java.thread.c_015
 */
public class T1 {

    // 底层使用了无锁算法CAS
    private final Lock lock = new ReentrantLock();

    void m1() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    void m2() {
        try {
            boolean b = lock.tryLock(2, TimeUnit.SECONDS);
            System.out.println(b);
            lock.lock();
            System.out.println("m2...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        Thread thread1 = new Thread(t1::m1);
        Thread thread2 = new Thread(t1::m2);
        thread1.start();
        thread2.start();
        System.out.println(thread2.getState());
    }
}
