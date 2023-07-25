package com.serendipity.learn.c_032_threadpool;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/6 12:10
 * FileName: T03_CompletableFuture
 * Description: com.java.thread.c_032_threadpool
 * <p>
 * <p>
 * 假设你能够提供一个服务
 * 这个服务查询各大电商网站同一类产品的价格并汇总展示
 *
 * CompletableFuture:可以管理线程任务
 */
public class T03_CompletableFuture {


    public static void main(String[] args) {

        long start, end;

     /* start = System.currentTimeMillis();
        priceJD();
        priceTB();
        priceTM();
        end = System.currentTimeMillis();
        System.out.println(end - start);*/

        start = System.currentTimeMillis();
        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(T03_CompletableFuture::priceTM);
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(T03_CompletableFuture::priceTB);
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(T03_CompletableFuture::priceJD);

        // join 方法是执行线程的意思。异步执行
//        futureJD.join();
//        futureTM.join();
//        futureTB.join();

        //还有另外一种方法，通过allof将三个线程任务放到一起执行
//        CompletableFuture.allOf(futureJD, futureTB, futureTM).join();

        CompletableFuture.supplyAsync(T03_CompletableFuture::priceTM)
                .thenApply(String::valueOf).thenApply(str -> "price:" + str)
                .thenAccept(System.out::println);

        end = System.currentTimeMillis();

        System.out.println(end - start);

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static double priceTM() {
        delay();
        return 1.00;
    }


    public static double priceTB() {
        delay();
        return 2.00;
    }


    public static double priceJD() {
        delay();
        return 3.00;
    }

    public static void delay() {
        int time = new Random().nextInt(1000);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(time);
    }

}
