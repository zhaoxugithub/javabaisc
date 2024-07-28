package com.serendipity.myselfDesign.demo01;

public abstract class AbstractPayService implements PayService {
    @Override
    public void pay(PayRequest payRequest) {
        // 校验
        validateRequest(payRequest);
        doPay(payRequest);
        postPay(payRequest);
    }


    public void validateRequest(PayRequest payRequest) {
        System.out.println("AbstractPayService:validateRequest");
    }

    public abstract void doPay(PayRequest payRequest);

    private void postPay(PayRequest payRequest) {
        System.out.println("AbstractPayService:postPay");
    }
}
