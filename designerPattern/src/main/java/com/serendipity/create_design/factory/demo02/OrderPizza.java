package com.serendipity.create_design.factory.demo02;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {

    public OrderPizza(SimpleFactoryPizza factory) {
        setFactory(factory);
    }

    private void setFactory(SimpleFactoryPizza factory) {
        do {
            String pizzaType = getPizzaType();
            Pizza pizza = factory.createPizza(pizzaType);
            if (pizza != null) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                System.out.println("订购披萨");
                break;
            }
        } while (true);
    }

    //获取pizza种类
    public String getPizzaType() {
        String name = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza 种类：");
            name = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }
}
