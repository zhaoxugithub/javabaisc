package com.serendipity.myold.lambda.ch06;

import com.serendipity.myold.lambda.ch01.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
    一、Stream 的三个操作步骤：
    1. 创建 Stream
    2. 中间操作
    3. 终止操作（终端操作）
 */
public class TestLambda6 {
    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 39, 5555.55),
            new Employee("王五", 50, 666.99),
            new Employee("赵六", 16, 6666.66),
            new Employee("田七", 8, 8888.88),
            new Employee("田七", 8, 8888.88),
            new Employee("田七", 8, 8888.88)
    );

    // 内部迭代：迭代操作由 Stream API 完成
    @Test
    public void test1() {
        employees.stream().filter((x) -> {
            System.out.println("内部迭代");
            return x.getAge() > 5;
        }).forEach(System.out::print);
    }

    // 外部迭代
    public void test2() {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee next = iterator.next();
            System.out.println(next);
        }
    }

    /*
        筛选与切片
        filter--接收 Lambda，从流中排除某些元素。
        limit--截断流，使其元素不超过给定数量。
        skip(n) -- 跳过元素，返回一个扔掉前 n 个元素的流。若流中元素不足 n 个 ，则返回一个空流。与 limit(n) 互补
        distinct -- 筛选，通过流所生成的元素的 hashCode() 和 equals() 去除重复元素
     */
    @Test
    public void test3() {
        employees.stream()
                .filter((e) -> {
                    System.out.println("短路！");//&&  ||
                    return e.getSalary() > 500;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    public void test4() {
        employees.stream()
                .filter((e) -> e.getSalary() > 5000)
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }

}
