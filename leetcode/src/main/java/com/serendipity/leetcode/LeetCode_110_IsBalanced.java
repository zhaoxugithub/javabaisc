package com.serendipity.leetcode;

public class LeetCode_110_IsBalanced {


    private static class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }


    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }else {
            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);
            if (Math.abs(leftHeight-rightHeight)>1) {
                return false;
            } else {
                boolean  f1= isBalanced(root.left);
                boolean f2 = isBalanced(root.right);
                return f1 && f2;
            }
        }
    }

    public int getHeight(TreeNode node){
        if (node == null) {
            return 0;
        }
        int height = Math.max(getHeight(node.left), getHeight(node.right));
        return height+1;
    }

    public static void main(String[] args) {
        LeetCode_110_IsBalanced babble = new LeetCode_110_IsBalanced();
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(5);

        root.right = new TreeNode(1);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(5);
        root.right.right.right.right = new TreeNode(6);
        System.out.println(babble.getHeight(root));
    }
}
