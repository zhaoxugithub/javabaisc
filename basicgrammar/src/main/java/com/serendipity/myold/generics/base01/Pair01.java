package com.serendipity.myold.generics.base01;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/11 23:28
 * FileName: Pair01
 * Description: com.generics.base01
 *
 * 定义一个范型类
 */
public class Pair01<T> {
    private T first;
    private T last;

    public Pair01(T first, T last) {
        this.first = first;
        this.last = last;
    }

    public T getFirst() {
        return first;
    }

    public T getLast() {
        return last;
    }
}
