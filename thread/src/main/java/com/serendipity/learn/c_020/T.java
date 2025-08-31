package com.serendipity.learn.c_020;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/5 10:37 上午
 * FileName: T
 * Description: com.java.thread.c_020
 * <p>
 * 读写锁（共享锁和排他锁）
 */
/*
    读读 不互斥
    读写 互斥
    写写 互斥
 */
public class T {
    private static Lock reLock = new ReentrantLock();
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();
    private static int v;

    public static void read(Lock lock) {
        try {
            Thread.sleep(10);
            lock.lock();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + ":read over");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int value) {
        try {
            lock.lock();
            Thread.sleep(10000);
            v = value;
            System.out.println(Thread.currentThread().getName() + ":write over");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                read(readLock);
            }).start();
        }
        new Thread(() -> write(writeLock, 10)).start();
        /*
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                write(writeLock, finalI);
            }).start();
        }*/
    }
}
