package com.serendipity.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName LeetCode_18_fourSum
 * Description TODO
 * Author 11931
 * Date 2022-12-05:1:16
 * Version 1.0
 **/
public class LeetCode_18_fourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        process(res, visited, nums, new ArrayList<Integer>(), target);
        return res;
    }

    public void process(List<List<Integer>> res, boolean[] visited, int[] nums, ArrayList<Integer> temp, int target) {
        if (temp.size() == 4) {
            return;
        }
    }

    public static void main(String[] args) {
    }
}
