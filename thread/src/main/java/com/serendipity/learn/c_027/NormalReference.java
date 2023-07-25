package com.serendipity.learn.c_027;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: Administrator
 * Date: 2021/9/11 15:37
 * FileName: NormalReference
 * Description: com.java.thread.c_027
 * 强引用,默认的是强引用
 */
public class NormalReference {


    public static void main(String[] args) {
        M m = new M();
        m = null;
        System.gc();
    }


}
