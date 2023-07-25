package com.serendipity.learn.c_030;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/2 21:44
 * FileName: SynchronusQueue_v1
 * Description: com.java.thread.c_030
 * <p>
 * <p>
 * 阻塞队列：主要场景是，两个线程，其中生产线程一直在等着给消费线程资源，如果没有消费线程，
 * 那么生产线程一直阻塞
 * <p>
 * <p>
 * 单人对单人之间的手对手
 */
public class SynchronusQueue_v1 {


    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> str = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(str.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println(str.size());
        //如果之前没有创建消费者线程，那么add方法会抛出一个异常：IllegalStateException: Queue full
//        boolean d = str.add("d");
//        System.out.println(d);


        // 如果没有消费线程，这个线程会一直阻塞
        str.put("dd");

//        //和add的区别是这个方法不会抛出异常
//        boolean dasd = str.offer("dasd");
//        System.out.println(dasd);
    }
}
