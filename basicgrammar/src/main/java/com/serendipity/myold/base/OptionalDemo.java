package com.serendipity.myold.base;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/20 11:08
 * FileName: OptionaDemo
 * Description: com.java8.base
 */
@Slf4j
@SuppressWarnings("all")
public class OptionalDemo {
    public static void main(String[] args) {
        // 断言
        Optional<String> fullName = Optional.ofNullable(null);
        // 判断是否为空，空就是false
        log.info("Full Name is set?" + fullName.isPresent());
        // 如果是空取None,否则就是fullName
        log.info("Full Name :" + fullName.orElse("[none]"));
        // 如果不为空就拼接，否则就是hey Stranger
        log.info(fullName.map(s -> "Hey " + s + "|").orElse("Hey Stranger"));
        final Optional<String> firstName = Optional.of("Tom");
        log.info("First Name is set? " + firstName.isPresent());
        log.info("First Name : " + firstName.orElseGet(() -> "[None]"));
        log.info(firstName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
    }
}
