package com.serendipity.myold.lambda.ch07;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/20 22:19
 * FileName: TestLambda02
 * Description: com.lambda.ch07
 */
public class TestLambda02 {


    private class Person {
        private String name;
        private String message;
        private Integer age;

        Person() {

        }

        Person(String name, String message, Integer age) {
            this.name = name;
            this.message = message;
            this.age = age;
        }


        public Integer getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public String getMessage() {
            return message;
        }


        public void setAge(Integer age) {
            this.age = age;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" + "name='" + name + '\'' + ", message='" + message + '\'' + '}';
        }
    }


    final List<Person> people = Arrays.asList(new Person("p1", "p1 message", 10), new Person("p1", "p2 message2222", 20), new Person("p2", "p2 message", 30), new Person("p3", "p3 message", 40), new Person("p4", "p4 message", 50));


    @Test
    public void test01() {
        final List<Integer> list = Arrays.asList(100, 200, 300, 400, 500);
        final Double aDouble = list.stream()
                                   .map((cost) -> cost + .12 * cost)
                                   .reduce(Double::sum)
                                   .get();
        System.out.println(aDouble);
    }

    @Test
    public void test02() {
        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");
        final String collect = G7.stream()
                                 .map(x -> x.toUpperCase())
                                 .collect(Collectors.joining(", "));
        System.out.println(collect);
    }

    // FlatMap
    @Test
    public void test03() {
        final List<Integer> collect = Stream.of(Arrays.asList(1, 3), Arrays.asList(5, 6))
                                            .flatMap(a -> a.stream())
                                            .collect(Collectors.toList());
        System.out.println(collect);
    }

    // distinct
    @Test
    public void test04() {
        final List<String> collect = people.stream()
                                           .map(Person::getName)
                                           .distinct()
                                           .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test05() {
        final long count = people.stream()
                                 .filter(p -> p.getAge() > 30)
                                 .count();
        System.out.println(count);
    }
}
