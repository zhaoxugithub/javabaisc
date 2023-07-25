package com.serendipity.myold.annotation.custom;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName TestMethodAnnotation
 * Description TODO
 * Author 11931
 * Date 2023-02-03:23:01
 * Version 1.0
 **/
public class TestMethodAnnotation {

    @Override
    @MyMethodAnnotation(title = "toStringMethod", description = "override toString method")
    public String toString() {
        return "Override toString method";
    }

    @Deprecated
    @MyMethodAnnotation(title = "old static method", description = "deprecated old static method")
    public static void oldMethod() {
        System.out.println("old method, don't use it.");
    }

    @SuppressWarnings("all")
    @MyMethodAnnotation(title = "test method", description = "suppress warning static method")
    public static void genericsTest() throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        list.add("abc");
        oldMethod();
    }
}
