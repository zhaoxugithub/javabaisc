package com.serendipity.myold.annotation;

import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import com.serendipity.myold.annotation.entity.Person;
import com.serendipity.myold.annotation.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;

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
public class AnnotationDemo {

    private static MockConfig config = null;

    static {
        config = new MockConfig()
                .globalConfig()
                .setEnabledStatic(true)
                .setEnabledCircle(true)
                .setEnabledPrivate(true)
                .setEnabledPublic(true)
                .setEnabledProtected(true);
    }

    @Test
    public void test1() {
        Person person = JMockData.mock(Person.class, config);
        person.sayHello();
        System.out.println(person);

        Student student = JMockData.mock(Student.class, config);
        student.sayHello();
        System.out.println(student);
    }

    @Test
    public void test2() {
        Person person = new Person();
        System.out.println(person);
        System.out.printf("Person name: %s, age: %d, remark: %s, email: %s\n",
                person.getName(), Person.age, person.getRemark(), person.getEmail());

        Student student = new Student();
        System.out.println(student);

        System.out.printf("Student name: %s, age: %d, remark: %s, email: %s\n",
                student.getName(), Student.age, student.getRemark(), student.getEmail());
        System.out.printf("Student name: %s, age: %d, remark: %s, email: %s\n",
                student.getName(), Student.age, student.remark, student.email);
    }

    @Test
    public void test3() {
        Arrays.stream(Person.class.getAnnotations()).forEach(System.out::println);
        Arrays.stream(Student.class.getAnnotations()).forEach(System.out::println);
    }
}


