package com.serendipity.myold.aop.dynamicProxy.demo03;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

@Slf4j
public class CgLibDynamicProxyHandler<T>  {

    private final Class<T> clazz;

    public CgLibDynamicProxyHandler(Class<T> clazz){
       this.clazz = clazz;
    }

    public T getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            long startTime = System.currentTimeMillis();
            log.info("Start time: {}, Method: {}, Args: {}", startTime, method.getName(), args);

            Object result = proxy.invokeSuper(obj, args);

            long endTime = System.currentTimeMillis();
            log.info("Cost time: {}, Result: {}", endTime - startTime, result);
            return result;
        });
        return (T)enhancer.create();
    }
}
