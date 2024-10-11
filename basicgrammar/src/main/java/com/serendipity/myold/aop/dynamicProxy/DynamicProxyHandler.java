package com.serendipity.myold.aop.dynamicProxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/11 18:15
 * FileName: dynamicProxyHandler
 * Description: com.reflect.dynamicProxy
 */
@Slf4j
public class DynamicProxyHandler {
    // 实际代理对象
    private final Object object;
    private final Class<?>[] interfaces;

    public DynamicProxyHandler(Object object) {
        this.object = object;
        this.interfaces = object.getClass()
                                .getInterfaces();
    }

    public Object handler() {
        return Proxy.newProxyInstance(object.getClass()
                                            .getClassLoader(),
                interfaces,
                (proxy, method, args) -> {

            long startTime = System.currentTimeMillis();
            log.info("start time: {}, method: {}, args: {}", startTime, method.getName(), args);

            Object result = method.invoke(object, args);

            long endTime = System.currentTimeMillis();
            log.info("cost time: {}, result: {}", endTime - startTime, result);
            return result;
        });
    }
}
