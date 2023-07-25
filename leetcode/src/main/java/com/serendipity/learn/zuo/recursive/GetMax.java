package com.serendipity.learn.zuo.recursive;

import java.util.Random;

//递归求出最大元素
public class GetMax {

    public static int getMax(int[] array, int L, int R) {
        if (L == R) {
            return array[L];
        }
        int k = L + ((R - L) >> 1);
        int leftValue = getMax(array, L, k);
        int rightValue = getMax(array, k + 1, R);
        return Math.max(leftValue, rightValue);
    }

    public static int[] generateArray() {
        int[] arr = new int[10];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] array = generateArray();
//        Collections.singletonList(array).forEach(System.out::print);
        int max = getMax(array, 0, array.length-1);
        System.out.println(max);
    }

}
