package com.serendipity.create_design.factory.demo04;


public class LDFactory implements AbsFactory {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza = new LDCheesePizza("伦敦cheesePizza");
        } else if ("pepper".equals(orderType)) {
            pizza = new LDCheesePizza("伦敦pepperPizza");
        }
        return pizza;
    }
}
