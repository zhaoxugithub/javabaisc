package com.serendipity.myold.base;

import java.util.Optional;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/20 11:08
 * FileName: OptionaDemo
 * Description: com.java8.base
 */
public class OptionalDemo {
    public static void main(String[] args) {
        // 断言
        Optional<String> fullName = Optional.ofNullable(null);
        // 判断是否为空，空就是false
        System.out.println("Full Name is set?" + fullName.isPresent());
        // 如果是空取None,否则就是fullName
        System.out.println("Full Name :" + fullName.orElse("[none]"));
        // 如果是空就拼接，否则就是hey Stranger
        System.out.println(fullName.map(s -> "Hey " + s + "|").orElse("Hey Stranger"));
        final Optional<String> firstName = Optional.of("Tom");
        System.out.println("First Name is set? " + firstName.isPresent());
        System.out.println("First Name : " + firstName.orElseGet(() -> "[None]"));
        System.out.println(firstName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
    }
}
