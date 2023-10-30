package com.serendipity.myold.reflect.dynamicProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * ClassName DynamicProxyHandler3
 * Description TODO
 * Author 11931
 * Date 2023-08-09:21:05
 * Version 1.0
 * <p>
 * 使用cglib动态代理
 **/
public class DynamicProxyHandler3 {
    class A {
        public A() {
        }

        public void function2(String params) {
            System.out.println("function2:" + params);
        }
    }

    private Object getProxyClass(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(target.getClass()
                                      .getClassLoader());
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("动态代理对象开始....." + method.getName() + "---params:" + Arrays.toString(objects));
                Object result = method.invoke(target, objects);
                System.out.println("动态代理对象结束....." + method.getName() + "---params:" + Arrays.toString(objects));
                return result;
            }
        });
        return enhancer.create();
    }


    private Object getProxyClass2(Class<?> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(clazz.getClassLoader());
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("动态代理对象开始....." + method.getName() + "---params:" + Arrays.toString(objects));
                Object result = methodProxy.invokeSuper(clazz, objects);
                System.out.println("动态代理对象结束....." + method.getName() + "---params:" + Arrays.toString(objects));
                return result;
            }
        });
        return enhancer.create();
    }

    class B {
        public void run() {
            System.out.println("B run");
        }
    }

    @Test
    public void test03() {
        B bProxy = (B) getProxyClass(new B());
        bProxy.run();
    }

    @Test
    public void test01() {
        A proxyClass = (A) getProxyClass(new A());
        proxyClass.function2("sdfdsff");
    }

    //--add-opens java.base/java.lang=ALL-UNNAMED
    @Test
    public void test02() {
        A proxyClass = (A) getProxyClass2(A.class);
        proxyClass.function2("sdfdsff");
    }

}
