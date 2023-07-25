package com.serendipity.myold.reflect.dynamicProxy;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/11 18:14
 * FileName: HelloImpl
 * Description: com.reflect.dynamicProxy
 */
public class HelloImpl implements Hello{
    @Override
    public void morning() {
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
