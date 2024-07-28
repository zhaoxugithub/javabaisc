package com.serendipity.myselfDesign.demo01;

public class PayService3 extends AbstractPayService {
    @Override
    public void doPay(PayRequest payRequest) {
        System.out.println("PayService3:doPay");
    }
}
