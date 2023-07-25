package com.serendipity.learn.zuo.recursive;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/8/16 下午10:06
 * FileName: Test
 * Description: com.datastruct.zuo.recursive
 */
public class Test implements Serializable {

    private static final long serialVersionUID = 9149469250098834865L;
    // psfi = 1
    public static final int ss = 1;

    //psf
    public static final int aa = 1;
    //main  psvm
    public static void main(String[] args) {
        try {
            String s = "ss";
            System.out.println(s);
            int a = 100;
            //a.foti
            for (int i = 0; i < a; i++) {

            }

            //ints.fori
            int[] ints = new int[2];
            for (int i = 0; i < ints.length; i++) {

            }
            //sout
            System.out.println("ss");
            //soutm
            System.out.println("Test.main");
            //soutv
            System.out.println("ints = " + ints);
            //soutp
            System.out.println("args = " + Arrays.deepToString(args));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
    public void a(int[] arr, int start, int end) {
        System.out.println("arr = " + Arrays.toString(arr) + ", start = " + start + ", end = " + end);
        //arr.fori
        for (int i = 0; i < arr.length; i++) {
        }
        //arr.forr
        for (int i = arr.length - 1; i >= 0; i--) {
        }
        //iter
        for (int i : arr) {

        }
        //arr.for
        for (int i : arr) {

        }
        String s = "";
        //s.null
        if (s == null) {

        }
        //ifn
        if (s == null) {

        }
        //s.nn
        if (s != null) {

        }
        //inn
        if (s != null) {

        }
    }


}
