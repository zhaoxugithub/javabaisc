package com.serendipity.myold.lambda.ch02;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

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
@Slf4j
@SuppressWarnings("all")
public class TestLambda2 {
    // 断言型接口
    public void test4() {
        List<String> strings = Arrays.asList("hello", "atguigu", "Lambda", "www", "ok");
        filterStr(strings, (x) -> x.length() > 3).forEach(System.out::print);
    }

    // 一般这种函数会跟着 一个元素或者列表和一个函数式接口
    public List<String> filterStr(List<String> list, Predicate<String> predicate) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }

        // 使用Stream API简化过滤操作
        return list.stream()
                .filter(predicate)
                .collect(java.util.stream.Collectors.toList());
    }

    // 函数式接口
    public void test03() {
        String xxx = filterStr2("xxx", (x) -> x.toUpperCase());
        log.info(xxx);
    }

    public String filterStr2(String str, Function<String, String> function) {
        return function.apply(str);
    }

    // 供给型接口
    public void test02() {
        filterStr3(10, () -> (int) (Math.random() * 100)).forEach(System.out::print);
    }

    public List<Integer> filterStr3(int count, Supplier<Integer> supplier) {
        // 参数校验
        if (count < 0) {
            throw new IllegalArgumentException("数量不能为负数");
        }
        // 预先分配合适大小的列表
        List<Integer> result = new ArrayList<>(count);
        // 使用Stream API优化生成过程
        return IntStream.range(0, count)
                .mapToObj(i -> supplier.get())
                .collect(java.util.stream.Collectors.toList());
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
