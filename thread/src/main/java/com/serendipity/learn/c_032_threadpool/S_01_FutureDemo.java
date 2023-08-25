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
        // newCachedThreadPool 线程池中的这个队列没有元素,来一个任务开辟一个线程,没有核心线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        /*
               submit(runnable) runnable里面没有返回值
               submit(callable) callable里面有返回值
         */
        Future<?> submit = executorService.submit(() -> {
            System.out.println(Thread.currentThread()
                                     .getName() + "run....");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("over");
            return "OK";
        });
        System.out.println("main start :" + System.currentTimeMillis());
        // 锁的释放和IO阻塞会导致用户态内核态切换以及线程的上下文切换
        // get方法一直处于阻塞状态,直到上面over执行完之后才会执行,这里会不会涉及到上下文切换,也不会设计内核态和用户态的切换
        Object o = submit.get();
        System.out.println(o);
        System.out.println("main end :" + System.currentTimeMillis());
        System.out.println(submit.isDone());
        System.out.println(submit.isCancelled());
        System.out.println(submit.cancel(true));
        System.out.println(submit.isCancelled());
        // 这个是用来终止线程执行
        executorService.shutdown();
    }
}
