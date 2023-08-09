package com.serendipity.myold.generics;

import java.util.Iterator;
import java.util.List;

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
    public <T> T getObject(Class<T> c) throws InstantiationException, IllegalAccessException {
        T t = c.newInstance();
        return t;
    }

    public static void testGetObject() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        GenericsTest_04 genericsTest04 = new GenericsTest_04();
        Object object = genericsTest04.getObject(Class.forName("com.old.generics.A"));
        if (object instanceof GenericsTest_06.A) {
            System.out.println("is A");
        } else {
            System.out.println("error");
        }
    }

    public static void main(String[] args) {
    }
}
