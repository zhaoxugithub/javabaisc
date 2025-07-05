package com.serendipity.java21.virtualThread;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPerformanceComparison {
    public static void main(String[] args) throws Exception {
        // 测试不同规模的任务
        int[] taskCounts = {1000, 10000, 100000};
        int ioWaitMs = 50; // 模拟I/O等待时间(毫秒)
        System.out.println("Java 21 虚拟线程与平台线程性能对比");
        System.out.println("================================\n");
        for (int taskCount : taskCounts) {
            System.out.println("\n## 测试 " + taskCount + " 个任务 (每个任务I/O等待: " + ioWaitMs + "ms) ##");
            // 测试平台线程
            int platformThreads = Math.min(200, taskCount / 10); // 适当的线程池大小
            System.out.println("\n1. 平台线程 (线程池大小: " + platformThreads + ")");
            try (ExecutorService executor = Executors.newFixedThreadPool(platformThreads)) {
                runTest(executor, taskCount, ioWaitMs);
            }
            // 测试虚拟线程
            System.out.println("\n2. 虚拟线程");
            try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
                runTest(executor, taskCount, ioWaitMs);
            }
        }
    }

    private static void runTest(ExecutorService executor, int taskCount, int ioWaitMs) throws Exception {
        AtomicInteger completed = new AtomicInteger(0);
        Instant start = Instant.now();
        // 提交所有任务
        for (int i = 0; i < taskCount; i++) {
            executor.submit(() -> {
                try {
                    // 模拟I/O操作
                    Thread.sleep(ioWaitMs);
                    completed.incrementAndGet();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        // 关闭执行器并等待任务完成
        executor.shutdown();
        // 这行代码的作用是等待所有已提交的任务执行完成或超时。
        // executor.awaitTermination(5, TimeUnit.MINUTES);
        // 会阻塞当前线程，最多等待 5 分钟，直到线程池中的任务全部执行完毕或超时返回。
        // 返回值 terminated 表示线程池是否在规定时间内终止。
        boolean terminated = executor.awaitTermination(5, TimeUnit.MINUTES);
        Duration duration = Duration.between(start, Instant.now());
        System.out.println("完成任务数: " + completed.get() + "/" + taskCount);
        System.out.println("总耗时: " + duration.toMillis() + " 毫秒");
        if (duration.toMillis() > 0) {
            double throughput = taskCount * 1000.0 / duration.toMillis();
            System.out.println("吞吐量: " + String.format("%.2f", throughput) + " 任务/秒");
        }
        if (!terminated) {
            System.out.println("警告: 执行器未能在超时时间内完成所有任务");
        }
    }
}
