package com.serendipity.myold.base;

import java.util.Arrays;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/20 0:32
 * FileName: Lambda
 * Description: com.java8.base
 */
public class Lambda {
    public static void main(String[] args) {
        Arrays.asList("a", "b", "d").forEach(System.out::println);
    }
}
