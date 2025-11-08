package com.serendipity.myold.generics;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GenericsTest_06<T> {
    static class A {
        @Override
        public String toString() {
            return "A{" + "name='" + "zhaoxu" + '\'' + '}';
        }
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        GenericsTest_06<String> stringGenericsTest06 = new GenericsTest_06<>();
        String[] strings = stringGenericsTest06.arrayWithTypeToken(String.class, 10);
        for (int i = 0; i < strings.length; i++) {
            strings[i] = 10 + i + "";
        }
        System.out.println(Arrays.toString(strings));
    }

    /**
     * 创建T类型的数组
     *
     * @param type
     * @param size
     * @return
     */
    public T[] arrayWithTypeToken(Class<T> type, int size) {
        return (T[]) Array.newInstance(type, size);
    }

    /**
     * @param type
     * @param size
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public T createTClass(Class<T> type, int size) throws InstantiationException, IllegalAccessException {
        return type.newInstance();
    }
}
