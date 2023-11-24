package com.serendipity.learn.c_001;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/22 8:42 下午
 * FileName: AwaitSignalExample
 * Description: com.java.thread.c_001
 */
@Slf4j
public class AwaitSignalExample {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void before() {
        log.info("threadName={},threadState={},before", Thread.currentThread().getName(), Thread.currentThread().getState());

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getState() + ":" + "before");
            // condition.signalAll();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void after() {
        lock.lock();
        try {
            // await现
            condition.await();
            System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getState() + ":" + "after");
            log.info(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AwaitSignalExample example = new AwaitSignalExample();

        executorService.execute(example::before);
        executorService.execute(example::before);
        executorService.shutdown();
    }
}
