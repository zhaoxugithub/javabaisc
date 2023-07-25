package com.serendipity.learn.zuo.line;

public class Code_01_DeleteNode {
    private class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    // 头结点翻转
    public ListNode reserveHead(ListNode head) {
        ListNode next = null;
        ListNode pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public ListNode reserveHead2(ListNode head2) {
        if (head2 == null || head2.next == null) {
            return head2;
        }
        ListNode pre = null;
        ListNode next = null;
        while (head2 != null) {
        }
        return head2;
    }

    // 因为删除的节点有可能是头结点，所以链表的返回需要返回另外的一个头结点
    public ListNode removeElements2(ListNode head, int val) {
        // 删除都节点等于val
        // 首先先删除头结点等于val的
        // while (head != null) {
        //     if (head.value != val) {
        //         break;
        //     }
        //     head = head.next;
        // }
        while (head.value == val) {
            head = head.next;
        }
        ListNode pre = head;
        ListNode cur = head;
        while (cur != null) {

            if (cur.value == val) {
                pre.next = cur.next;
            } else {
                // 整理只能
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public void showList(ListNode head) {
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    public void testListNode() {
        ListNode head = new ListNode(3);
        head.next = new ListNode(3);
        ListNode cur = head.next;
        for (int i = 1; i < 10; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        showList(head);
        ListNode newHead = reserveHead(head);
        System.out.println();
        showList(newHead);
    }

    public static void main(String[] args) {
        new Code_01_DeleteNode().testListNode();
    }
}
