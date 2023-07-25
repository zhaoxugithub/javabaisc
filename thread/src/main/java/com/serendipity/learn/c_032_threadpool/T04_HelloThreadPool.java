package com.serendipity.learn.c_032_threadpool;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/6 13:41
 * FileName: T04_HelloThreadPool
 * Description: com.java.thread.c_032_threadpool
 * <p>
 * <p>
 * 自定义线程池
 * 线程池的7个参数意义
 * <p>
 * 2.当调用execute()方法添加一个请求任务时，线程池会做以下判断
 * <p>
 * 如果正在运行的线程数量小于corePoolSize，那么马上创建线程运行这个任务；
 * <p>
 * 如果正在运行的线程数据大于或等于corePoolSize，那么这个任务放到队列；
 * <p>
 * 如果这时候队列满了且正在运行的线程数量还小于maximumPoolsize，那么还是要创建非核心线程立刻执行这个任务；
 * <p>
 * 如果队列满了且正在运行的线程数量大于或等于maximumPoolsize，那么线程池会启动饱和拒绝策略来执行
 */
public class T04_HelloThreadPool {

    static class Task implements Runnable {
        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Task " + i);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }
    }


    public static void main(String[] args) {
        /**
         * corePoolSize：线程池中的常驻核心线程数，线程池刚创建完成，核心线程数为0，当有新的任务来了的时候，不会放到任务队列中（等待队列（阻塞队列）），而是交给核心线程去处理，当任务数超过核心线程数的时候，就会放到等待队列中。
         * 当等待队列满的时候，则会启动非核心线程去执行新来的任务。当非核心线程空闲时间超过keepAliveTime的时候，就会自动销毁；核心线程默认不会被销毁掉
         *
         *
         * maximumPoolSize：线程池能够容纳同时执行的最大线程数，此值必须大于等于1
         * keepAliveTime：多余的空闲线程的存活时间
         * unit：keepAliveTime的单位
         * workQueue：任务队列，被提交单尚未被执行的任务
         * threadFactory：表示生成线程池中工作线程的线程工厂，用于创建线程一般用默认的即可
         * handler：拒绝策略：
         * 内置拒绝策略均实现了RejectedExecutionHandler接口
         * AbortPolicy(默认)：直接抛出RejectedExecutionException异常阻止系统正常运行 （抛异常，系统正常执行）
         * CallerRunsPolicy：“调用者运行”一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量 （退回调用者）
         * DiscardOldestPolicy：抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务  （抛弃最久）
         * DiscardPolicy：直接丢弃任务，不予任务处理也不抛弃异常，如果允许任务丢失，这是最好的一种方案   （抛弃新来的）
         */

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
        //创建任务
        for (int i = 0; i < 8; i++) {
            threadPoolExecutor.execute(new Task(i));
        }
        //获取等待队列
        System.out.println(threadPoolExecutor.getQueue());
        //创建一个线程，因为超过了线程池的最大容量，会根据对应的拒绝策略拒绝掉丢弃掉任务
        threadPoolExecutor.execute(new Task(100));
        threadPoolExecutor.execute(new Task(101));
        System.out.println(threadPoolExecutor.getQueue());
        threadPoolExecutor.shutdown();
    }
}
