package com.serendipity.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName LeetCode_046_permute
 * Description TODO
 * Author 11931
 * Date 2022-12-03:14:31
 * Version 1.0
 **/
public class LeetCode_046_permute {

    public static void main(String[] args) {

    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        process(res, visited, nums, new ArrayList<Integer>());
        return res;
    }

    /**
     * @param res     返回结果
     * @param visited
     * @param nums
     * @param temp
     */
    private void process(List<List<Integer>> res, boolean[] visited, int[] nums, ArrayList<Integer> temp) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            temp.add(nums[i]);
            process(res, visited, nums, temp);
            // 将原来的元素重置
            visited[i] = false;
            temp.remove(temp.size() - 1);
        }
    }

    // 全排列
    public List<List<Integer>> permute2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        process2(nums, res, visited, new ArrayList<Integer>());
        return res;
    }

    public void process2(int[] nums, List<List<Integer>> res, boolean[] visited, ArrayList<Integer> temp) {
        if (nums.length == temp.size()) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 如果第i个元素已经被访问过,继续访问下一个元素
            if (visited[i]) {
                continue;
            }
            // 如果第i个元素没有被访问过
            visited[i] = true;
            temp.add(nums[i]);
            process2(nums, res, visited, temp);
            temp.remove(temp.size() - 1);
            visited[i] = false;
        }
    }
}
