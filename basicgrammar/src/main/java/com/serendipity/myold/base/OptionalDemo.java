package com.serendipity.myold.base;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        logPresenceAndValue(fullName, "Full Name");

        // 如果不为空就拼接，否则就是hey Stranger
        log.info("Greeting: {}", fullName.map(s -> "Hey " + s + "|").orElse("Hey Stranger"));

        final Optional<String> firstName = Optional.of("Tom");
        logPresenceAndValue(firstName, "First Name");
        log.info("Greeting: {}", firstName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
    }

    private static void logPresenceAndValue(Optional<String> optional, String label) {
        boolean isPresent = optional.isPresent();
        log.info("{} is set? {}", label, isPresent);
        log.info("{} : {}", label, isPresent ? optional.get() : "[none]");
    }
}
