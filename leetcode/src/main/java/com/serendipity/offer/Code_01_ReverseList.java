package com.serendipity.offer;

import com.serendipity.node.ListNode;
import com.serendipity.utils.LinkedListUtils;
import org.junit.Test;

import java.util.Stack;

public class Code_01_ReverseList {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode p1 = head;
        while (p1 != null) {
            stack.push(p1);
            p1 = p1.next;
        }
        stack.stream().forEach(System.out::println);
        ListNode newHead = new ListNode(-1);
        ListNode result = newHead;
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            System.out.println(pop);
            newHead.next = pop;
            newHead = newHead.next;
        }
        return result;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p1 = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = p1;
            p1 = cur;
            cur = temp;
        }
        return p1;
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param head ListNode类
     * @param m    int整型
     * @param n    int整型
     * @return ListNode类
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        return null;
    }

    @Test
    public void test01() {
        ListNode<Integer> listNode = LinkedListUtils.createListNode(1, 2, 3);
        ListNode listNode1 = reverseList2(listNode);
        LinkedListUtils.printListNode(listNode1);
    }

}
