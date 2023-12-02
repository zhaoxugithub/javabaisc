package com.serendipity.myold.annotation.anno;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.annotation.*;

/**
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
     * @Inherited 具有继承
     * @Retention(RetentionPolicy.RUNTIME) 在运行时有效
     * @Target({ElementType.TYPE, ElementType.METHOD})作用于方法和类上
     */
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
            Class<Student> studentClass = Student.class;
            // 获取Student上的所有注解（注解有继承）
            Annotation[] annotations = studentClass.getAnnotations();
            for (Annotation annotation : annotations) {
                log.info("annotation ={}", annotation);
            }
        }
    }

    @Test
    public void test() {
        new Student().test();
    }
}


