package com.serendipity.myold.aop.dynamicProxy.demo03;

import org.junit.jupiter.api.Test;

public class CgLibDynamicProxyHandlerTest {

    class ProductService {
        public void addProduct() {
            System.out.println("add product....");
        }
    }

    @Test
    public void test() {
        CgLibDynamicProxyHandler<ProductService> proxyHandler = new CgLibDynamicProxyHandler<>(ProductService.class);
        ProductService proxy = proxyHandler.getProxy();
        proxy.addProduct();
    }
}
