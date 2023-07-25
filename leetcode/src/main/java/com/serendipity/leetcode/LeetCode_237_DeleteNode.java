package com.serendipity.leetcode;


import com.common.ListNode;

/**
 * ClassName LeetCode_237_DeleteNode
 * Description TODO
 * Author 11931
 * Date 2022-09-16:1:04
 * Version 1.0
 * https://leetcode.cn/problems/delete-node-in-a-linked-list/
 **/
public class LeetCode_237_DeleteNode {
    public void deleteNode(ListNode node) {
        //获取下一个节点的值
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
