package com.serendipity.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_144_PreorderTraversal {

    public class TreeNode {
        int val;
        LeetCode_104_maxDepth.TreeNode left;
        LeetCode_104_maxDepth.TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, LeetCode_104_maxDepth.TreeNode left, LeetCode_104_maxDepth.TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //二叉树的前序遍历NLR
    public List<Integer> preorderTraversal(LeetCode_104_maxDepth.TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderTraversal(root,list);
        return list;
    }

    public void preorderTraversal(LeetCode_104_maxDepth.TreeNode node, List<Integer> list) {
        if (node != null) {
            list.add(node.val);
            preorderTraversal(node.left, list);
            preorderTraversal(node.right, list);
        }
    }

}
