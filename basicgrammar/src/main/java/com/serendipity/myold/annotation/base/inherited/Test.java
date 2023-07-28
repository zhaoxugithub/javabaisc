package com.serendipity.myold.annotation.base.inherited;

import java.lang.annotation.Annotation;

/**
 * ClassName Test
 * Description TODO
 * Author 11931
 * Date 2023-02-03:22:26
 * Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        Class<Student> stringClass = Student.class;
        Annotation[] annotations = stringClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.toString());
        }
    }
}
