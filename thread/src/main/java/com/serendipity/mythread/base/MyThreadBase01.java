package com.serendipity.mythread.base;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/5/8 0:14
 * FileName: ThreadBase01
 * Description: com.java.mythread
 * <p>
 * 线程不是安全的
 * <p>
 * 并发出现问题的根源:并发三要素
 * 可见性：一个线程对共享变量的修改，另外一个线程可以立刻看到
 * 原子性
 * 有序性
 * <p>
 * //而更深层次的区别在于sleep方法只是暂时让出CPU的执行权，并不释放锁。而wait方法则需要释放锁。
 */
public class MyThreadBase01 {

    private class ThreadUnsafeExample {
        private Long cnt = 1L;

        public void add() {
            cnt = cnt+1;
        }

        public Long get() {
            return cnt;
        }
    }

    @Test
    public void test() {
        final Integer ThreadSize = 1000;
        ThreadUnsafeExample threadUnsafeExample = new ThreadUnsafeExample();
        CountDownLatch countDownLatch = new CountDownLatch(ThreadSize);

        // create thread pool
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < ThreadSize; i++) {
            executorService.execute(() -> {
                threadUnsafeExample.add();
                countDownLatch.countDown();
            });
        }
        // 主线程阻塞,直到上述线程执行成功之后才会执行以下程序
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println(threadUnsafeExample.get());
    }
}
