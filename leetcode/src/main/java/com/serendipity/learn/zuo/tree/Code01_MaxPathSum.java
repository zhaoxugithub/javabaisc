package com.serendipity.learn.zuo.tree;

public class Code01_MaxPathSum {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int maxPathSum(TreeNode root) {
        return 0;
    }

    public void middle(TreeNode root) {
        if (root == null) {
            return;
        }

        middle(root.left);




    }
}
