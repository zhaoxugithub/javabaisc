package com.serendipity.create_design.factory.demo02;

public class SimpleFactoryPizza {
    public Pizza createPizza(String pizzaType) {
        Pizza pizza = null;
        if ("greek".equals(pizzaType)) {
            pizza = new GreekPizza("greekPizza");
        } else if ("cheese".equals(pizzaType)) {
            pizza = new CheesePizza("cheesePizza");
        } else if ("pepper".equals(pizzaType)) {
            pizza = new PepperPizza("pepperPizza");
        }
        return pizza;
    }

    public static Pizza createPizza2(String pizzaType) {
        Pizza pizza = null;
        if ("greek".equals(pizzaType)) {
            pizza = new GreekPizza("greekPizza");
        } else if ("cheese".equals(pizzaType)) {
            pizza = new CheesePizza("cheesePizza");
        } else if ("pepper".equals(pizzaType)) {
            pizza = new PepperPizza("pepperPizza");
        }
        return pizza;
    }
}
