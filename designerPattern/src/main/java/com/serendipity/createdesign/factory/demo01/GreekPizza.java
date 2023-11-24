package com.serendipity.createdesign.factory.demo01;

public class GreekPizza extends Pizza {

    public GreekPizza(String name) {
        super(name);
    }

    @Override
    public void prepare() {
        System.out.println("GreekPizza prepare");
    }
}
