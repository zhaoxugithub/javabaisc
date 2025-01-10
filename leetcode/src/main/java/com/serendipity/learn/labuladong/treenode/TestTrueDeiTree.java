package com.serendipity.learn.labuladong.treenode;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

public class TestTrueDeiTree {

    private TreeNode root;

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /*
        3
     9      20
         15     7
     */
    @Before
    public void before() {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;

        root = t1;
    }


    // 前序遍历 (递归) 根->左->右
    private void preOrderRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + "->");
        preOrderRecursion(root.left);
        preOrderRecursion(root.right);
    }

    @Test
    public void testPreOrderRecursion() {
        preOrderRecursion(root);
        System.out.println("null");
    }

    // 前序遍历 (非递归)
    public void preOrderNonRecursion(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                System.out.print(treeNode.val);
                stack.push(treeNode);
                // 遍历完根,然后还是遍历
                treeNode = treeNode.left;
            }
        }
    }
}
