package com.serendipity.create_design.factory.simplefactory;

import java.util.Map;

class Common {
    public static Phone fullParams(Map<String, String> params) {
        String name = params.get("name");
        String size = params.get("size");
        String brand = params.get("brand");
        String color = params.get("color");
        String money = params.get("money");
        return new Phone(name, size, brand, color, money);
    }
}
