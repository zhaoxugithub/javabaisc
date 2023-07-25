package com.serendipity.mythread.base;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ClassName MyThreadFutureTask
 * Description TODO
 * Author 11931
 * Date 2023-06-10:18:21
 * Version 1.0
 **/
public class MyThreadFutureTask {
    private static final int Max_TURN = 5;
    private static final int COMPUTE_TIMES = 100000000;

    // 创建一个Callable接口的实现类
    private static class ReturnableTask implements Callable<Long> {
        @Override
        public Long call() throws Exception {
            long startTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " thread begin start.");
            Thread.sleep(1000);
            for (int i = 0; i < COMPUTE_TIMES; i++) {
                int j = i * 10000;
            }
            long used = System.currentTimeMillis() - startTime;
            System.out.println(Thread.currentThread().getName() + " thread end.");
            return used;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ReturnableTask task = new ReturnableTask();
        FutureTask<Long> futureTask = new FutureTask<Long>(task);
        Thread thread = new Thread(futureTask, "returnableThread");
        thread.start();
        Thread.sleep(500);
        System.out.println(Thread.currentThread().getName() + " please fly....");
        System.out.println(Thread.currentThread().getName() + " do something for myself.");
        for (int i = 0; i < COMPUTE_TIMES; i++) {
            int j = i * 10000;
        }
        System.out.println(Thread.currentThread().getName() + " 获取并发任务的执行结果");
        System.out.println(thread.getName() + " 线程占用时间: " + futureTask.get());
        System.out.println(Thread.currentThread().getName() + " 运行结束.");
    }
}
