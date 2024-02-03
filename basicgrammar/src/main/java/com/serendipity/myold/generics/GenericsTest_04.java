package com.serendipity.myold.generics;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

@Slf4j
@SuppressWarnings("all")
public class GenericsTest_04 {

    private <T> int max() {
        return 0;
    }

    private <T> T max(T t) {
        return t;
    }

    private <E extends Comparable<? super E>> E max(List<? extends E> e1) {
        if (e1 == null) {
            return null;
        }
        // 迭代器返回的元素属于 E 的某个子类型
        Iterator<? extends E> iterator = e1.iterator();
        E result = iterator.next();
        while (iterator.hasNext()) {
            E next = iterator.next();
            if (next.compareTo(result) > 0) {
                result = next;
            }
        }
        return result;
    }

    /**
     * 范型方法
     *
     * @param c   用来创建范型对象
     * @param <T> 申明一个范型T
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> T getObject(Class<T> c) throws Exception {
        return c.newInstance();
    }

    @Test
    public void test() throws Exception {
        GenericsTest_04 genericsTest04 = getObject(GenericsTest_04.class);
        log.info(genericsTest04.toString());

        String str = getObject(String.class);
        log.info(str);
        str = "sss";
        log.info(str);

        Integer integ = getObject(Integer.class);
        integ = 10;
        log.info(String.valueOf(integ));
    }

    public static void testGetObject() throws Exception {
        GenericsTest_04 genericsTest04 = new GenericsTest_04();
        Object object = genericsTest04.getObject(Class.forName("com.old.generics.A"));
        if (object instanceof GenericsTest_06.A) {
            log.info("is A");
        } else {
            log.info("error");
        }
    }
}
