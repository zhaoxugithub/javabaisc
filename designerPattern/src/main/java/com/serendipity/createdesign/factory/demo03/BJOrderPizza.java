package com.serendipity.createdesign.factory.demo03;

public class BJOrderPizza extends OrderPizza {
    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza = new BJCheesePizza("北京cheesePizza");
        } else if ("pepper".equals(orderType)) {
            pizza = new BJPepperPizza("北京pepperPizza");
        }
        return pizza;
    }
}
