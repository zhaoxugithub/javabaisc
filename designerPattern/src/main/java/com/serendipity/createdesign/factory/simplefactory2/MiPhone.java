package com.serendipity.createdesign.factory.simplefactory2;

public class MiPhone implements Phone {

    public MiPhone() {
        this.make();
    }

    @Override
    public void make() {
        System.out.println("bbbb");
    }
}
