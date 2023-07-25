package com.serendipity.learn.zuo.base;


import org.junit.Test;

/**
 * ClassName Code_01_PrintB
 * Description TODO
 * Author 11931
 * Date 2023-02-16:0:59
 * Version 1.0
 **/
public class Code_01_PrintB {

    // 给定一个整数int(有符号)，打印打他的32位0,1
    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    public static void print1() {
        for (int i = 31; i >= 0; i--) {
            System.out.println(1 << i);
        }
        System.out.println();
    }

    // 逆序
    public static void print2(int num) {
        for (int i = 0; i < 32; i++) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    public static void print3() {
        int t = 1;
        print(t);
        print(t << 2);
        print(t << 3);
        print(t << 4);
    }


    public static void print4() {
        System.out.println("-----------");
        print(-1);
        print(Integer.MIN_VALUE);

        //[-2^31,2^31-1]
        System.out.println("------------");
        print(Integer.MAX_VALUE);
    }

    public static void print5() {

        // 正数的左移右移
        int x = 1024;
        print(x);

        // 左移
        print(x >> 1);
        // 右移
        print(x << 1);
    }

    public static void print6() {
        int x = -1024;
        print(x);
        print(x >> 1);
        print(x << 1);
    }

    public static void print7() {

        // 取反操作
        int a = 10;
        int b = -10;

        // 一个数的相反数=一个数的取反+1
        int c = (~a) + 1;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        System.out.println("--------------");
        print(a);
        print(c);
    }

    @Test
    public void test8() {
        int a = 1;
        int b = -1;

        print(a);
        print(b);
    }

    // int 32 位
    public static void main(String[] args) {
        // print(10);
        // print2(10);
        // print3();
        // print5();
        // System.out.println("-----");
        // print6();
        print7();
    }
}
