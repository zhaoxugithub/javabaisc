package com.serendipity.leetcode;

import com.serendipity.node.ListNode;

/**
 * ClassName LeetCode_middleNode
 * Description TODO
 * Author 11931
 * Date 2023-10-22:22:04
 * Version 1.0
 **/
public class LeetCode_876_middleNode {

    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
