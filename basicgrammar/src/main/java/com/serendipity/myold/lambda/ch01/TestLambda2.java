import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/5/18 13:19
 * FileName: TestLambda2
 * Description: com.lambda.ch01
 * <p>
 * https://www.pdai.tech/md/java/java8/java8-stream.html
 */
public class TestLambda2 {

    private static List<Integer> list = new ArrayList<>();

    static {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    // lambda 表达式
    public void test01() {
        // 如果对输入参数有变动就无法写成方法引用
        list.forEach(x -> System.out.println(x + 1));
        list.forEach(System.out::println);
    }

    public void test02() {
        List<Integer> list = Arrays.asList(2, 3, 5, 7);
        // lambda域外的局部变量只能访问，不能修改
        int fa = 2;
        list.forEach(ele -> {
//            fa++; 这里会报错
        });

        // 访问是可以的
        list.forEach(ele -> {
            System.out.println(fa * ele);
        });
    }

    // 分类
    // 惰性求值方法和
    public void test03() {
        // 这行代码并未做什么实际性的工作，filter只是描述了Stream，没有产生新的集合。
        Stream<Integer> integerStream = list.stream()
                                            .filter(f -> f > 5)
                                            .filter(f -> f.equals(6));

        // 及早求值方法
        // collect最终会从Stream产生新值，拥有终止操作。
        List<Integer> collect = integerStream.collect(Collectors.toList());
    }


    // 顺序流和并行流
    // 数据量大的时候采用并行流的效率要高很多
    @Test
    public void test04() {
        // 顺序流
        List<Integer> collect = list.stream()
                                    .filter(f -> f > 5)
                                    .collect(Collectors.toList());
        Integer[] integers = list.toArray(new Integer[0]);
        // 并行流
        List<Integer> collect1 = list.stream()
                                     .parallel()
                                     .filter(f -> f > 5)
                                     .collect(Collectors.toList());
    }

    // 方法引用
    @Test
    public void test05() {
        // 1.静态方法引用ClassName :: staticMethodName
        Arrays.asList("d", "b", "c", "a")
              .forEach(TestLambda2::println);
        // lambda表达式使用：
        Arrays.asList(new String[]{"a", "c", "b"})
              .forEach(s -> TestLambda2.println(s));
        // 静态方法引用：
        Arrays.asList(new String[]{"a", "c", "b"})
              .stream()
              .forEach(TestLambda2::println);

        System.out.println("-------------------------------------------------");


        // 构造器引用ClassName :: new
        // 构造器引用适用于lambda表达式主体中仅仅调用了某个类的构造函数返回实例的场景
        Supplier<List<String>> supplier = new Supplier() {
            @Override
            public List<String> get() {
                return new ArrayList<String>();
            }
        };

        Supplier<List<String>> listSupplier = () -> new ArrayList<>();
        Supplier<String> ar = String::new;
        String s = ar.get();

        System.out.println("-----------------------------------------");
        // 类的任意对象的实例方法引用

        String[] s1 = {"d", "b", "c", "d"};
        Arrays.sort(s1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        Arrays.sort(s1, (o1, o2) -> o1.compareToIgnoreCase(o2));
        Arrays.sort(s1, String::compareToIgnoreCase);

        // 特定对象的实例方法引用
    }

    // Filter & Predicate
    // @Test
    public void test06() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> str.startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> str.endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> str.length() > 4);
    }


    public static void filter(List<String> names, Predicate<String> condition) {
        names.stream()
             .filter(condition)
             .forEach(System.out::println);
        System.out.println("------------------");
    }


    // 多个Predicate组成filter
    @Test
    public void test07() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        // 断言的规则
        Predicate<String> stringPredicate = (n) -> n.startsWith("J");
        Predicate<String> predicateLength = (n) -> n.length() > 4;
        languages.stream()
                 .filter(stringPredicate.and(predicateLength))
                 .forEach(System.out::println);
    }


    public void test08() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        Double aDouble = costBeforeTax.stream()
                                      .map(x -> (x + x * 0.12))
                                      .reduce(Double::sum)
                                      .get();
        System.out.println(aDouble);
    }

    public static void println(String s) {
        System.out.println(s + 1);
    }
}
