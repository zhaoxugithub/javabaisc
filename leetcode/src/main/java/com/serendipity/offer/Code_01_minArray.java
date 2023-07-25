package com.serendipity.offer;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/15 9:03 下午
 * FileName: Code_01_minArray
 * Description: com.toOffer_v2
 */
public class Code_01_minArray {

    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length ==0) {
            return -1;
        }
        int min = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (min>numbers[i]) {
                min = numbers[i];
            }
        }
        return min;
    }

    public int minArray2(int[] numbers){
        if (numbers == null || numbers.length ==0) {
            return -1;
        }
        int mid = numbers.length >>2;
        return 0;
    }

    public void sss(int[] arr,int start,int m){
        return;
    }


}
