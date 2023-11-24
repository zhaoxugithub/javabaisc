package com.serendipity.createdesign.factory.simplefactory2;

public class IPhone implements Phone {

    public IPhone() {
        this.make();
    }

    @Override
    public void make() {
        System.out.println("aaaa");
    }
}
