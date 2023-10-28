package com.serendipity.leetcode;

import com.serendipity.node.ListNode;

/**
 * ClassName LeetCode_19_removeNthFromEnd
 * Description TODO
 * Author 11931
 * Date 2023-10-22:21:38
 * Version 1.0
 **/
public class LeetCode_19_removeNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }

        ListNode dumpNext = new ListNode(-1);
        dumpNext.next = head;

        ListNode nNode = findNNode(dumpNext, n + 1);
        nNode.next = nNode.next.next;
        return dumpNext.next;
    }

    public ListNode findNNode(ListNode head, int n) {
        ListNode p1 = head;
        for (int i = 0; i < n; i++) {
            p1 = p1.next;
        }
        ListNode p2 = head;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    public static void main(String[] args) {

    }
}
