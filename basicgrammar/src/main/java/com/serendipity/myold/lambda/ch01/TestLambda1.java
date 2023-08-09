package com.serendipity.myold.lambda.ch01;

import org.junit.Test;

import java.util.*;

public class TestLambda1 {
    @Test
    public void test01() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        TreeSet<Integer> treeSet = new TreeSet(comparator);
    }

    // 采用lambda
    public void test02() {
        Comparator<Integer> comparator = (o1, o2) -> o1.compareTo(o2);
        TreeSet<Integer> integers = new TreeSet<>(comparator);
    }

    public void test02_1() {
        TreeSet<Integer> integers = new TreeSet<Integer>((o1, o2) -> {
            return o1.compareTo(o2);
        });
    }

    public void test02_2() {
        TreeSet<Integer> integers = new TreeSet<Integer>(Integer::compareTo);
        TreeSet<Employee> employees1 = new TreeSet<>((Comparator.comparingInt(Employee::getAge)));
    }

    List<Employee> employees = Arrays.asList(new Employee("张三", 10, 10.00), new Employee("李四", 20, 20.00), new Employee("王五", 30, 30.00), new Employee("赵六", 40, 40.00), new Employee("田七", 50, 50.00));

    // 需求；获取当前公司中员工年龄大于30的员工信息
    public void test03() {
        List<Employee> employees = filterEmployees(this.employees);
        employees.forEach(System.out::print);
    }

    public List<Employee> filterEmployees(List<Employee> employees) {
        List<Employee> res = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAge() > 30) {
                res.add(employee);
            }
        }
        return res;
    }

    // 需求：获取当前公司中员工工资大于30的员工信息
    public void test04() {
        List<Employee> employees = filterEmployees2(this.employees);
        employees.forEach(System.out::print);
    }

    public List<Employee> filterEmployees2(List<Employee> employees) {
        List<Employee> res = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getSalary() > 30) {
                res.add(employee);
            }
        }
        return res;
    }

    //--------------采用匿名内部类表达式----策略模式----------------------------------------
    public void test05() {
        List<Employee> employees = filterEmployees3(this.employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() > 30;
            }
        });
        employees.forEach(System.out::print);
    }

    public void test06() {
        List<Employee> employees = filterEmployees3(this.employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 30;
            }
        });
        employees.forEach(System.out::print);
    }

    public List<Employee> filterEmployees3(List<Employee> employees, MyPredicate<Employee> predicate) {
        List<Employee> res = new ArrayList<>();
        for (Employee employee : employees) {
            if (predicate.test(employee)) {
                res.add(employee);
            }
        }
        return res;
    }

    // 采用lambda表达式----------------------------
    public void test07() {
        List<Employee> employees = filterEmployees3(this.employees, (e) -> e.getAge() > 30);
        employees.forEach(System.out::print);
    }

    public void test08() {
        List<Employee> employees = filterEmployees3(this.employees, (e) -> e.getSalary() > 30);
        employees.forEach(System.out::print);
    }

    // Stream 优化
    public void test09() {
        employees.stream()
                 .filter((x) -> x.getSalary() > 35)
                 .limit(1)
                 .forEach(System.out::print);
    }

    // Stream 优化+lambda
    @Test
    public void test10() {
        employees.stream()
                 .map(Employee::getName)
                 .forEach(System.out::println);
    }
}
