package com.serendipity.offer;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/15 2:53 上午
 * FileName: Code_01_numWays
 * Description: com.toOffer_v2
 */
public class Code_01_numWays {

    public int numWays(int n) {
        final int MOD = 1000000007;
        if(n <1){
            return 1;
        }
        int p = 0,q=0,r =1;
        for (int i = 1; i <=n; i++) {
            p=q;
            q=r;
            r = (p+q) %MOD;
        }
        return r;
    }

    public static void main(String[] args) {
        int i = new Code_01_numWays().numWays(5);
        System.out.println(i);
    }
}
