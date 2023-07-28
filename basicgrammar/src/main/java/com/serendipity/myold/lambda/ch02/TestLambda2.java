package com.serendipity.myold.lambda.ch02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * JAVA8  内置的四大核心函数式接口
 * <p>
 * <p>
 * Consumer<T> 消费性接口
 * void accept(T t);
 * <p>
 * Supplier<T> 供给型接口
 * T get()
 * <p>
 * Function<T,R> 函数型接口
 * R apply(T t)
 * <p>
 * Predicate<T> 断言型接口
 */
public class TestLambda2 {
    // 断言型接口
    public void test4() {
        List<String> strings = Arrays.asList("hello", "atguigu", "Lambda", "www", "ok");
        filterStr(strings, (x) -> x.length() > 3).forEach(System.out::print);
    }

    public List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> list1 = new ArrayList<>();
        for (String str : list) {
            if (predicate.test(str)) {
                list1.add(str);
            }
        }
        return list1;
    }

    // 函数式接口
    public void test03() {
        String xxx = filterStr2("xxx", (x) -> x.toUpperCase());
        System.out.println(xxx);
    }

    public String filterStr2(String str, Function<String, String> function) {
        return function.apply(str);
    }

    // 供给型接口
    public void test02() {
        filterStr3(10, () -> (int) (Math.random() * 100)).forEach(System.out::print);
    }

    public List<Integer> filterStr3(int nu, Supplier<Integer> supplier) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nu; i++) {
            Integer integer = supplier.get();
            res.add(integer);
        }
        return res;
    }

    // 消费性接口
    @Test
    public void test01() {
        filterStr4("zzz", System.out::println);
    }

    public void filterStr4(String str, Consumer<String> consumer) {
        consumer.accept(str);
    }
}
