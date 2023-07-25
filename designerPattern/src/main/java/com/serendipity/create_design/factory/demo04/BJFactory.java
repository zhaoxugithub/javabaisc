package com.serendipity.create_design.factory.demo04;


public class BJFactory implements AbsFactory {

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza = new BJCheesePizza("北京cheesePizza");
        } else if ("pepper".equals(orderType)) {
            pizza = new BJPepperPizza("北京pepperPizza");
        }
        return pizza;
    }
}
