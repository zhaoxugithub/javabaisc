package com.serendipity.mythread.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcConcurrentExample {

    // 模拟RPC接口调用
    public static String callRpc(int i) {
        try {
            // 假设每次调用RPC接口需要1秒
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Result from RPC call " + i;
    }

    public static void main(String[] args) {
        int numCalls = 1000; // 总调用次数
        int maxWorkers = 10; // 线程池大小
        // 创建固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(maxWorkers);
        List<CompletableFuture<String>> futures = new ArrayList<>();

        long startTime = System.currentTimeMillis();
        System.out.println("start:" + startTime);
        // 提交任务到线程池
        for (int i = 0; i < numCalls; i++) {
            final int index = i;
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> callRpc(index), executor);
            futures.add(future);
        }
        // 收集结果
        List<String> results = new ArrayList<>();
        for (CompletableFuture<String> future : futures) {
            try {
                results.add(future.get()); // 阻塞获取结果
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        // 关闭线程池
        executor.shutdown();

        // 结束时间
        long endTime = System.currentTimeMillis();


        System.out.println("end:" + endTime);

        System.out.println("cost:" + (endTime - startTime));
        // 输出结果
        System.out.println("Total results: " + results.size());
    }
}
