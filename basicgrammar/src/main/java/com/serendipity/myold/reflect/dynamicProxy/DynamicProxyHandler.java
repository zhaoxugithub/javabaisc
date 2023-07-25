package com.serendipity.myold.reflect.dynamicProxy;

import java.lang.reflect.Proxy;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/11 18:15
 * FileName: dynamicProxyHandler
 * Description: com.reflect.dynamicProxy
 */
public class DynamicProxyHandler {

    private Object object = null;

    public DynamicProxyHandler(Object object) {
        this.object = object;
    }

    public Hello handler() {
        return (Hello) Proxy.newProxyInstance(object.getClass().getClassLoader(), new Class[]{Hello.class}, (proxy, method, args) -> {
            System.out.println("start...");
            Object result = method.invoke(object, args);
            System.out.println("over...");
            return result;
        });
    }
}
