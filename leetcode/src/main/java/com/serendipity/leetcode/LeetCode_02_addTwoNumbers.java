package com.serendipity.leetcode;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int x) {
            val = x;
        }

        ListNode(int x, ListNode next) {
            this.val = x;
            this.next = next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        ListNode p1 = l1;
        ListNode p2 = l2;

        while (p1 != null) {
            stack1.push(p1.val);
            p1 = p1.next;
        }
        while (p2 != null) {
            stack2.push(p2.val);
            p2 = p2.next;
        }
        List<Integer> list = new ArrayList<>();
        int temp = 0;
        while (!stack2.isEmpty() && !stack1.isEmpty()) {
            Integer b = stack2.pop();
            Integer a = stack1.pop();
            int re = (a + b + temp) % 10;
            list.add(re);
            temp = a + b > 9 ? 1 : 0;
        }
        while (!stack1.isEmpty()) {
            Integer pop = stack1.pop();
            list.add(pop);
        }
        while (!stack2.isEmpty()) {
            Integer pop = stack2.pop();
            list.add(pop);
        }
        ListNode head = new ListNode(list.get(0));
        p1 = head;
        for (int i = 1; i < list.size(); i++) {
            p1.next = new ListNode(list.get(i));
            p1 = p1.next;
        }
        return head;
    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int more = 0;
        ListNode p1 = l1;
        ListNode p2 = l2;

        return null;
    }

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


    public static void main(String[] args) {
        ListNode listNode = new ListNode(2);
        listNode.next = new ListNode(4);
        listNode.next.next = new ListNode(3);
        ListNode listNode2 = new ListNode(5);
        listNode2.next = new ListNode(6);
        listNode2.next.next = new ListNode(4);
        ListNode listNode1 = addTwoNumbers2(listNode, listNode2);
        while (listNode1 != null) {
            System.out.println(listNode1.val);
            listNode1 = listNode1.next;
        }
    }
}
