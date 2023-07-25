package com.serendipity.myold.reflect.dynamicProxy;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/11 17:37
 * FileName: Main
 * Description: com.reflect.dynamicProxy
 */
public class Main {

    public static void helloTest(Hello hello) {
        DynamicProxyHandler dynamicProxyHandler = new DynamicProxyHandler(hello);
        Hello handler = dynamicProxyHandler.handler();
        handler.morning();
        handler.afternoon();
        handler.evening();
    }

    public static void main(String[] args) {
        helloTest(new HelloImpl());
        System.out.println("----------");
        helloTest(new WorldImpl());

        System.out.println("vvvv");

    }
}
