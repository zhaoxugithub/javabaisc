package com.serendipity.learn.c_024_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/5 5:23 下午
 * FileName: T01_WithoutVolatile_read_write
 * Description: com.java.thread.c_024_interview
 * <p>
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * <p>
 * 分析下面这个程序，能完成这个功能吗？
 * <p>
 * 第一个想法：读写锁（共享锁和拍他锁）
 */
public class T01_WithoutVolatile_read_write {
    private static List<Object> list = new ArrayList<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    public void add(Object obj) {
        try {
            writeLock.lock();
            list.add(obj);
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public int size() {
        int len = 0;
        try {
            readLock.lock();
            len = list.size();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return len;
    }

    public static void main(String[] args) {
        T01_WithoutVolatile_read_write wv = new T01_WithoutVolatile_read_write();
        new Thread(() -> {
            while (wv.size() < 5) {
            }
            System.out.println("5 end");
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("put" + i);
                wv.add(i);
            }
        }).start();
    }

}
