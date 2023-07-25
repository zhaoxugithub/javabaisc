package com.serendipity.myold.annotation.custom;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * ClassName Main
 * Description TODO
 * Author 11931
 * Date 2023-02-03:23:08
 * Version 1.0
 **/
@SuppressWarnings("all")
@Slf4j
public class Main {
    /**
     * 通过反射的反射获取哪些类或者方法上标注了哪些注解
     */
    public static void test1() throws ClassNotFoundException {
        ClassLoader classLoader = TestMethodAnnotation.class.getClassLoader();
        log.info("classLoader ={}", classLoader);
        // 获取自定义注解的class对象
        Class<?> aClass = classLoader.loadClass("com.old.annotation.custom.TestMethodAnnotation");
        log.info("class = {}", aClass);
        // 获取注解的所有方法
        Method[] methods = aClass.getMethods();
        // Arrays.stream(methods).forEach(System.out::println);
        for (Method method : methods) {
            // 判断method方法上是否含有MyMethodAnnotation类型的注解
            if (method.isAnnotationPresent(MyMethodAnnotation.class)) {
                // 获取方法上的注解（排除继承父类的）
                for (Annotation declaredAnnotation : method.getDeclaredAnnotations()) {
                    log.info("Annotation is Method :" + method + ":" + declaredAnnotation);
                }
                // 获取这个注解的详细信息
                MyMethodAnnotation annotation = method.getAnnotation(MyMethodAnnotation.class);
                log.info("annotition title = {},description = {}", annotation.title(), annotation.description());
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        test1();
    }
}
