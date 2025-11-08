package com.serendipity.mythread.completable;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class CompletableDemo01 {

    public static void main(String[] args) throws InterruptedException {
        test4();
    }

    public static void test2() throws InterruptedException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(CompletableDemo01::slowFunction);
        // 执行成功之后进行回调
        cf.thenAccept((result) -> System.out.println("result: " + result));
        System.out.println("main thread is not blocked");
        Arrays.asList(1, 2, 3).forEach(System.out::println);
        Thread.sleep(5000);
    }

    public static void test3() throws InterruptedException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(CompletableDemo01::fastFunction);
        Thread.sleep(1000);
        Arrays.asList(1, 2, 3).forEach(System.out::println);
        // 执行成功之后进行回调
        cf.thenAccept((result) -> System.out.println("result: " + result));
        Thread.sleep(5000);
    }

    public static void test4() throws InterruptedException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(CompletableDemo01::exceptionFunction);
        // 执行成功回调
        cf.exceptionally((ex -> {
            System.out.println("error: " + ex.getMessage());
            return "error";
        }));
        cf.thenAccept((result -> System.out.println("result: " + result)));
        Thread.sleep(2000);
    }

    // 串行执行
    public static void test5() {
        // 第一个任务
        CompletableFuture.supplyAsync(() -> {
            return null;
        });
    }

    static String queryCode(String name) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }

    public static void test() {
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableDemo01::fetchPrice);
        // 执行成功回调
        cf.thenAccept((result) -> System.out.println("price: " + result));
    }

    static String exceptionFunction() {
        throw new RuntimeException("fetch price failed!");
    }

    static String fastFunction() {
        return "hello";
    }

    static String slowFunction() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        return "hello";
    }

    static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }
}
