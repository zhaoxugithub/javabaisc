package com.serendipity.offer;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/14 10:30 下午
 * FileName: Code_01_fib
 * Description: com.toOffer_v2
 */
public class Code_01_fib {

    /**
     * 递归算法超时
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 1 || n == 0) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public int fib2(int n) {
        final int MOD = 1000000007;
        if (n < 2) {
            return n;
        }
        int p = 0, q = 0, r = 1;
        for (int i = 2; i <= n; i++) {
            p = q;
            q = r;
            r = (p + q) % MOD;
        }
        return r;
    }
}


