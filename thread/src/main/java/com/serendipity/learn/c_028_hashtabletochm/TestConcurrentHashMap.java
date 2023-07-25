package com.serendipity.learn.c_028_hashtabletochm;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: Administrator
 * Date: 2021/9/12 17:11
 * FileName: TestConcurrentHashMap
 * Description: com.java.thread.c_028_hashtabletochm
 * <p>
 * 底层采用CAS无锁，自旋锁的次数是10次
 * <p>
 * <p>
 * <p>
 * 测试 ConcurrentHashMap: 相对比HashTable和SynchronizedHashMap来说读写性能更加快，底层采集它主要依靠了 volatile 和 CAS 操作，再加上 synchronized 即实现了一个支持并发的哈希表，主要是提升在读上面
 * <p>
 * <p>
 * synchronized锁升级过程：
 * 一开始处于无锁状态，只有一个线程在执行，锁对象中的无锁标志位为01，
 * 当多次都是同一个线程去访问临界资源的时候，就是进入到偏向锁状态，锁对象对象头markword中会在偏向锁的锁标志位中记录是否是偏向锁，
 * 并且会记录偏向的线程ID,以后该线程在进入和退出同步块时不需要花费CAS操作来加锁和解锁
 * <p>
 * 同步队列：进入Synchronized方法块(同步方法)时竞争锁的时候失败，则进入同步队列
 * 等待队列：比如线程调用了wait()方法，线程则进入等待队列，等待被唤醒再进入同步队列
 * <p>
 * 当线程自旋超过10次，就会进入到同步队列里面。直到抢到临界资源
 */
public class TestConcurrentHashMap {

    static Map<UUID, UUID> m = new ConcurrentHashMap<>();

    static int count = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static final int THREAD_COUNT = Constants.THREAD_COUNT;

    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        int start;
        int gap = count / THREAD_COUNT;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start + gap; i++) {
                m.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < threads.length; i++) {
            threads[i] =
                    new MyThread(i * (count / THREAD_COUNT));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);

        System.out.println(m.size());

        //-----------------------------------

        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000000; j++) {
                    m.get(keys[10]);
                }
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
