package com.serendipity.myold.annotation;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.annotation.*;

/**
 * 学习参考： https://www.pdai.tech/md/java/basic/java-basic-x-annotation.html
 * <p>
 * <p>
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/18 10:41 上午
 * FileName: Person
 * Description: com.anno
 */
@Slf4j
@SuppressWarnings("all")
public class AnnotationDemo {

    /*
       这四个是元注解:
        是否可以继承;
        注解保留范围：运行期，编译期，源文件期间
        作用范围： 方法，类，方法参数等等
     * @Documented
     * @Inherited 具有继承
     * @Retention(RetentionPolicy.RUNTIME) 在运行时有效
     * @Target({ElementType.TYPE, ElementType.METHOD})作用于方法和类上
     */
    @Documented
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    public @interface TestInheritedAnnotation {
        String[] values();

        int number();
    }

    @TestInheritedAnnotation(values = {"value"}, number = 10)
    class Person {
    }

    class Student extends Person {
        public void test() {
        }
    }

    @Test
    public void test() {
        // 获取Student上的所有注解（注解有继承）
        log.info("获取Student上的所有注解:{}", Student.class.getAnnotations());
    }
}


