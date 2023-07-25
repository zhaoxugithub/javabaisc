package com.serendipity.learn.c_001;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/22 6:16 下午
 * FileName: ThreadUnsafeExample
 * Description: com.java.thread.c_001
 */
public class ThreadUnsafeExample {
    private volatile static int cnt = 0;

    public /*synchronized*/ void add() {
        for (int i = 0; i < 1000; i++) {
            cnt++;
        }

    }

    public int get() {
        return cnt;
    }

    public static void test1() {
        ThreadUnsafeExample threadUnsafeExample = new ThreadUnsafeExample();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(threadUnsafeExample::add);
        }

        Arrays.stream(threads).forEach(Thread::start);
        Arrays.stream(threads).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(threadUnsafeExample.get());
    }

    public static void est() {

        ThreadUnsafeExample threadUnsafeExample = new ThreadUnsafeExample();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(threadUnsafeExample::add);
        }
        CountDownLatch latch = new CountDownLatch(threads.length);

        Arrays.stream(threads).forEach(t -> {
            t.start();
            latch.countDown();
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadUnsafeExample.get());
    }

    public void test() throws InterruptedException {
        final int threadSize = 1000;
        ThreadUnsafeExample example = new ThreadUnsafeExample();
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                example.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(example.get());
    }

    public static void main(String[] args) {
        est();
        // test1();
    }
}
