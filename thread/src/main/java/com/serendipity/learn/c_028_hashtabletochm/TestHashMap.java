package com.serendipity.learn.c_028_hashtabletochm;

import java.util.HashMap;
import java.util.UUID;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: Administrator
 * Date: 2021/9/11 15:27
 * FileName: TestHashMap
 * Description: com.java.thread.c_028_hashtabletochm
 */
public class TestHashMap {


    private static final int count = 1000000;
    private static final int thread_count = 100;

    private static HashMap<UUID, UUID> hashMap = new HashMap<>();
    private static UUID[] keys = new UUID[count];
    private static UUID[] values = new UUID[count];

    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {

        private int start;
        int gap = count / thread_count;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = 0; i < start + gap; i++) {
                hashMap.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        MyThread[] threads = new MyThread[thread_count];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(i * (count / thread_count));
        }

        for (MyThread thread : threads) {
            thread.start();
        }

        for (MyThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(System.currentTimeMillis() - start);
        System.out.println(hashMap.size());


        System.out.println("------------------------------------");


    }
}
