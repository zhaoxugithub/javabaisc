package com.serendipity.learn.c_030;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/1 18:10
 * FileName: LinkedBlockingQueue_v1
 * Description: com.java.thread.c_030
 * <p>
 * 阻塞队列，在线程池方面用的比较多
 */
public class LinkedBlockingQueue_v1 {
    public static BlockingQueue<String> blockQueue = new LinkedBlockingQueue<String>();
    public static Random random = new Random();

    public static void main(String[] args) {

        // 启动一个线程用来写
        new Thread(() -> {

            for (int i = 0; i < 100; i++) {
                try {
                    // 如果容器满了，会阻塞住
                    blockQueue.put("a" + i);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 启动五个线程去读
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        if (blockQueue.isEmpty()) {
                            System.out.println(Thread.currentThread().getName() + "--please wait for 1 minute");
                            TimeUnit.SECONDS.sleep(1);
                        }
                        // take 如果容器空了会阻塞住
                        System.out.println(Thread.currentThread().getName() + "-----take-----" + blockQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }, "C" + i).start();
        }

    }
}
