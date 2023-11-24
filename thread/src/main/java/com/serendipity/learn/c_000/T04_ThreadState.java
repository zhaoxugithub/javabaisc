package com.serendipity.learn.c_000;

import lombok.extern.slf4j.Slf4j;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/8/31 12:13 上午
 * FileName: T04_ThreadState
 * Description: com.java.thread.c_001
 * <p>
 * 线程的几种状态：RUNNABLE，NEW,BLOCKED,WAITING,TIMED_WAITING,TERMINATED
 * <p>
 * NEW： 新建状态，线程对象已经创建，但尚未启动
 * <p>
 * RUNNABLE:就绪状态，可运行状态，调用了线程的start方法，已经在java虚拟机中执行，等待获取操作系统资源如CPU，操作系统调度运行。
 * <p>
 * BLOCKED:堵塞状态。线程等待锁的状态，等待获取锁进入同步块/方法或调用wait后重新进入需要竞争锁,属于被动的状态
 * <p>
 * WAITING：等待状态。等待另一个线程以执行特定的操作。调用以下方法进入等待状态。 Object.wait(), Thread.join(),LockSupport.park
 * <p>
 * TIMED_WAITING: 线程等待一段时间。调用带参数的Thread.sleep, objct.wait,Thread.join，LockSupport.parkNanos,LockSupport.parkUntil
 * <p>
 * TERMINATED:进程结束状态。
 */
@Slf4j
public class T04_ThreadState {
    public static class MyThread extends Thread {
        @Override
        public void run() {
            // RUNNABLE,等待操作系统执行
            log.info(String.valueOf(this.getState()));
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    log.error("exception={}", e.getMessage());
                }
                log.info("i={}", i);
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        // NEW,线程对象已经创建完成
        log.info(String.valueOf(myThread.getState()));
        myThread.start();
        try {
            // mythread线程强制执行,其他线程必须等到mythread 线程执行完毕之后才能执行
            myThread.join();
        } catch (InterruptedException e) {
            log.error("exception={}", e.getMessage());
        }
        for (int i = 0; i < 10; i++) {
            log.info("main thread:{}", i);
        }
        // TERMINATED
        log.info("myState={}", myThread.getState());
    }
}
