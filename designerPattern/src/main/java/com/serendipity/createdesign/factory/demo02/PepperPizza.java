package com.serendipity.createdesign.factory.demo02;

public class PepperPizza extends Pizza {

    public PepperPizza(String name) {
        super(name);
    }

    @Override
    public void prepare() {
        System.out.println("PepperPizza prepare");
    }
}
