package com.serendipity.leetcode;

import com.serendipity.node.TreeNode;
import com.serendipity.utils.TreeUtil;
import org.junit.Test;

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

    public static int maxPathSum2(TreeNode root) {
        return 0;
    }

    public void preShow(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.value);
        preShow(root.left);
        preShow(root.right);
    }

    public void midShow(TreeNode root) {
        if (root == null) {
            return;
        }
        midShow(root.left);
        System.out.println(root.value);
        midShow(root.right);
    }

    public void postShow(TreeNode root) {
        if (root == null) {
            return;
        }
        postShow(root.left);
        postShow(root.right);
        System.out.println(root.value);
    }

    @Test
    public void test01() {
        TreeNode tree = TreeUtil.createTree(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        TreeUtil.printNode(tree);
        // preShow(tree);
        // System.out.println("=======");
        // midShow(tree);
        // System.out.println("=======");
        postShow(tree);
    }

    public static void main(String[] args) {
     /*   int[] ints = ArrayUtils.generateRandomArray(20, 100, 1);
        Integer[] integers = ArrayUtils.intToInteger(ints);
        TreeNode tree = TreeUtil.createTree(integers);
        TreeUtil.printNode(tree);
        System.out.println("maxPathSum(tree) = " + maxPathSum(tree));*/
    }
}
