package com.serendipity.java21.virtualThread;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadPerformanceTest {
    // 任务数量
    private static final int TASKS = 10000;

    // 任务内容（模拟IO操作，可以调整为其它耗时任务）
    private static void task() {
        try {
            Thread.sleep(10); // 模拟耗时操作
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // 使用平台线程
    public static void testPlatformThreads() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(TASKS);
        List<Thread> threads = new ArrayList<>(TASKS);
        Instant start = Instant.now();
        for (int i = 0; i < TASKS; i++) {
            Thread t = new Thread(() -> {
                task();
                latch.countDown();
            });
            threads.add(t);
            t.start();
        }
        latch.await();
        Instant end = Instant.now();
        System.out.println("平台线程耗时: " + Duration.between(start, end).toMillis() + " ms");
    }

    // 使用虚拟线程
    public static void testVirtualThreads() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(TASKS);
        List<Thread> threads = new ArrayList<>(TASKS);
        Instant start = Instant.now();
        for (int i = 0; i < TASKS; i++) {
            Thread t = Thread.ofVirtual().unstarted(() -> {
                task();
                latch.countDown();
            });
            threads.add(t);
            t.start();
        }
        latch.await();
        Instant end = Instant.now();
        System.out.println("虚拟线程耗时: " + Duration.between(start, end).toMillis() + " ms");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("==== 测试开始 ====");
        System.out.println("任务数: " + TASKS + ", 每个线程休眠10ms");
        // 先测试平台线程
        testPlatformThreads();
        // 再测试虚拟线程
        testVirtualThreads();
    }
}
