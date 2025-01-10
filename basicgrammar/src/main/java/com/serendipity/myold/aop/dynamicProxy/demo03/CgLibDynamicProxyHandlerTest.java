package com.serendipity.myold.aop.dynamicProxy.demo03;

import org.junit.jupiter.api.Test;

public class CgLibDynamicProxyHandlerTest {

    @Test
    public void test01() {
        CgLibDynamicProxyHandler<ProductService> proxyHandler = new CgLibDynamicProxyHandler<>(ProductService.class);
        ProductService proxy = proxyHandler.getProxy();
        proxy.addProduct();
    }

    @Test
    public void test02() {
        CgLibDynamicProxyHandler<PersonService> personProxy = new CgLibDynamicProxyHandler<>(PersonService.class);
        PersonService personServiceProxy = personProxy.getProxy();
        personServiceProxy.run();
        // 静态方法AOP不生效
        PersonService.play();
    }
}
