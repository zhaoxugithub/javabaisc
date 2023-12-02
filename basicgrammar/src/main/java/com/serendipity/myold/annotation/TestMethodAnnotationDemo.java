package com.serendipity.myold.annotation;

import java.io.FileNotFoundException;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/18 10:59 上午
 * FileName: TestMethodAnnotation
 * Description: com.anno
 */
public class TestMethodAnnotationDemo {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MyMethodAnnotation {
        String title() default "";

        String description() default "";
    }

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

    @SuppressWarnings({"unchecked", "deprecation"})
    @MyMethodAnnotation(title = "test method", description = "suppress warning static method")
    public static void genericsTest() throws FileNotFoundException {
        List l = new ArrayList();
        l.add("abc");
        oldMethod();
    }

    public static void main(String[] args) {
        Method[] methods = TestMethodAnnotationDemo.class.getMethods();
        for (Method method : methods) {
            // boolean isAnnotationPresent(Class<?extends Annotation> annotationClass)
            // 判断该程序元素上是否包含指定类型的注解，存在则返回true，否则返回false。注意：此方法会忽略注解对应的注解容器。
            if (method.isAnnotationPresent(MyMethodAnnotation.class)) {
                // 获取方法上的所有注解
                Annotation[] declaredAnnotations = method.getAnnotations();
                // 遍历所有的注解
                for (Annotation declaredAnnotation : declaredAnnotations) {
                    System.out.println("Annotation in Method '" + method + "' : " + declaredAnnotation);
                }
                // 获取这个注解上的详细信息
                MyMethodAnnotation annotation = method.getAnnotation(MyMethodAnnotation.class);
                String title = annotation.title();
                System.out.println("title:" + title);
            }
        }
    }
}
