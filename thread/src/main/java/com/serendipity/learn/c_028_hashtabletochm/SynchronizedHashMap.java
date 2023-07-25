package com.serendipity.learn.c_028_hashtabletochm;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: Administrator
 * Date: 2021/9/12 12:51
 * FileName: SynchronizedHashMap
 * Description: com.java.thread.c_028_hashtabletochm
 * 429
 * 1000000
 * 48318
 *
 * 测试 synchronizedMap 在多线程下的读写性能
 *
 * synchronizedMap和Hashtable的比较：
 *         1.两者读写性能差不多，synchronizedMap 的锁粒度要小一些，锁是加在方法内部，hashtable的锁是加在方法上，
 */
public class SynchronizedHashMap {

    static Map<UUID, UUID> m = Collections.synchronizedMap(new HashMap<UUID, UUID>());

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
        int gap = count/THREAD_COUNT;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for(int i=start; i<start+gap; i++) {
                m.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];

        for(int i=0; i<threads.length; i++) {
            threads[i] =
                    new MyThread(i * (count/THREAD_COUNT));
        }
        for(Thread t : threads) {
            t.start();
        }
        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        //245
        System.out.println(end - start);
        System.out.println(m.size());
        //-----------------------------------
        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 10000000; j++) {
                    m.get(keys[10]);
                }
            });
        }
        for(Thread t : threads) {
            t.start();
        }
        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();
        //30232
        System.out.println(end - start);
    }
}
