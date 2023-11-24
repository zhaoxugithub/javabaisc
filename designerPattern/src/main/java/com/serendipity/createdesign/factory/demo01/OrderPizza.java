package com.serendipity.createdesign.factory.demo01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {

    public OrderPizza() {
        do {
            Pizza pizza = null;
            String pizzaType = getPizzaType();
            if (pizzaType.equals("cheesePizza")) {
                pizza = new CheesePizza("cheesePizza");
            } else if (pizzaType.equals("greekPizza")) {
                pizza = new GreekPizza("greekPizza");
            } else {
                System.out.println("please input greekPizza or cheesePizza");
                break;
            }
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
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
