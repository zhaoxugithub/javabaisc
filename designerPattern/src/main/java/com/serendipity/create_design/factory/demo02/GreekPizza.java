package com.serendipity.create_design.factory.demo02;

public class GreekPizza  extends Pizza {

    public GreekPizza(String name) {
        super(name);
    }

    @Override
    public void prepare() {
        System.out.println("GreekPizza prepare");
    }
}
