package com.serendipity.myold.annotation.anno.base.target;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/11 22:27
 * FileName: Main
 * Description: com.anno.base.target
 */
public class Main {
    @Hello_01(type = 100, level = "level", value = "value")
    private int num = 10;

    @Hello_01(type = 200, level = "level_method", value = "value_method")
    public void run() {
        System.out.println("run...");
    }

    public static void main(String[] args) {
        // todo 通过反射的方法解析注解
    }
}
