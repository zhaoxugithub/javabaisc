package com.serendipity.myold.generics;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@Slf4j
@SuppressWarnings("all")
public class GenericsTest_07 {
    @Test
    public void test1() {
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        log.info(String.valueOf(list2.getClass() == list1.getClass()));
    }

    @Test
    public void test2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        list1.add(2);
        // 有异常产生
        list1.getClass()
             .getMethod("add", Object.class)
             .invoke(list1, "asd");

        // 这里打印会抛出异常
        list1.forEach(System.out::println);


        for (int i = 0; i < list1.size(); i++) {

        }

    }

    public <T> T add(T x, T y) {
        return y;
    }

    @Test
    public void test3() {
        /**不指定泛型的时候*/
        // 这两个参数都是Integer，所以T为Integer类型
        Integer add = add(1, 2);
        // 这两个参数一个是Integer，一个是Float，所以取同一父类的最小级，为Number
        Number add1 = add(1, 1.2);
        // 这两个参数一个是Integer，一个是String，所以取同一父类的最小级，为Object
        Object add2 = add(1, "asd");
        System.out.println();
        /**指定泛型的时候*/
        // 指定了Integer，所以只能为Integer类型或者其子类
        Integer l1 = add(1, 2);
        // 编译错误，指定了Integer，不能为Float
        // Integer l2 = GenericsTest_07.<Integer>add(1, 1.2);
        // 指定为Number，所以可以为Integer和Float
        Number add3 = add(1, 1.2);
    }
}
