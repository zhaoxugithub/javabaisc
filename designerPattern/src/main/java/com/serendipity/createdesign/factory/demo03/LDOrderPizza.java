package com.serendipity.createdesign.factory.demo03;

public class LDOrderPizza extends OrderPizza {

    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza = new LDCheesePizza("伦敦cheesePizza");
        } else if ("pepper".equals(orderType)) {
            pizza = new LDCheesePizza("伦敦pepperPizza");
        }
        return pizza;
    }
}
