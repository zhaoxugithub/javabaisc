package com.serendipity.learn.zuo.dp;



import com.serendipity.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//[1,5,2,4,3]

public class LeetCode_300_lengthOfLIS {
    //最长递增子序列
    //方法一：暴力
    public static int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int max = process(nums, i);
            list.add(max);
        }
        return Collections.max(list);
    }

    //返回的是从 i 位置开始遍历的最长子序列长度
    public static int process(int[] num, int i) {
        //当遍历到数组的最有一个元素的时候，也就是说只有最后一元素的时候U结束
        if (i == num.length - 1) {
            return 1;
        }
        int max = 1;
        for (int j = i + 1; j < num.length; j++) {
            //如果后面元素大于起始位置的元素
            //去计算从j开始的最长子序列
            if (num[j] > num[i]) {
                max = Math.max(max, process(num, j) + 1);
            }
        }
        return max;
    }

    /*
        DP:用空间换时间
            暴力遍历的话会有很多重复计算，使用一个容器将计算结果暂时存放起来

     */
    //采用dp算法，以空间换时间,递归算法
    public static int lengthOfLIS_DP(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //创阿金
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            process_DP(nums, i, dp);
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    private static int process_DP(int[] nums, int i, int[] dp) {
        if (dp[i] != 0) {
            return dp[i];
        }
        if (nums.length - 1 == i) {
            dp[nums.length - 1] = 1;
        }
        int max = 1;
        //计算从i往后的最大自增子序列
        for (int i1 = i + 1; i1 < nums.length; i1++) {
            if (nums[i1] > nums[i]) {
                max = Math.max(process_DP(nums, i1, dp) + 1, max);
            }
        }
        dp[i] = max;
        return max;
    }

    /**
     * dp 非递归
     * 有点问题
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS_DP_2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            //计算从i开始遍历的最短子序列
            dp[i] = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

        }
        return Arrays.stream(dp).max().getAsInt();
    }


    public static void testEqual() {
        for (int i = 0; i < 100; i++) {
            int[] ints = ArrayUtils.generateRandomArray(10, 100, 2);
            int[] copyArray = ArrayUtils.copyArray(ints);
            int r1 = lengthOfLIS1(ints);
            int r2 = lengthOfLIS_DP(copyArray);
            int r3 = lengthOfLIS_DP_2(ArrayUtils.copyArray(ints));
            if (r1 != r2 || r3 != r1) {
                System.out.println("error");
                System.out.println("r1=" + r1 + ";r2=" + r2 + ";r3=" + r3);
                return;
            } else {
                System.out.println(r1);
            }

        }
    }

    public static void testTime() {
        long start1 = System.currentTimeMillis();
        System.out.println();
        for (int i = 0; i < 100; i++) {
            int[] ints = ArrayUtils.generateRandomArray(100, 100, 2);
            int r2 = lengthOfLIS_DP(ints); //
        }
        System.out.println(System.currentTimeMillis() - start1);
    }


    public static void main(String[] args) {

        testEqual();
    }
}
