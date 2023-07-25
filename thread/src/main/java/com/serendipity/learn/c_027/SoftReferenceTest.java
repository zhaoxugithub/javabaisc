package com.serendipity.learn.c_027;

import java.lang.ref.SoftReference;
import java.util.Arrays;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: Administrator
 * Date: 2021/9/11 15:40
 * FileName: SoftReference
 * Description: com.java.thread.c_027
 *
 * 软引用：内存不够用的时候会释放
 */
public class SoftReferenceTest {

    public static void main(String[] args) {

        SoftReference<byte[]> softReference = new SoftReference(new byte[1024 * 1024 * 10]);

        System.out.println(Arrays.toString(softReference.get()));
//        softReference = null;
        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(softReference.get()));

        byte[] bytes = new byte[1024 * 1024 * 15];

        System.out.println(softReference.get());

    }
}
