package com.serendipity.myold.lambda.ch04;


import com.serendipity.myold.lambda.ch01.Employee;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/*

1.  Optional 容器类的常用方法
    Optional.of(T t): 创建一个Optional 实例
    Optional.empty()：创建一个空的Optional 实例
    Optional.ofNullable(T t)  若t不为null，创建Optional实例，否则创建空实例

    isPresent():判断是否包含值
    orElse(T t)：如果调用对象包含值，返回该值,否则返回t
    orElseGet(Supplier s):如果调用对象包含值，返回该值，否则返回s获取的值
    flatMap(Function mapper):与map类似，要求返回值必须是Optional
 */
public class TestLambda4 {

    @Test
    public void test01() {
        Optional<Employee> employee = Optional.of(null);
        System.out.println(employee.get());
    }

    @Test
    public void test02() {
        Optional<Employee> empty = Optional.empty();
        System.out.println(empty.get());
    }

    @Test
    public void test03() {
        Optional<Employee> employee = Optional.ofNullable(new Employee());
        boolean present = employee.isPresent();
        System.out.println(present);
    }

    @Test
    public void test04() {
        Optional<Employee> op = Optional.ofNullable(new Employee("张三", 18, 888.88));
        Optional<String> str = op.map((e) -> e.getName());
        System.out.println(str.get());
        Optional<String> str2 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(str2.get());
    }

    @Test
    public void test05() {
        Optional<Object> o = Optional.ofNullable(null);
        System.out.println(o.get());
    }
}
