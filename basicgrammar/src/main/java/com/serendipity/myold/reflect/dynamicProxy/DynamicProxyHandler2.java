package com.serendipity.myold.reflect.dynamicProxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 使用jdk自带的动态代理
 * https://javaguide.cn/java/basis/proxy.html#_2-静态代理
 */
public class DynamicProxyHandler2 {

    private interface AInterface {
        void function1(String param1);
    }

    private class A implements AInterface {
        private String name;

        public void function1(String param1) {
            System.out.println("class A method function1 ---- " + param1);
        }
    }

    // 继承InvocationHandler
    private class DynamicProxyHandlerDemo implements InvocationHandler {

        // 真实对象
        private final Object target;

        private DynamicProxyHandlerDemo(Object target) {
            this.target = target;
        }

        /**
         * @param proxy  the proxy instance that the method was invoked on
         *               代理对象
         * @param method the {@code Method} instance corresponding to
         *               the interface method invoked on the proxy instance.  The declaring
         *               class of the {@code Method} object will be the interface that
         *               the method was declared in, which may be a superinterface of the
         *               proxy interface that the proxy class inherits the method through.
         * @param args   an array of objects containing the values of the
         *               arguments passed in the method invocation on the proxy instance,
         *               or {@code null} if interface method takes no arguments.
         *               Arguments of primitive types are wrapped in instances of the
         *               appropriate primitive wrapper class, such as
         *               {@code java.lang.Integer} or {@code java.lang.Boolean}.
         * @return
         * @throws Throwable
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("动态代理对象开始....." + method.getName() + "---params:" + Arrays.toString(args));
            Object result = method.invoke(target, args);
            System.out.println("动态代理对象结束....." + method.getName() + "---params:" + Arrays.toString(args));
            return result;
        }
    }

    public Object getProxyClass(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new DynamicProxyHandlerDemo(target));
    }

    @Test
    public void test01() {
        // 生成动态代理对象
        AInterface proxyClass = (AInterface) getProxyClass(new A());
        proxyClass.function1("params111111");
    }

}
