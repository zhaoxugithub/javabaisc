package com.serendipity.myselfDesign.demo01;

// test
public class Main {

    public static void main(String[] args) {
        testAbstractPayService(new PayService1());
        testAbstractPayService(new PayService2());
        testAbstractPayService(new PayService3());
    }

    public static void testAbstractPayService(AbstractPayService abstractPayService) {
        abstractPayService.pay(new PayRequest());
    }
}

