package com.serendipity.learn.c_032_threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/5 13:34
 * FileName: S_05_SimpleThreadPool
 * Description: com.java.thread.c_032_threadpool
 */
public class S_05_SimpleThreadPool {

    public static void main(String[] args) throws InterruptedException {

        // 创建一个具有5个线程的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 执行10个任务
        for (int i = 0; i < 10; i++) {
            executorService.submit(new WorkerThread("t" + i));
        }
        // 任务执行完之后线程池需要关闭,也是异步执行的
        // 该方法会停止ExecutorService添加新的任务, 但是老任务还是会继续执行. 这个方法是立刻返回
        executorService.shutdown();

        // main线程阻塞等待所有的线程执行完毕之后才继续往下面执行
        // executorService.awaitTermination(3000, TimeUnit.MILLISECONDS);
        // 或者用这一行，判断线程池任务是否已经终止了，没有终止一直循环
        while (!executorService.isTerminated()) {
        }
        System.out.println("Finished all Thread");
    }


    static class WorkerThread implements Runnable {
        private String command;

        public WorkerThread(String s) {
            this.command = s;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Start. Command = " + command);
            processCommand();
            System.out.println(Thread.currentThread().getName() + " End.");
        }

        private void processCommand() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return this.command;
        }
    }
}
