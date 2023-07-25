package com.serendipity.learn.c_030;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/12 9:32 下午
 * FileName: ConcurrentMap
 * Description: com.java.thread.c_030
 */
public class ConcurrentMap {

    public static void main(String[] args) {
        /*
        至此相信你已经对 ConcurrentHashMap 可以支持并发的原理有了大致的了解，
        它主要依靠了 volatile 和 CAS 操作，再加上 synchronized 即实现了一个支持并发的哈希表。
         */
        Map<String, String> map = new ConcurrentHashMap<>();


        //高并发并且排序,跳表结构
//        Map<Object, Object> map = new ConcurrentSkipListMap<>();
        Random r = new Random();
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    map.put(Thread.currentThread().getName() + "----" + j, "b" + r.nextInt(100000));
                }
                latch.countDown();
            });
        }
        Arrays.asList(threads).forEach(t -> t.start());

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        map.entrySet().stream().limit(100).forEach(System.out::println);

        System.out.println(System.currentTimeMillis() - start);
        System.out.println(map.size());
    }
}
