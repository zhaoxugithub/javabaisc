package com.serendipity.myold.aop.cglib;


import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用Cglib实现动态代理
 */
public class CglibDemo01 {
    public class UserServiceImpl {
        void play() {
            System.out.println("Cglib UserServiceImpl play");
        }
    }

    public class CglibProxy implements MethodInterceptor {

        private Enhancer enhancer = new Enhancer();

        public Object getProxy(Class clazz) {
            enhancer.setSuperclass(clazz);
            enhancer.setCallback(this);
            return enhancer.create();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("before.");
            Object invoke = methodProxy.invokeSuper(o, objects);
            System.out.println("after.");
            return invoke;
        }
    }

    @Test
    public void test() {
        CglibProxy cglibProxy = new CglibProxy();
        UserServiceImpl proxy = (UserServiceImpl) cglibProxy.getProxy(UserServiceImpl.class);
        proxy.play();
    }

}
