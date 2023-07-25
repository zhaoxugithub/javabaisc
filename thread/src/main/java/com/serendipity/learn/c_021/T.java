package com.serendipity.learn.c_021;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/5 11:05 上午
 * FileName: T
 * Description: com.java.thread.c_021
 * <p>
 * 无锁、偏向锁、重量锁、轻量锁（自旋锁）
 * <p>
 * 1）CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同：
 * 　　　　CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；
 * 　　　　而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；
 * 　　　　另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。
 * 　　2）Semaphore其实和锁有点类似，它一般用于控制对某组资源的访问权限。
 */
public class T {
    public static void main(String[] args) {
        //限流
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    // 加1
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + ":start...at" + System.currentTimeMillis());
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放1
                    semaphore.release();
                }
            }).start();
        }
    }
}
