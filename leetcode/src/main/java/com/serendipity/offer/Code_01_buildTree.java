package com.serendipity.offer;

import java.util.HashMap;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/1 10:04 上午
 * FileName: Code_01_buildTree
 * Description: com.toOffer_v2
 * <p>
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * 示例 2:
 * <p>
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code_01_buildTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return null;
    }

    class Solution {
        HashMap<Integer, Integer> map = new HashMap<>();// 标记中序遍历
        int[] preorder;// 保留的先序遍历，方便递归时依据索引查看先序遍历的值

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            this.preorder = preorder;
            // 将中序遍历的值及索引放在map中，方便递归时获取左子树与右子树的数量及其根的索引
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            // 三个索引分别为
            // 当前根的的索引
            // 递归树的左边界，即数组左边界
            // 递归树的右边界，即数组右边界
            return recur(0, 0, inorder.length - 1);
        }

        TreeNode recur(int pre_root, int in_left, int in_right) {
            if (in_left > in_right) return null;// 相等的话就是自己
            TreeNode root = new TreeNode(preorder[pre_root]);// 获取root节点
            int idx = map.get(preorder[pre_root]);// 获取在中序遍历中根节点所在索引，以方便获取左子树的数量
            // 左子树的根的索引为先序中的根节点+1
            // 递归左子树的左边界为原来的中序in_left
            // 递归左子树的右边界为中序中的根节点索引-1
            root.left = recur(pre_root + 1, in_left, idx - 1);
            // 右子树的根的索引为先序中的 当前根位置 + 左子树的数量 + 1
            // 递归右子树的左边界为中序中当前根节点+1
            // 递归右子树的右边界为中序中原来右子树的边界
            root.right = recur(pre_root + (idx - in_left) + 1, idx + 1, in_right);
            return root;

        }
    }
}
