package com.serendipity.createdesign.factory.demo01;


public class CheesePizza extends Pizza {

    public CheesePizza(String name) {
        super(name);
    }

    @Override
    public void prepare() {
        System.out.println("cheesePizza prepare");
    }
}
