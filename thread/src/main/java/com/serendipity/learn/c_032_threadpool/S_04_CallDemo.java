package com.serendipity.learn.c_032_threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/5 13:11
 * FileName: S_04_CallDemo
 * Description: com.java.thread.c_032_threadpool
 */
public class S_04_CallDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*第一种方法:Future+ExecutorService
        ExecutorService executorService = Executors.newCachedThreadPool();
        //Future 里面封装的是线程执行任务的返回信息，返回值，返回状态，异步执行
        Future<Integer> submit = executorService.submit(new Task());
        //关闭线程池
        executorService.shutdown();*/
        /*//第二种方法:FutureTask+ExecutorService
        ExecutorService executorService = Executors.newCachedThreadPool();
        //FutureTaskb 里面包装了线程的运行以及返回结果
        FutureTask<Integer> integerFutureTask1 = new FutureTask<>(new Task());
        executorService.submit(integerFutureTask1);
        //获取线程的返回结果直接从FutureTask中获取就可以了
        System.out.println(integerFutureTask1.get());
        executorService.shutdown();*/
//        //第二种方法：FutureTask+Thread,没有采用线程池
        FutureTask<Integer> task = new FutureTask<>(new Task());
        // 因为FutureTask 实现了runnable接口，所以可以作为Thread参数
        Thread thread = new Thread(task);
        thread.setName("t1");
        thread.start();
        Thread.sleep(1000);
        // 判断线程是否结束
        if (!task.isDone()) {
            System.out.println("task is not done");
            Thread.sleep(2000);
        }
        // 任务执行超过3s,任务执行成功返回true
        System.out.println(task.isDone());
        System.out.println(task.get());
    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("Thread [" + Thread.currentThread().getName() + "] is running");
            int result = 0;
            for (int i = 0; i < 100; ++i) {
                result += i;
            }
            Thread.sleep(3000);
            return result;
        }
    }
}
