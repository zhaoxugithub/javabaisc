package com.serendipity.myold.annotation.base.doc;

import java.util.ArrayList;

/**
 * ClassName Test
 * Description TODO
 * Author 11931
 * Date 2023-02-03:22:18
 * Version 1.0
 **/
public class Test {


    @TestDocAnnotation("mytestDoc")
    public void testDoc() {
        ArrayList<Object> objects = new ArrayList<>();
        int[] ints = new int[10];
        if (ints == null) {
            System.out.println("dsfdsf");

        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.testDoc();
    }
}
