package com.serendipity.learn.c_030;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/2 17:15
 * FileName: ArrayBlockingQueue_v1
 * Description: com.java.thread.c_030
 */
public class ArrayBlockingQueue_v1 {

    private static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(10);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            blockingQueue.add("a" + i);
        }
        // ArrayBlockingQueue队列满了的时候，调用不同步API有不同的效果
        // IllegalStateException: Queue full
//        blockingQueue.add("dfasdf");
        // offer 和add区别是有返回值,并且不会阻塞
        boolean fsdsdfsd = blockingQueue.offer("fsdsdfsd");
        System.out.println(fsdsdfsd);
        boolean dasdsa = blockingQueue.offer("dasdsa", 1, TimeUnit.SECONDS);
        // put方法和add方法的区别就是：当队列满了的时候，put会阻塞，但是add会报出异常
        blockingQueue.put("sdasdasd");
    }
}
