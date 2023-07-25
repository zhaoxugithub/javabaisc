package com.serendipity.myold.lambda.ch01;

@FunctionalInterface
public interface MyPredicate<T> {
    boolean test(T t);
}
