package com.serendipity.myold.aop.dynamicProxy.demo01;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/11 17:37
 * FileName: Main
 * Description: com.reflect.dynamicProxy
 */
public class DynamicProxyHandlerTest {
    interface Hello {
        void morning() throws InterruptedException;
        void afternoon();

        void evening();
    }

    static class HelloImpl implements Hello {
        @Override
        public void morning() throws InterruptedException {
            Thread.sleep(1000);
            System.out.println("HelloImpl morning...");
        }

        @Override
        public void afternoon() {
            System.out.println("HelloImpl afternoon....");
        }

        @Override
        public void evening() {
            System.out.println("HelloImpl evening....");
        }
    }

    static class WorldImpl implements Hello {
        @Override
        public void morning() {
            System.out.println("worldImpl morning....");
        }

        @Override
        public void afternoon() {
            System.out.println("worldImpl afternoon....");
        }

        @Override
        public void evening() {
            System.out.println("worldImpl evening....");
        }
    }

    // 测试2

    interface Hello2 {
        void play();

        void sleep();
    }

    static class Hello2Impl implements Hello2 {
        @Override
        public void play() {
            System.out.println("Hello2Impl play....");
        }

        @Override
        public void sleep() {
            System.out.println("Hello2Impl sleep....");
        }
    }

    private static <T> T getProxyInstance(T t) {
        DynamicProxyHandler<T> dynamicProxyHandler = new DynamicProxyHandler<>(t);
        return dynamicProxyHandler.getProxy();
    }

    public static void test(Object object) throws InterruptedException {
        Object instance = getProxyInstance(object);
        switch (instance) {
            case Hello hello -> {
                hello.morning();
                hello.afternoon();
                hello.evening();
            }
            case Hello2 hello2 -> {
                hello2.play();
                hello2.sleep();
            }
            default -> throw new IllegalStateException("Unexpected value: " + object);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        test(new HelloImpl());
        test(new WorldImpl());
        test(new Hello2Impl());
        Object o = getProxyInstance(new HelloImpl());
    }
}
