package com.serendipity.leetcode;

import com.serendipity.node.TreeNode;
import com.serendipity.utils.ArrayUtils;
import com.serendipity.utils.TreeUtil;

/**
 * ClassName LeetCode_124_maxPathSum
 * Description TODO
 * Author 11931
 * Date 2022-10-18:23:03
 * Version 1.0
 **/
public class LeetCode_124_maxPathSum {

    private static int sum = 0;

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxPathSum(root.left);
        int right = maxPathSum(root.right);
        sum = Math.max(sum, left + right + root.value);
        return root.value + left + right;
    }

    public static void main(String[] args) {
        int[] ints = ArrayUtils.generateRandomArray(20, 100, 1);
        Integer[] integers = ArrayUtils.intToInteger(ints);
        TreeNode tree = TreeUtil.createTree(integers);
        TreeUtil.printNode(tree);

        System.out.println("maxPathSum(tree) = " + maxPathSum(tree));

    }
}
