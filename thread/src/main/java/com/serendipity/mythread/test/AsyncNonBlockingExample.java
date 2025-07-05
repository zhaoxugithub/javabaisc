package com.serendipity.mythread.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncNonBlockingExample {

    // 模拟耗时操作的方法，例如RPC调用或IO操作
    public static String performTask(int taskId) {
        try {
            // 模拟耗时1秒
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Task " + taskId + " completed";
    }

    public static void main(String[] args) {
        // 异步任务数量
        int totalTasks = 10;
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<CompletableFuture<String>> futures = new ArrayList<>();

        // 提交异步非阻塞任务
        for (int i = 0; i < totalTasks; i++) {
            final int taskId = i;
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> performTask(taskId), executor);
            futures.add(future);
        }

        // 非阻塞等待所有任务完成，并收集结果
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        allFutures.thenRun(() -> {
            List<String> results = new ArrayList<>();
            for (CompletableFuture<String> future : futures) {
                try {
                    results.add(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            results.forEach(e -> System.out.println("All tasks completed. Results: " + e));
        }).join();

        executor.shutdown();
    }
}
