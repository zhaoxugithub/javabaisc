package com.serendipity.myold.aop.dynamicProxy.demo01;

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
    private final T object;

    public DynamicProxyHandler(T object) {
        this.object = object;
    }

    @SuppressWarnings("unchecked")
    public T getProxy() {
        return (T) Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        log.info("Start time: {}, Method: {}, Args: {}", startTime, method.getName(), args);

        Object result;
        try {
            result = method.invoke(object, args);
        } catch (Exception e) {
            log.error("Method invocation failed: {}", e.getMessage(), e);
            throw e;
        }

        long endTime = System.nanoTime();
        log.info("Cost time: {} ns, Result: {}", endTime - startTime, result);
        return result;
    }
}