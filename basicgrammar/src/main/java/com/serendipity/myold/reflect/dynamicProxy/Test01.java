package com.serendipity.myold.reflect.dynamicProxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * ClassName Test01
 * Description TODO
 * Author 11931
 * Date 2023-05-29:23:50
 * Version 1.0
 **/
@Slf4j
public class Test01 {

    private interface A {
        void method1();

        void method2();

        void method3();
    }

    private static class AChild implements A {

        @Override
        public void method1() {
            System.out.println("Achild,method1");
        }

        @Override
        public void method2() {
            System.out.println("Achild,method2");
        }

        @Override
        public void method3() {
            System.out.println("Achild,method3");
        }
    }

    public A test(Object obj) {
        return (A) Proxy.newProxyInstance(Test01.class.getClassLoader(), new Class[]{A.class}, (InvocationHandler) (proxy, method, args) -> {
            System.out.println("before");
            Object invoke = method.invoke(obj, args);
            System.out.println("after");
            return invoke;
        });
    }

    public static void main(String[] args) {
        Test01 test01 = new Test01();
        A aChildProxy = test01.test(new AChild());
        aChildProxy.method1();

    }

}
