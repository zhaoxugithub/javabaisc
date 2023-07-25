package com.serendipity.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName LeetCode_22_generateParenthesis
 * Description TODO
 * Author 11931
 * Date 2022-12-07:23:42
 * Version 1.0
 **/
public class LeetCode_22_generateParenthesis {

    public List<String> generateParenthesis(int n) {
        if (n < 1) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        process(res, sb, n);
        return res;
    }
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        if (arr1 == null || arr2 == null) {
            return 0;
        }
        int i = 0;
        int j = 0;
        int res = 0;
        while (i < arr1.length && j < arr2.length) {
            if (Math.abs(arr1[i] - arr2[j]) < d) {
                i++;
                j = 0;
            } else {
                if (j == arr2.length - 1) {
                    res++;
                    j = 0;
                    i++;
                } else {
                    j++;
                }
            }
        }
        return 0;
    }
    private void process(List<String> res, StringBuilder sb, int n) {
        if (n <= 0) {
            res.add(sb.toString());
            return;
        }
        sb.append("(");
    }
}
