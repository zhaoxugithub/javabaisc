package com.serendipity.myold.exception.exce;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/6 23:41
 * FileName: ExceptionDemo01
 * Description: com.exception
 */
public class ExceptionDemo01 {

    public void exec() {
        String s = "abc";
        // 这里会抛出来一个异常
        int i = Integer.parseInt(s);
    }

    public static byte[] toGBK(String s) {
        try {
            return s.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
            return s.getBytes();
        }
    }

    public static void test01(String s) {
        try {
            //process1();
            //process2();
            s.getBytes("GBK");
            // TODO: 2022/4/7
        } catch (UnsupportedEncodingException e) {
            System.out.println("Bad encoding");
        } finally {
            System.out.println("END");
        }
    }


    public static void test02() {


    }

    public static void main(String[] args) {
        byte[] dfafds = toGBK("中文");
        System.out.println(Arrays.toString(dfafds));

    }
}
