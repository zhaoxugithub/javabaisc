package com.serendipity.learn.c_019;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: Administrator
 * Date: 2021/9/4 17:04
 * FileName: T
 * Description: com.java.thread.c_019
 */

/*
CyclicBarrier 的主要作用如下：
它可以让一组线程在达到共同的屏障点之前等待彼此，然后同时继续执行
同步多个线程：CyclicBarrier 可以用于同步多个线程，让它们在某个点上进行等待，直到所有线程都到达该点，然后同时继续执行。这对于需要多个线程协同工作完成某个任务的场景非常有用。
划分任务阶段：CyclicBarrier 可以帮助划分任务的阶段，每个阶段都在达到屏障点时进行同步。线程可以在每个阶段结束时等待其他线程，然后在所有线程都完成当前阶段后，继续执行下一个阶段。
执行回调操作：CyclicBarrier 提供了一个可选的回调操作，可以在所有线程都到达屏障点时执行。这个回调操作可以用于执行一些特定的逻辑或任务，例如收集线程的计算结果或更新共享数据。

 */

public class T {
    public static void main(String[] args) {
        // 第一个参数：表示需要等待的线程数量
        // 第二个参数：表示所有线程到达屏障点后，需要执行的操作
        CyclicBarrier barrier = new CyclicBarrier(10, () -> {
            System.out.println("end");
        });
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println("init...");
                    System.out.println(Thread.currentThread().getName() + ":" + finalI);
                    TimeUnit.SECONDS.sleep(2);
                    // 线程调用 await() 表示自己已经到达栅栏
                    barrier.await();
                    System.out.println("start ....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
