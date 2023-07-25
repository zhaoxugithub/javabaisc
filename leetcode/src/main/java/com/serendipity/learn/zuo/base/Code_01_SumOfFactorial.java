package com.serendipity.learn.zuo.base;

/**
 * ClassName Code_01_SumOfFactorial
 * Description TODO
 * Author 11931
 * Date 2023-02-21:1:34
 * Version 1.0
 * 计算阶乘
 **/
public class Code_01_SumOfFactorial {
    public static long factorial(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += f1(i);
        }
        return sum;
    }

    public static long f1(int n) {
        int temp = 1;
        for (int i = 1; i <= n; i++) {
            temp *= i;
        }
        return temp;
    }

    public static long factorial2(int num) {
        int sum = 0;
        int cur = 1;
        for (int i = 1; i <= num; i++) {
            cur = cur * i;
            sum += cur;
        }
        return sum;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int random = (int) (Math.random() * 101);
            long factorial = factorial(random);
            long l = factorial2(random);
            if (factorial != l) {
                System.out.println("error");
                break;
            }
        }
        System.out.println("success");
    }
}
