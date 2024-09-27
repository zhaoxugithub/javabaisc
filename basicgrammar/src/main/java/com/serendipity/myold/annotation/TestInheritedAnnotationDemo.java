package com.serendipity.myold.annotation;

import org.junit.jupiter.api.Test;

import java.lang.annotation.*;

/**
 * ClassName TestInheritedAnnotationDemo
 * Description TODO
 * Author 11931
 * Date 2023-12-03:0:57
 * Inherited注解的作用：被它修饰的Annotation将具有继承性。如果某个类使用了被@Inherited修饰的Annotation，则其子类将自动具有该注解。
 * Version 1.0
 **/
public class TestInheritedAnnotationDemo {

    @TestInheritedAnnotation(value = {"123456"}, number = 10)
    class Person {
    }

    class Student extends Person {
    }

    @Inherited // 注解可以被继承
    @Retention(RetentionPolicy.RUNTIME) // 运行期间保留
    @Target({ElementType.TYPE, ElementType.METHOD})  // 注解作用方法，类、接口、枚举类
    public @interface TestInheritedAnnotation {
        String[] value();

        int number();
    }

    @Test
    public void test() {
        Class<Student> stringClass = Student.class;
        Annotation[] annotations = stringClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}
