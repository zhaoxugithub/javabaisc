package com.serendipity.myold.reflect.dynamicProxy;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

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
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new DynamicProxyHandlerDemo(target));
    }


    // 自带jdk生成动态代理对象
    private Object getProxyClass2(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, args) -> {
            System.out.println("动态代理对象开始....." + method.getName() + "---params:" + Arrays.toString(args));
            if (StrUtil.equals(method.getName(), "play")) {
                // todo 如果被代理对象有多个方法，则需要进行对每一个方法单独处理
            }
            Object result = method.invoke(target, args);
            System.out.println("动态代理对象结束....." + method.getName() + "---params:" + Arrays.toString(args));
            return result;
        });
    }


    interface Bi {
        void run();

        void play();
    }

    interface Bi1 extends Bi {
        void run();
    }

    interface Bi2 extends Bi {
        void play();
    }

    class B implements Bi1, Bi2 {
        @Override
        public void run() {
            System.out.println("B run");
        }

        @Override
        public void play() {
            System.out.println("B play");
        }
    }

    @Test
    public void test02() {
        // 如果想用jdk自带的动态代理，必须要让被代理的对象接口
        // Bi1 proxyClass2 = (Bi1) getProxyClass2(new B());
        // proxyClass2.run();
        //
        // Bi2 bi2 = (Bi2) getProxyClass2(new B());
        // bi2.play();
        // 思考一个问题： 如果被代理的对象实现了多个接口，怎么让代理对象具有所有的接口的方法
        // 解决方法： 申明一个顶级接口，包含了子接口的所有的方法，可以实现，但是很显然，这种方法不好

        // 再思考一个问题。如果被代理的对象有多个方法都需要被代理怎么操作？
        // 是不是需要在代理对象单独判断某一个方法的处理逻辑
        // method
        Bi bi = (Bi) getProxyClass2(new B());
        bi.run();
        bi.play();
    }

    @Test
    public void test01() {
        // 生成动态代理对象
        AInterface proxyClass = (AInterface) getProxyClass(new A());
        proxyClass.function1("params111111");

        AInterface proxyClass2 = (AInterface) getProxyClass2(new A());
        proxyClass2.function1("sdfadfsafdas");
    }
}
