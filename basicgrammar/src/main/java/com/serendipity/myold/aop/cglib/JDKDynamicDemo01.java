package com.serendipity.myold.aop.cglib;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 使用jdk自带的方法实现实现动态代理
 * 主要依赖Proxy和InvocationHandler
 */
@SuppressWarnings("all")
public class JDKDynamicDemo01 {

    // 定义一个接口
    public interface UserService {
        void play();
    }

    // 定义一个实现类
    public class UserServiceImpl implements UserService {
        @Override
        public void play() {
            System.out.println("userServiceImpl play");
        }
    }

    // 创建一个handler
    public class ProxyHandler {
        private Object object;

        public ProxyHandler(Object object) {
            this.object = object;
        }

        public UserService getProxy() {
            return (UserService) Proxy.newProxyInstance(object.getClass().getClassLoader(), new Class[]{UserService.class}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("before.");
                    Object invoke = method.invoke(object, args);
                    System.out.println("after.");
                    return invoke;
                }
            });
        }
    }

    @Test
    public void test() {
        ProxyHandler proxyHandler = new ProxyHandler(new UserServiceImpl());
        UserService proxy = proxyHandler.getProxy();
        proxy.play();
    }

}
