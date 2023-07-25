package com.serendipity.myold.annotation.anno;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/18 10:41 上午
 * FileName: Person
 * Description: com.anno
 */
@TestInheritedAnnotation(values = {"value"}, number = 10)
public class Person {
}

@Slf4j
class Student extends Person {
    public void test() {
        Class<Student> studentClass = Student.class;
        // 获取Student上的所有注解（注解有继承）
        Annotation[] annotations = studentClass.getAnnotations();
        for (Annotation annotation : annotations) {
            log.info("annotation ={}", annotation);
        }
    }

    public static void main(String[] args) {
        new Student().test();
    }
}
