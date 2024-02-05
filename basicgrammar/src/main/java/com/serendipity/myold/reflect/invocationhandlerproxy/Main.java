package com.serendipity.myold.reflect.invocationhandlerproxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main {

    static InvocationHandler invocationHandler;

    static {
        // proxy: 代理类的类加载器，代理那个类就传那个类的加载器
        // method: 方法
        // args: 参数
        invocationHandler = (proxy, method, args) -> {
            if (method.getName().equals("morning")) {
                // method.invoke()
                System.out.println("morning....");
            } else if (method.getName().equals("defaultMethod")) {
                method.invoke(proxy, args);
                System.out.println("de.....");
            } else {
                System.out.println("other");
            }
            return proxy;
        };
    }

    interface Hello {
        void morning();

        void afternoon();

        void goodwill();
    }

    interface World {
        void morning();

        default void defaultMethod() {
            System.out.println("default.");
        }
    }

    class HelloA implements Hello {

        @Override
        public void morning() {
            System.out.println("helloA-morning");
        }

        @Override
        public void afternoon() {
            System.out.println("helloA-afternoon");
        }

        @Override
        public void goodwill() {
            System.out.println("helloA-goodwill");
        }
    }

    @Test
    public void test() {
        // newProxyInstance(类加载器,接口数组，代理)
        Hello hello = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class[]{Hello.class}, invocationHandler);
        hello.morning();
        hello.afternoon();
    }

    @Test
    public void test2() {
        // newProxyInstance(类加载器,接口数组，代理)
        World hello = (World) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class[]{Hello.class, World.class}, invocationHandler);
        hello.morning();
//        hello.defaultMethod();
//        hello.afternoon();
    }
}
