package com.serendipity.learn.zuo.tree;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/5/30 22:49
 * FileName: Code01_RecursiveTraversalBT
 * Description: com.datastruct.zuo.tree
 * <p>
 * 二叉树的前中后遍历
 */
public class Code01_RecursiveTraversalBT {
	private static class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;

		TreeNode(int value) {
			this.value = value;
		}
	}

	//先根->左—>右
	public static void pre(TreeNode root) {
		if (root == null) {
			return;
		}
		System.out.println(root.value);
		pre(root.left);
		pre(root.right);
	}

	public static void middle(TreeNode root) {
		if (root == null) {
			return;
		}

		middle(root.left);
		System.out.println(root.value);
		middle(root.right);
	}

	public static void post(TreeNode root) {
		if (root == null) {
			return;
		}
		post(root.left);
		post(root.right);
		System.out.println(root.value);
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
