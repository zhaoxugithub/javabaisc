package com.serendipity.myselfDesign.demo01;

public class PayService2 extends AbstractPayService {
    @Override
    public void doPay(PayRequest payRequest) {
        System.out.println("PayService2:doPay");
    }
}
