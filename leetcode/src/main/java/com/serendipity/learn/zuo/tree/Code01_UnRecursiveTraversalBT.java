package com.serendipity.learn.zuo.tree;

import java.util.Stack;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/5/30 23:16
 * FileName: Code01_UnRecursiveTraversalBT
 * Description: com.datastruct.zuo.tree
 * <p>
 * 非递归二叉树前中后遍历
 */
public class Code01_UnRecursiveTraversalBT {

    private static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }
    }

    public static void pre(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode head = stack.pop();
            System.out.print(head.value + " ");
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }
        System.out.println();
    }


    public static void middle(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode pop = stack.pop();
                System.out.print(pop.value + " ");
                root = pop.right;
            }
        }
        System.out.println();
    }

    public static void post(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode pop = stack1.pop();
            stack2.push(pop);
            if (pop.left != null) {
                stack1.push(pop.left);
            }
            if (pop.right != null) {
                stack1.push(pop.right);
            }
        }

        while (!stack2.isEmpty()) {
            TreeNode pop = stack2.pop();
            System.out.print(pop.value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);

        pre(head);
        System.out.println("========");
        middle(head);
        System.out.println("========");
        post(head);
        System.out.println("========");
    }
}
