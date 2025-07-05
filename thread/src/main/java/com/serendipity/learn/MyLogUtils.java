package com.serendipity.learn;

public class MyLogUtils {
    @SafeVarargs
    public static <T> void record(T msg, T... extra) {
        System.out.printf(msg + "%n", (Object) extra);
    }
}
