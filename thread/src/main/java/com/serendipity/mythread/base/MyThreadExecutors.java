package com.serendipity.mythread.base;

import java.util.concurrent.*;

/**
 * ClassName MyThreadExecutors
 * Description TODO
 * Author 11931
 * Date 2023-06-11:20:29
 * Version 1.0
 **/
public class MyThreadExecutors {
    private static final int MAX_TURN = 5;
    private static final int COMPUTE_TIMES = 1000_000_00;
    private static ExecutorService pool = Executors.newFixedThreadPool(3);

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.println(Thread.currentThread().getName() + ", 轮次: " + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static class ReturnableTask implements Callable<Long> {
        // 返回并发执行的时间
        @Override
        public Long call() throws Exception {
            long startTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " 线程运行开始");
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.println(Thread.currentThread().getName() + ", 轮次: " + i);
                Thread.sleep(10);
            }
            long used = System.currentTimeMillis() - startTime;
            System.out.println(Thread.currentThread().getName() + " 线程运行结束.");
            return used;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 执行线程示例,无返回
        pool.execute(new MyRunnable());
        pool.execute(() -> {
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.println(Thread.currentThread().getName() + ", 轮次: " + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        // 提交Callable 执行目标示例,有返回
        Future future = pool.submit(new ReturnableTask());
        Long result = (Long) future.get();
        System.out.println("异步任务的执行结果为:" + result);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
