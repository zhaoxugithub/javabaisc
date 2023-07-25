package com.serendipity.learn.c_032_threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/5 2:00
 * FileName: T02_ExecutorService
 * Description: com.java.thread.c_032_threadpool
 * <p>
 * <p>
 * 认识ExecutorService,阅读API文档
 * 认识submit方法，扩展了execute方法，具有一个返回值
 * <p>
 * <p>
 * 线程池：
 * ThreadPoolExecutor:
 * <p>
 * ForkJoinPool: 分解汇总的任务，用很少的线程可以执行很多的任务（子任务） ThreadPoolExecutor 做不到先执行子任务，适用于CPU密集型
 */
public class T02_ExecutorService {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
    }

}
