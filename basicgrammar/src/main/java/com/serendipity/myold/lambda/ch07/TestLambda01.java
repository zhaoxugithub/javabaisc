package com.serendipity.myold.lambda.ch07;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/20 16:59
 * FileName: TestLambda01
 * Description: com.lambda.ch07
 */
public class TestLambda01 {

    private class Person {
        private String name;
        private String message;

        Person() {
        }

        Person(String name, String message) {
            this.name = name;
            this.message = message;
        }

        public String getName() {
            return name;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return "Person{" + "name='" + name + '\'' + ", message='" + message + '\'' + '}';
        }
    }

    final List<Person> people = Arrays.asList(new Person("p1", "p1 message"), new Person("p1", "p2 message2222"),
            new Person("p2", "p2 message"), new Person("p3", "p3 message"), new Person("p4", "p4 message"));

    public void test01() {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4);
        // 下面这两种方式是等价的
        list.forEach(n -> System.out.println(n));
        list.forEach(System.out::println);
        // list.forEach((String s) -> System.out.println(s));
        int i = 9;
        // 变量不可修改
        // list.forEach(s -> i++);
    }

    @Test
    public void test02() {
        final List<Person> p1 = people.stream()
                                      .filter(f -> f.getName()
                                                    .equals("p1"))
                                      .collect(Collectors.toList());
        System.out.println(p1.get(0));
    }

    // 顾名思义，当使用顺序方式去遍历时，每个item读完后再读下一个item。而使用并行去遍历时，数组会被分成多个段，其中每一个都在不同的线程中处理，然后将结果一起输出
    @Test
    public void test03() {
        // 顺序流
        // final List<Person> collect = people.stream().toList();
        // 并行流
        // final List<Person> collect1 = people.stream().parallel().toList();
    }

    // stream与parallelStream性能测试对比
    @Test
    public void test04() {
        long t0 = System.nanoTime();
        // 初始化一个范围100万整数流,求能被2整除的数字，toArray()是终点方法
        // 这里是用并行流来计算
        int b[] = IntStream.range(0, 1_000_000_000)
                           .parallel()
                           .filter(p -> p % 2 == 0)
                           .toArray();
        long t1 = System.nanoTime();
        int a[] = IntStream.range(0, 1_000_000_000)
                           .filter(p -> p % 2 == 0)
                           .toArray();
        long t2 = System.nanoTime();
        // 我本机的结果是serial: 0.06s, parallel 0.02s，证明并行流确实比顺序流快
        System.out.printf("serial: %.2fs, parallel %.2fs%n", (t1 - t0) * 1e-9, (t2 - t1) * 1e-9);
    }

    // 方法引用
    @Test
    public void test05() {
        Supplier<Person> p = () -> new Person("p2", "message");
        Supplier<Person> p2 = Person::new;
    }

    @Test
    public void test06() {
        List<String> strings = Arrays.asList("java", "python", "goland", "c++");
        // 策略模式
        filter(strings, (str) -> str.startsWith("j"));
        filter(strings, str -> str.startsWith("g"));
        filter(strings, str -> str.length() > 5);
    }


    @Test
    public void test07() {
        Predicate<Person> p1 = (x) -> x.getName()
                                       .equals("p1");
        Predicate<Person> p2 = (x) -> x.getMessage()
                                       .equals("p1 message");
        people.stream()
              .filter(p1.and(p2))
              .forEach(System.out::println);
    }

    public void test08() {
        IntStream.range(0, 1000)
                 .map(x -> (x * 12));
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
    }

    public void filter(List<String> names, Predicate<String> condition) {
        names.stream()
             .filter(condition)
             .forEach((name) -> {
                 System.out.println(name + " ");
             });
    }
}
