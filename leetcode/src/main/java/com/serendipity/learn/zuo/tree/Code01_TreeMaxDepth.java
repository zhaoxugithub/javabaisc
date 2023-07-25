package com.serendipity.learn.zuo.tree;

import com.serendipity.node.TreeNode;
import com.serendipity.utils.ArrayUtils;
import com.serendipity.utils.TreeUtil;

/**
 * ClassName Code01_TreeMaxDepth
 * Description TODO
 * Author 11931
 * Date 2023-06-08:0:53
 * Version 1.0
 **/
public class Code01_TreeMaxDepth {
    // 记录最大深度
    int res = 0;
    int depth = 0;

    // 主函数
    int maxDepth2(TreeNode root) {
        traverse(root);
        return res;
    }

    // 二叉树遍历框架
    void traverse(TreeNode root) {
        if (root == null) {
            // 到达叶子节点
            res = Math.max(res, depth);
            return;
        }
        // 前序遍历位置
        depth++;
        traverse(root.left);
        traverse(root.right);
        // 后序遍历位置
        depth--;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return maxDepth(root.right);
        }
        if (root.right == null) {
            return maxDepth(root.left);
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {

        int[] ints = ArrayUtils.generateRandomArray(10, 100, 1);
        Integer[] array = ArrayUtils.intToInteger(ints);

        TreeNode tree = TreeUtil.createTree(array);
        TreeUtil.printNode(tree);


    }
}
