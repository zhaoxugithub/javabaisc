package com.serendipity.myold.aop.cglib;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

/**
 * 使用jdk自带的方法实现实现动态代理
 * 主要依赖Proxy和InvocationHandler
 */
@Slf4j
@SuppressWarnings("all")
public class DynamicProxyDemo {
    // 定义一个接口
    public interface UserService {
        void play();
    }

    // 定义一个实现类
    public static class UserServiceImpl implements UserService {
        @Override
        public void play() {
            System.out.println("userServiceImpl play");
        }
    }

    // 创建一个handler
    public static class ProxyHandler {
        private final Object object;

        public ProxyHandler(Object object) {
            this.object = object;
        }

        public UserService getProxy() {
            return (UserService) Proxy.newProxyInstance(object.getClass()
                            .getClassLoader(),

                    new Class[]{UserService.class},

                    (proxy, method, args) -> {
                        log.info("proxy:{},method: {}, args: {}", proxy.getClass()
                                .getSimpleName(), method.getName(), args);

                        Object invoke = method.invoke(object, args);
                        log.info("after invoke");
                        return invoke;
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
