package com.serendipity.ch01;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
class Cat {
    private Integer id;
    private String name;
    private String color;
    private Long time;

    // private Cat() {
    // }
    public Cat() {
    }

    private static Cat cat = new Cat();

    // 这是一种饿汉式的单例模式
    // 也是通过静态方法代替构造器实现静态方法
    public static Cat getInstance() {
        return cat;
    }

    //------------------------------------------
    private Cat(Integer id) {
        this.id = id;
    }

    private Cat(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Map<String, Cat> catMap = new HashMap<>();

    // 每次返回只会相同的name只会创建一个对象实例，并且返回
    public static Cat getInstance(String name) {
        // 使用静态方法代替构造方法是对参数进行判断，根据参数要求返回实例
        if (name == null) return null;
        return catMap.computeIfAbsent(name, cat -> new Cat(1, name));
    }
}
