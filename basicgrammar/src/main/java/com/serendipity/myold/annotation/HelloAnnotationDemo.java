package com.serendipity.myold.annotation;

import com.serendipity.myold.annotation.annotation.Hello2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
        // 获取注解字段和方法上注解信息
        Method[] declaredMethods = HelloAnnotationDemo.class.getDeclaredMethods();
        Field[] fields = HelloAnnotationDemo.class.getFields();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.isAnnotationPresent(Hello2.class)) {
                Hello2 annotation = declaredMethod.getAnnotation(Hello2.class);
                System.out.printf(String.format("type:%s,level:%s,value:%s", annotation.type(), annotation.level(), annotation.value()));
            }
        }
        for (Field field : fields) {
            if (field.isAnnotationPresent(Hello2.class)) {
                Hello2 annotation = field.getAnnotation(Hello2.class);
                System.out.printf(String.format("type:%s,level:%s,value:%s", annotation.type(), annotation.level(), annotation.value()));
            }
        }
    }
}
