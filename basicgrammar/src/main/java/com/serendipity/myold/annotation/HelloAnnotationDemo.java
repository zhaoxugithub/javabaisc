package com.serendipity.myold.annotation;

import com.serendipity.myold.annotation.annotation.Hello2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * ClassName HelloAnnotationDemo
 * Description TODO
 * Author 11931
 * Date 2023-12-02:23:49
 * Version 1.0
 **/
@Slf4j
@SuppressWarnings("all")
public class HelloAnnotationDemo {
    @Hello2(type = 100, level = "level", value = "value")
    private int num = 10;

    @Hello2(type = 200, level = "level_method", value = "value_method")
    public void run() {
        System.out.println("run...");
    }

    @Test
    public void test() {
        // todo 解析
    }
}
