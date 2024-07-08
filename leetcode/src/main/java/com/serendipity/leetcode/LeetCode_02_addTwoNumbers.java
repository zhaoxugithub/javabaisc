package com.serendipity.leetcode;


import com.serendipity.node.ListNode;
import com.serendipity.utils.LinkedListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.serendipity.utils.LinkedListUtils.createListNode;

/*

给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

 

示例 1：


输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.
示例 2：

输入：l1 = [0], l2 = [0]
输出：[0]
示例 3：

输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/add-two-numbers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_02_addTwoNumbers {


    // 反转ListNode,迭代方法
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static ListNode reverseList3(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // 反转ListNode,递归
    public static ListNode reverseList2(ListNode head) {
        return null;
    }


    //
    public static ListNode<Integer> addTwoNumbers111(ListNode<Integer> l1, ListNode<Integer> l2) {
        ListNode preHead = new ListNode(0);
        ListNode cur = preHead;
        // 进位符
        int carry = 0;

        while (l1 != null && l2 != null) {
            int num1 = l1.val;
            int num2 = l2.val;
            int sum = num1 + num2 + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int sum = l1.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            int sum = l2.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            l2 = l2.next;
        }

        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return preHead.next;
    }

    /**
     * 优化版本
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode<Integer> addTwoNumbers22(ListNode<Integer> l1, ListNode<Integer> l2) {

        ListNode preHead = new ListNode(-1);
        ListNode cur = preHead;

        int carry = 0;
        while (l1 != null || l2 != null) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;

            int sum = num1 + num2 + carry;

            carry = sum / 10;
            sum = sum % 10;

            cur.next = new ListNode(sum);
            cur = cur.next;

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return preHead.next;
    }


    public static void main(String[] args) {
        ListNode<Integer> listNode1 = createListNode(9, 9, 9, 9, 9, 9, 9);
        ListNode<Integer> listNode2 = createListNode(9, 9, 9, 9);
        ListNode<Integer> integerListNode = addTwoNumbers22(listNode1, listNode2);
        LinkedListUtils.printListNode(integerListNode);

    }
}
