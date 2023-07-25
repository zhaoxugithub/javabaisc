package com.serendipity.learn.c_032_threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/5 1:51
 * FileName: S_03_FutureDemo
 * Description: com.java.thread.c_032_threadpool
 */
public class S_03_FutureDemo {
    public static void main(String[] args) {
        // 线程池接口，实际上返回的是ThreadPoolExecutor线程池，ThreadPoolExecutor实现了ExecutorService这个接口
        // Executors 这个是一个工厂类，专门用来创建各种线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Long start = System.currentTimeMillis();
                while (true) {
                    Long current = System.currentTimeMillis();
                    if ((current - start) > 1000) {
                        return 1;
                    }
                }
            }
        });
        try {
            Integer result = (Integer) future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 这个是用来终止线程执行
        executorService.shutdown();
    }
}
