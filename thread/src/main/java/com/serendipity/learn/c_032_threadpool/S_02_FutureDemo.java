package com.serendipity.learn.c_032_threadpool;

import java.util.concurrent.*;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/5 1:38
 * FileName: S_02_FutureDemo
 * Description: com.java.thread.c_032_threadpool
 */
public class S_02_FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
//        Future<Integer> submit = executorService.submit(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                TimeUnit.SECONDS.sleep(1);
//                return 1;
//            }
//        });
//        Integer integer = submit.get();
//        System.out.println(integer);
        Future<Integer> submit = executorService.submit(() -> {
            System.out.println("start...");
            TimeUnit.SECONDS.sleep(1);
            return 1;
        });
        Integer integer = submit.get();
        System.out.println(integer);
        // 这个是用来终止线程执行
        executorService.shutdown();
    }
}
