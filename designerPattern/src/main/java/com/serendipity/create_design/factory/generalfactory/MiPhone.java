package com.serendipity.create_design.factory.generalfactory;

public class MiPhone implements Phone {

    public MiPhone() {
        this.make();
    }

    @Override
    public void make() {
        System.out.println("bbbb");
    }
}
