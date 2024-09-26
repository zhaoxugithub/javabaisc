package com.serendipity.myold.annotation;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * https://www.pdai.tech/md/java/basic/java-basic-x-annotation
 * .html#java-%E5%9F%BA%E7%A1%80---%E6%B3%A8%E8%A7%A3%E6%9C%BA%E5%88%B6%E8%AF%A6%E8%A7%A3
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/18 10:30 上午
 * FileName: TestDocAnnotation
 * Description: com.anno
 * Documented注解的作用是：描述在使用 javadoc 工具为类生成帮助文档时是否要保留其注解信息。
 *
 * @Target({ElementType.FIELD, ElementType.METHOD}):作用于方法和变量上
 */

@Slf4j
@SuppressWarnings("all")
public class DocAnnotationDemo {
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Target({ElementType.FIELD, ElementType.METHOD})
    public @interface TestDocAnnotation {
        public String value() default "default";
    }

    class A {
        @TestDocAnnotation("dsdasd")
        public void test1() {
        }

        @Test
        @TestDocAnnotation("test2")
        public void test2() {
        }

        @Test
        public void test03() {
        }
    }

    @Test
    public void test() {
        Arrays.stream(A.class.getMethods())
              .filter(method -> method.isAnnotationPresent(TestDocAnnotation.class))
              .forEach(method -> {
                  log.info("method ={}", method.getName());
                  System.out.println(Arrays.toString(method.getAnnotations()));
              });
    }
}