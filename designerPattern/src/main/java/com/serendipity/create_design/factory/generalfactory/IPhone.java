package com.serendipity.create_design.factory.generalfactory;

public class IPhone implements Phone {

    public IPhone() {
        this.make();
    }

    @Override
    public void make() {
        System.out.println("aaaa");
    }
}
