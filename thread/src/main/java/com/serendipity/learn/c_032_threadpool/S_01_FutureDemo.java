package com.serendipity.learn.c_032_threadpool;

import java.util.concurrent.*;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/5 1:32
 * FileName: S_01_FutureDemo
 * Description: com.java.thread.c_032_threadpool
 */
public class S_01_FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 异步执行
//        Future<?> submit = executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println(Thread.currentThread().getName() + "run....");
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("over");
//            }
//        });
        // 下面这个方法采用lambda
        Future<?> submit = executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "run....");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("over");
        });
        // get方法一直处于阻塞状态,直到上面over执行完之后才会执行
        Object o = submit.get();
        System.out.println(o);
        System.out.println(submit.isDone());
        System.out.println(submit.isCancelled());
        System.out.println(submit.cancel(true));
        System.out.println(submit.isCancelled());
        // 这个是用来终止线程执行
        executorService.shutdown();
    }
}
