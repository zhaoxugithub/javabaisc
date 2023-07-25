package com.serendipity.action_design.strategy.demo01;

public interface Comparable<T> {
    //这里添加泛型的目的是为了限制比较的对象
    int compare(T o);
}
