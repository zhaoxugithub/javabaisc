package com.serendipity.myold.lambda.ch03;

import com.serendipity.myold.lambda.ch01.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*
    一、方法引用：若 Lambda 体中的内容有方法已经实现了，我们可以使用"方法引用"
            （可以理解为方法引用是 Lambda 表达式的另外一种表现形式）
    主要有三种语法格式：
    对象::实例方法名
    类::静态方法名
    类::实例方法名
    注意：
        ①Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！
        ②若 Lambda 参数列表中的第一参数是 实例方法的调用者，而第二个参数时实例方法的参数时，可以使用 ClassName :: method
    二、构造器引用：
        格式：
        ClassName::new
        注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致！
    三、数组引用：
       Type::new;
 */

public class TestLambda3 {
    // 对象::实例方法名
    @Test
    public void test01() {
        Consumer<String> com = (x) -> System.out.print(x);
        com.accept("aaa");
        PrintStream out = System.out;
        Consumer<String> consumer = out::print;
        consumer.accept("ssss");
//      Consumer<String> consumer1 = System.out::print;
//      consumer.accept("cccc");
    }

    @Test
    public void test02() {
        Supplier<Employee> supplier1 = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };
        // 传统
        Supplier<Employee> supplier = () -> new Employee("张三", 10, 10.00);
        Employee employee = supplier.get();
        System.out.print(employee);
        // 对象::实例方法名
        Employee employee1 = new Employee("张三", 10, 10.00);
        String name = employee1.getName();
        Supplier<String> getName = employee1::getName;
        System.out.print(getName.get());
    }

    // 类::静态方法名
    @Test
    public void test03() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        int compare1 = comparator.compare(1, 2);
        System.out.println(compare1);
        Comparator<Integer> compare = Integer::compare;
        int compare2 = compare.compare(2, 3);
        System.out.println(compare2);
    }

    // 类::实例方法名
    public void test04() {
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);
        boolean test = biPredicate.test("ss", "vvv");
        System.out.println(test);
        BiPredicate<String, String> biPredicate1 = String::equals;
        boolean test1 = biPredicate1.test("aaaaa", "ccccc");
        System.out.println(test1);
    }

    // 构造器引用
    // 有参构造
    public void test05() {
        Function<Integer, Employee> function = (x) -> new Employee(x);
        Employee apply = function.apply(10);
        System.out.println(apply);
        Function<Integer, Employee> integerEmployeeFunction = Employee::new;
        Employee apply1 = integerEmployeeFunction.apply(20);
        System.out.println(apply1);
    }

    // 无参构造
    public void test06() {
        Supplier<Employee> supplier = () -> new Employee();
        Employee employee = supplier.get();
        System.out.println(employee);
        Supplier<Employee> supplier1 = Employee::new;
        Employee employee1 = supplier1.get();
        System.out.println(employee1);
    }

    // 数组
    public void test07() {
        Function<Integer, String[]> function = (x) -> new String[x];
        String[] apply = function.apply(10);
        System.out.println(apply.length);
        Function<Integer, String[]> function2 = String[]::new;
        String[] apply1 = function2.apply(20);
        System.out.println(apply1.length);
    }
}
