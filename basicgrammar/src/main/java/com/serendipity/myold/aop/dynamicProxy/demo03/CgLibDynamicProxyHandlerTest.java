package com.serendipity.myold.aop.dynamicProxy.demo03;

public class CgLibDynamicProxyHandlerTest {

    public static void main(String[] args) {
        CgLibDynamicProxyHandler<ProductService> proxyHandler = new CgLibDynamicProxyHandler<>(ProductService.class);
        ProductService proxy = proxyHandler.getProxy();
        proxy.addProduct();
    }
}
