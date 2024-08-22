package com.serendipity.learn;

public class MyLogUtils {
    public static <T> void record(T msg, T... extra) {
        System.out.println(String.format((String) msg, extra));
    }
}
