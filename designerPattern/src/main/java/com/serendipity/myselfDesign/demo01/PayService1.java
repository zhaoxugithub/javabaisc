package com.serendipity.myselfDesign.demo01;

public class PayService1 extends AbstractPayService {
    @Override
    public void doPay(PayRequest payRequest) {
        System.out.println("PayService1:doPay");
    }
}
