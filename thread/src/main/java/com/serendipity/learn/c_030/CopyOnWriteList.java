package com.serendipity.learn.c_030;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/1 17:37
 * FileName: CopyOnWriteList
 * Description: com.java.thread.c_030
 *
 *
 * 写时复制容器 copy on write
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境
 */
public class CopyOnWriteList {

    /**
     * @Description: 计算线程花费时间
     * @Param:
     * @return:
     * @Author:
     * @Date:
     */
    public static void computerTime(Thread[] threads) {
        long start = System.currentTimeMillis();
        Arrays.stream(threads).forEach(Thread::start);
        Arrays.stream(threads).forEach(th -> {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void main(String[] args) {
//        List<String> list =
//                new CopyOnWriteArrayList<>();
//                new ArrayList<>();


        //和CopyOnWriteArrayList比较，还是ConcurrentLinkedQueue 效率个更高，
        //
        //
        // 写时复制，读的时候时不用加锁的，但是写的时候时需要加锁并且copy原来数组；相对比synchronizedLinkedList 和vector 来说，读的时候效率更高
        // 适用场景：读线程比较多的情况下
        Queue<String> list = new ConcurrentLinkedQueue<String>();

        Thread[] th = new Thread[100];
        for (int i = 0; i < th.length; i++) {

            th[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    list.add("a" + j);
                }
            });
        }

        computerTime(th);

        System.out.println(list.size());


    }


}
