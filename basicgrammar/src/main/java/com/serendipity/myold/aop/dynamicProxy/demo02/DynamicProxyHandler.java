package com.serendipity.myold.aop.dynamicProxy.demo02;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/11 18:15
 * FileName: dynamicProxyHandler
 * Description: com.reflect.dynamicProxy
 */
@Slf4j
public class DynamicProxyHandler<T> implements InvocationHandler {
    // 实际代理对象
    private final Class<T> clazz;

    public DynamicProxyHandler(Class<T> object) {
        this.clazz = object;
    }

    @SuppressWarnings("unchecked")
    public T getProxy() {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("Start time: {}, Method: {}, Args: {}", startTime, method.getName(), args);

        Object result = method.invoke(clazz.getDeclaredConstructor()
                                           .newInstance(), args);

        long endTime = System.currentTimeMillis();
        log.info("Cost time: {}, Result: {}", endTime - startTime, result);
        return result;
    }
}