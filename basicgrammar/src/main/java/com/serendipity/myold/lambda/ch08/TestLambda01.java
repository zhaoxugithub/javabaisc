package com.serendipity.myold.lambda.ch08;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ClassName TestLambda01
 * Description TODO
 * Author 11931
 * Date 2023-08-07:1:46
 * Version 1.0
 * <p>
 * https://www.51cto.com/article/714088.html
 **/
public class TestLambda01 {
    @Test
    public void test01() {
        // 有输入无输出
        // 定义消费类型的函数式接口,返回的是函数式接口
        Consumer<String> consumer = (i) -> System.out.println("this is " + i);
        consumer.accept("consumer");
        // 无输入有输出
        // 定义的是一个返回的是一个函数式接口
        Supplier<String> supplier = () -> "this is supplier";
        System.out.println(supplier.get());
        // Function<T,R> 输入T,输出R
        // 返回的也是一个函数式接口
        Function<Integer, List<Integer>> function = (i) -> List.of(i * 2);
        List<Integer> apply = function.apply(100);
        System.out.println(apply);
        // BiFunction<T,U,R> 输入T,U 输出R
        BiFunction<Integer, Integer, List<Integer>> biFunction = (i, j) -> List.of(i * 2, j * 2);
        List<Integer> apply1 = biFunction.apply(100, 200);
        System.out.println(apply1);
    }

    @Test
    public void test02() {
        String[] strArr = {"ss1", "ss2", "", "ss4"};
        Stream.of(strArr)
              .forEach(System.out::print);
        Stream.iterate(1, (i) -> i + 1)
              .limit(10)
              .forEach(System.out::print);
    }

    @Test
    public void test03() {
        String[] strArr = {"s1", "b2", "abc3", "s4", ""};
        String d = Stream.of(strArr)
                         .filter(i -> !i.isEmpty())
                         .sorted()
                         .limit(1)
                         .map(i -> i.replace("3", "d"))
                         .flatMap(i -> Stream.of(i.split("")))
                         .sorted()
                         .collect(Collectors.joining());
        System.out.println(d);
    }

    // java 响应式编程
    @Test
    public void test04() {
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<String>() {
            Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("start subscription....");
                this.subscription = subscription;
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                subscription.request(10);
                System.out.println("receive: " + item);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete.");
            }
        };
    }


}
