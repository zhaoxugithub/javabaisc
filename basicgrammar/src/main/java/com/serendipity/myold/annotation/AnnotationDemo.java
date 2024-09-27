package com.serendipity.myold.annotation;

import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

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


    // Builder注解 只能用于 静态内部类 或者外部类
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @TestInheritedAnnotation(values = {"value"}, number = 10)
    class Person {
        private String name;
        private int age;
        public String remark;
        public String email;

        public void sayHello() {
            System.out.println("Hello, " + name + "!");
        }

        private void fun() {
            System.out.println("fun");
        }
    }

    class Student extends Person {
        private String name;
        private int age;
        public String remark;


        public void sayHello() {
            System.out.println("Student Hello, " + name + "!");
        }

        private void fun() {
            System.out.println("Student fun");
        }
    }


    class Teacher {
        private String name;
        private int age;

        Teacher() {

        }
    }

    static MockConfig config = null;

    static {
        config = new MockConfig().globalConfig()
                                 .setEnabledStatic(true)
                                 .setEnabledCircle(true)
                                 .setEnabledPrivate(true)
                                 .setEnabledPublic(true)
                                 .setEnabledProtected(true);
    }

    @Test
    public void test() {

    }

    public static void main(String[] args) {
        // Person person = JMockData.mock(new TypeReference<Person>() {
        // }, config);
        // System.out.println(person);

        Teacher mock = JMockData.mock(AnnotationDemo.Teacher.class, config);
        System.out.println(mock);
    }
}


