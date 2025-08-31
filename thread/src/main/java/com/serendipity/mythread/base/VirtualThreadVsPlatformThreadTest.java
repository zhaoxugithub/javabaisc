package com.serendipity.mythread.base;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadVsPlatformThreadTest {
    // 测试参数
    private static final int THREAD_COUNT = 10000;
    private static final int IO_SIMULATION_MS = 100;
    private static final int CPU_INTENSIVE_ITERATIONS = 1000000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 虚拟线程 vs 平台线程性能测试 ===\n");
        // 测试1: I/O密集型任务
        System.out.println("测试1: I/O密集型任务 (模拟网络请求)");
        testIOIntensiveTask();
        System.out.println("\n" + "=".repeat(50) + "\n");
        // 测试2: CPU密集型任务
        System.out.println("测试2: CPU密集型任务");
        testCPUIntensiveTask();
        System.out.println("\n" + "=".repeat(50) + "\n");
        // 测试3: 混合任务
        System.out.println("测试3: 混合任务 (CPU + I/O)");
        testMixedTask();
    }

    // I/O密集型任务测试
    private static void testIOIntensiveTask() throws InterruptedException {
        System.out.println("线程数量: " + THREAD_COUNT);
        System.out.println("I/O等待时间: " + IO_SIMULATION_MS + "ms\n");
        // 平台线程测试
        long platformThreadTime = measureTime(() -> {
            try (ExecutorService executor = Executors.newCachedThreadPool()) {
                CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
                for (int i = 0; i < THREAD_COUNT; i++) {
                    executor.submit(() -> {
                        try {
                            // 模拟I/O操作
                            Thread.sleep(IO_SIMULATION_MS);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } finally {
                            latch.countDown();
                        }
                    });
                }
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // 虚拟线程测试
        long virtualThreadTime = measureTime(() -> {
            try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
                CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
                for (int i = 0; i < THREAD_COUNT; i++) {
                    executor.submit(() -> {
                        try {
                            // 模拟I/O操作
                            Thread.sleep(IO_SIMULATION_MS);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } finally {
                            latch.countDown();
                        }
                    });
                }
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        printResults("I/O密集型任务", platformThreadTime, virtualThreadTime);
    }

    // CPU密集型任务测试
    private static void testCPUIntensiveTask() throws InterruptedException {
        System.out.println("线程数量: " + THREAD_COUNT);
        System.out.println("CPU计算迭代次数: " + CPU_INTENSIVE_ITERATIONS + "\n");
        // 平台线程测试
        long platformThreadTime = measureTime(() -> {
            try (ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {
                CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
                for (int i = 0; i < THREAD_COUNT; i++) {
                    executor.submit(() -> {
                        try {
                            // CPU密集型计算
                            cpuIntensiveTask();
                        } finally {
                            latch.countDown();
                        }
                    });
                }
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // 虚拟线程测试
        long virtualThreadTime = measureTime(() -> {
            try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
                CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

                for (int i = 0; i < THREAD_COUNT; i++) {
                    executor.submit(() -> {
                        try {
                            // CPU密集型计算
                            cpuIntensiveTask();
                        } finally {
                            latch.countDown();
                        }
                    });
                }

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        printResults("CPU密集型任务", platformThreadTime, virtualThreadTime);
    }

    // 混合任务测试
    private static void testMixedTask() throws InterruptedException {
        System.out.println("线程数量: " + THREAD_COUNT);
        System.out.println("混合任务: CPU计算 + I/O等待\n");

        // 平台线程测试
        long platformThreadTime = measureTime(() -> {
            try (ExecutorService executor = Executors.newCachedThreadPool()) {
                CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

                for (int i = 0; i < THREAD_COUNT; i++) {
                    executor.submit(() -> {
                        try {
                            // 混合任务：先CPU计算，再I/O等待
                            cpuIntensiveTask();
                            Thread.sleep(10); // 短暂I/O等待
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } finally {
                            latch.countDown();
                        }
                    });
                }

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // 虚拟线程测试
        long virtualThreadTime = measureTime(() -> {
            try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
                CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

                for (int i = 0; i < THREAD_COUNT; i++) {
                    executor.submit(() -> {
                        try {
                            // 混合任务：先CPU计算，再I/O等待
                            cpuIntensiveTask();
                            Thread.sleep(10); // 短暂I/O等待
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } finally {
                            latch.countDown();
                        }
                    });
                }

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        printResults("混合任务", platformThreadTime, virtualThreadTime);
    }

    // CPU密集型计算任务
    private static void cpuIntensiveTask() {
        long sum = 0;
        for (int i = 0; i < CPU_INTENSIVE_ITERATIONS; i++) {
            sum += Math.sqrt(i) * Math.sin(i);
        }
        // 防止JIT优化掉计算
        if (sum == Long.MAX_VALUE) {
            System.out.println("Impossible");
        }
    }

    // 测量执行时间的工具方法
    private static long measureTime(Runnable task) {
        Instant start = Instant.now();
        task.run();
        Instant end = Instant.now();
        return Duration.between(start, end).toMillis();
    }

    // 打印测试结果
    private static void printResults(String taskType, long platformTime, long virtualTime) {
        System.out.printf("%-15s | 平台线程: %6d ms | 虚拟线程: %6d ms\n", taskType, platformTime, virtualTime);

        double improvement = ((double) platformTime - virtualTime) / platformTime * 100;
        if (improvement > 0) {
            System.out.printf("虚拟线程性能提升: %.2f%%\n", improvement);
        } else {
            System.out.printf("平台线程性能更好: %.2f%%\n", -improvement);
        }

        System.out.printf("性能比率: %.2f:1 (平台线程:虚拟线程)\n", (double) platformTime / virtualTime);

        // 内存使用情况
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.printf("当前内存使用: %.2f MB\n", usedMemory / 1024.0 / 1024.0);
    }
}