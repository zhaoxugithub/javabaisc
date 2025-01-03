package com.serendipity.learn.labuladong;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName Test01
 * Description TODO
 * Author 11931
 * Date 2023-10-13:17:06
 * Version 1.0
 **/
public class Test01 {

    private class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (map.containsKey(another)) {
                return new int[]{i, map.get(another)};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode head = new ListNode(-1);
        ListNode result = head;
        int yu = 0;
        while (p1 != null && p2 != null) {
            int twoSum = p1.val + p2.val;
            if ((twoSum + yu) >= 10) {
                yu = (twoSum + yu) / 10;
                head.next = new ListNode((twoSum + yu) % 10);
            }
            head = head.next;
            p1 = p1.next;
            p2 = p2.next;
        }

        while (p1 != null) {
            if ((p1.val + yu) / 10 >= 1) {
                yu = (p1.val) / 10;
            }
            head.next = new ListNode(p1.val % 10);
            head = head.next;
            p1 = p1.next;
        }

        while (p2 != null) {
            if ((p2.val + yu) / 10 >= 1) {
                yu = (p2.val) / 10;
            }
            head.next = new ListNode(p2.val % 10);
            head = head.next;
            p2 = p2.next;
        }

        if (yu > 0) {
            head.next = new ListNode(yu);
        }

        return result.next;
    }


    public int[] twoSumTest(int[] nums, int target) {

        Map<Integer, Integer> maps = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (maps.containsKey(another)) {
                return new int[]{maps.get(another), i};
            } else {
                maps.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }


    public void test() {

    }


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode head = new ListNode(-1);

        ListNode cur = head;
        ListNode p1 = list1;
        ListNode p2 = list2;

        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                cur.next = p1;
                p1 = p1.next;
            }
            if (p1.val > p2.val) {
                cur.next = p2;
                p2 = p2.next;
            }
            cur = cur.next;
        }
        if (p1 != null) {
            cur.next = p1;
        }
        if (p2 != null) {
            cur.next = p2;
        }
        return head.next;
    }


    public int[] twoSum3(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (map.containsKey(another)) {
                return new int[]{map.get(another), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }

    //
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }

        ListNode head = null;
        ListNode tail = null;

        // 进位
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
        }
        return head;
    }

}