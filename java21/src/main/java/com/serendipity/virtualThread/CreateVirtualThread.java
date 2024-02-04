package com.serendipity.virtualThread;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author zhaoxu
 * @date 2024/1/17
 */
public class CreateVirtualThread {
    @Test
    public void testCreateVirtualThread() {
        // 创建并提交执行虚拟线程
        long start = System.currentTimeMillis();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10_000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    return i;
                });
            });
        }
        System.out.println("time:" + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    public void testCreatePhysicsThread() {
        // 创建并提交执行虚拟线程
        long start = System.currentTimeMillis();
        try (var executor = Executors.newCachedThreadPool()) {
            IntStream.range(0, 10_000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    return i;
                });
            });
        }
        System.out.println("time:" + (System.currentTimeMillis() - start) + "ms");
    }
}
