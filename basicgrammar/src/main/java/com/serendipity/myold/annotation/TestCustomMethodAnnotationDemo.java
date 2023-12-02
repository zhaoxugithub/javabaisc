package com.serendipity.myold.annotation;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName TestMethodAnnotation
 * Description TODO
 * Author 11931
 * Date 2023-02-03:23:01
 * Version 1.0
 **/
@Slf4j
@SuppressWarnings("all")
public class TestCustomMethodAnnotationDemo {

    @Override
    @MyMethodAnnotation(title = "toStringMethod", description = "override toString method")
    public String toString() {
        return "Override toString method";
    }

    @Deprecated
    @MyMethodAnnotation(title = "old static method", description = "deprecated old static method")
    public static void oldMethod() {
        System.out.println("old method, don't use it.");
    }

    @SuppressWarnings("all")
    @MyMethodAnnotation(title = "test method", description = "suppress warning static method")
    public static void genericsTest() throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        list.add("abc");
        oldMethod();
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface MyMethodAnnotation {
        String title() default "";

        String description() default "";
    }

    /**
     * 通过反射的反射获取哪些类或者方法上标注了哪些注解
     */
    @Test
    public void test1() throws ClassNotFoundException {
        ClassLoader classLoader = TestCustomMethodAnnotationDemo.class.getClassLoader();
        log.info("classLoader ={}", classLoader);
        // 获取自定义注解的class对象
        Class<?> aClass = classLoader.loadClass("com.old.annotation.custom.TestMethodAnnotation");
        log.info("class = {}", aClass);
        // 获取注解的所有方法
        Method[] methods = aClass.getMethods();
        // Arrays.stream(methods).forEach(System.out::println);
        for (Method method : methods) {
            // 判断method方法上是否含有MyMethodAnnotation类型的注解
            if (method.isAnnotationPresent(TestCustomMethodAnnotationDemo.MyMethodAnnotation.class)) {
                // 获取方法上的注解（排除继承父类的）
                for (Annotation declaredAnnotation : method.getDeclaredAnnotations()) {
                    log.info("Annotation is Method :" + method + ":" + declaredAnnotation);
                }
                // 获取这个注解的详细信息
                TestCustomMethodAnnotationDemo.MyMethodAnnotation annotation = method.getAnnotation(TestCustomMethodAnnotationDemo.MyMethodAnnotation.class);
                log.info("annotition title = {},description = {}", annotation.title(), annotation.description());
            }
        }
    }
}
