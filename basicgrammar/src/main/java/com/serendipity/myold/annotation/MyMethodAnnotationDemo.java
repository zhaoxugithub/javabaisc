package com.serendipity.myold.annotation;

import com.serendipity.myold.annotation.annotation.MyMethodAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
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
public class MyMethodAnnotationDemo {

    @Override
    @MyMethodAnnotation(title = "toStringMetod", description = "override toString method")
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

    /**
     * 通过反射的反射获取哪些类或者方法上标注了哪些注解
     */
    @Test
    public void test1() throws ClassNotFoundException {
        ClassLoader classLoader = MyMethodAnnotationDemo.class.getClassLoader();
        // 获取所有已加载的包
        Arrays.stream(classLoader.getDefinedPackages()).forEach(System.out::println);
        // 获取自定义注解的class对象,不会执行初始化方法
        Class<?> aClass = classLoader.loadClass("com.serendipity.myold.annotation.MyMethodAnnotationDemo");
        // 获取注解的所有方法
        Method[] methods = aClass.getMethods();
        Arrays.stream(methods)
                .forEach(method -> {
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
                });
    }

    @Test
    public void test2() {
        // 直接获取类引用，避免反射加载
        Class<?> targetClass = MyMethodAnnotationDemo.class;
        Arrays.stream(targetClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(MyMethodAnnotation.class))
                .forEach(method -> {
                    MyMethodAnnotation annotation = method.getAnnotation(MyMethodAnnotation.class);
                    // 合并注解处理与日志输出
                    Arrays.stream(method.getDeclaredAnnotations())
                            .forEach(declaredAnnotation ->
                                    log.info("Annotation on Method {}: {}", method, declaredAnnotation));
                    log.info("Annotation details - title: {}, description: {}",
                            annotation.title(), annotation.description());
                });
        // 移除已兼容的包打印逻辑
    }
}
