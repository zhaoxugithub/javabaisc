package com.serendipity.java21.virtualThread;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class VirtualThread01 {

    public static void main(String[] args) {
        // 平台线程
        long start1 = System.currentTimeMillis();
        testPlatformThread();
        System.out.println(STR."cost time:\{System.currentTimeMillis() - start1}");

        long start2 = System.currentTimeMillis();
        // 虚拟线程
        testVirtualThread();
        System.out.println(STR."cost time:\{System.currentTimeMillis() - start2}");
    }

    // 平台线程
    public static void testPlatformThread() {
        // ExecutorService 实现了AutoCloseable 接口，可以自动关闭
        try (ExecutorService executor = Executors.newCachedThreadPool()) {
            // 向executor提交1000000个任务
            IntStream.range(0, 1000000).forEach(_ -> executor.submit(() -> {
                try {
                    Thread.sleep(Duration.ofSeconds(1));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 虚拟线程
    public static void testVirtualThread() {
        // ExecutorService 实现了AutoCloseable 接口，可以自动关闭
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            // 向executor提交1000000个任务
            IntStream.range(0, 1000000).forEach(_ -> executor.submit(() -> {
                try {
                    Thread.sleep(Duration.ofSeconds(1));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
