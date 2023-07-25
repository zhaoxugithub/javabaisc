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
public class T {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(10, () -> {
            System.out.println("end");
        });

        for (int i = 0; i < 100; i++) {

            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println("init...");
                    System.out.println(Thread.currentThread().getName()+":"+ finalI);
                    TimeUnit.SECONDS.sleep(2);
                    //线程调用 await() 表示自己已经到达栅栏
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
