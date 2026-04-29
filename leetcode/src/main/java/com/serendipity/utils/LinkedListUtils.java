package com.serendipity.utils;

import com.serendipity.model.Person;
import com.serendipity.node.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ClassName LinkedListUtil
 * Description 单链表工具类
 * Author 11931
 * Date 2022-10-16:10:35
 * Version 1.0
 **/
public class LinkedListUtils {
    /**
     * 创建单链表
     *
     * @param array
     * @return
     */
    public static ListNode<Integer> createListNode(int... array) {
        if (array == null || array.length == 0) {
            return null;
        }
        ListNode<Integer> head = new ListNode<>(array[0]);
        ListNode<Integer> current = head;
        for (int i = 1; i < array.length; i++) {
            current.next = new ListNode<>(array[i]);
            current = current.next;
        }
        return head;
    }

    public static <T> ListNode<T> createListNode(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        T first = list.getFirst();
        ListNode<T> head = new ListNode<>(first);
        ListNode<T> current = head;
        for (int i = 1; i < list.size(); i++) {
            current.next = new ListNode<>(list.get(i));
            current = current.next;
        }
        return head;
    }

    public static ListNode<String> createListNodeString(List<String> strings) {
        return createListNode(strings);
    }

    public static ListNode<String> createListNodeString(String... strings) {
        return createListNode(Arrays.asList(strings));
    }

    public static ListNode<Character> createListNodeChar(Character... chars) {
        return createListNode(Arrays.asList(chars));
    }

    /**
     * 打印单链表
     *
     * @param msg
     * @param head
     */
    public static void printListNode(String msg, ListNode<Integer> head) {
        System.out.println(appendVal(head));
    }

    public static void printListNode(ListNode<Integer> head) {
        System.out.println(appendVal(head));
    }

    public static <T> void printListNodeObj(ListNode<T> head) {
        System.out.println(appendVal(head));
    }

    /**
     * @param head
     * @return
     */
    private static <T> String appendVal(ListNode<T> head) {
        StringBuilder sb = new StringBuilder();
        ListNode<T> current = head;

        while (current != null) {
            sb.append(current.val.toString());
            sb.append("->");
            current = current.next;
        }
        sb.append("NULL");
        return sb.toString();
    }

    public static void main(String[] args) {
        // 1.创建10个Person对象
        // 2. createListNode 参数放在 10个Person
        // 3. printListNodeObj 一下
        // 1. 创建10个Person对象
        List<Person> people = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            people.add(new Person("name" + i, i, "address-" + i));
        }
        // 2. createListNode 参数放在 10个Person
        ListNode<Person> head = createListNode(people);
        // 3. printListNodeObj 一下
        printListNodeObj(head);

        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integers.add(i);
        }
        printListNodeObj(createListNode(integers));
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("A" + i);
        }
        printListNodeObj(createListNode(strings));
    }

    // ==================== 查询操作 ====================

    /**
     * 获取链表长度
     */
    public static <T> int length(ListNode<T> head) {
        int count = 0;
        ListNode<T> cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    /**
     * 获取第 index 个节点的值（从0开始）
     */
    public static <T> T get(ListNode<T> head, int index) {
        if (head == null || index < 0) return null;
        ListNode<T> cur = head;
        for (int i = 0; i < index; i++) {
            if (cur == null) return null;
            cur = cur.next;
        }
        return cur == null ? null : cur.val;
    }

    /**
     * 判断链表是否包含某个值
     */
    public static <T> boolean contains(ListNode<T> head, T val) {
        ListNode<T> cur = head;
        while (cur != null) {
            if (cur.val != null && cur.val.equals(val)) return true;
            cur = cur.next;
        }
        return false;
    }

    /**
     * 将链表转换为 List
     */
    public static <T> List<T> toList(ListNode<T> head) {
        List<T> list = new ArrayList<>();
        ListNode<T> cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        return list;
    }

    // ==================== 插入操作 ====================

    /**
     * 在链表尾部追加节点
     */
    public static <T> ListNode<T> appendNode(ListNode<T> head, T val) {
        ListNode<T> newNode = new ListNode<>(val);
        if (head == null) return newNode;
        ListNode<T> cur = head;
        while (cur.next != null) cur = cur.next;
        cur.next = newNode;
        return head;
    }

    /**
     * 在第 index 位置插入节点（从0开始），超出范围则追加到末尾
     */
    public static <T> ListNode<T> insertAt(ListNode<T> head, int index, T val) {
        ListNode<T> dummy = new ListNode<>(null, head);
        ListNode<T> cur = dummy;
        for (int i = 0; i < index && cur.next != null; i++) {
            cur = cur.next;
        }
        cur.next = new ListNode<>(val, cur.next);
        return dummy.next;
    }

    // ==================== 删除操作 ====================

    /**
     * 删除第 index 个节点（从0开始）
     */
    public static <T> ListNode<T> deleteAt(ListNode<T> head, int index) {
        if (head == null || index < 0) return head;
        ListNode<T> dummy = new ListNode<>(null, head);
        ListNode<T> cur = dummy;
        for (int i = 0; i < index; i++) {
            if (cur.next == null) return dummy.next;
            cur = cur.next;
        }
        if (cur.next != null) cur.next = cur.next.next;
        return dummy.next;
    }

    /**
     * 删除链表中所有值等于 val 的节点
     */
    public static <T> ListNode<T> deleteByValue(ListNode<T> head, T val) {
        ListNode<T> dummy = new ListNode<>(null, head);
        ListNode<T> cur = dummy;
        while (cur.next != null) {
            if (cur.next.val != null && cur.next.val.equals(val)) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    /**
     * 删除倒数第 n 个节点（n 从1开始）
     */
    public static <T> ListNode<T> removeNthFromEnd(ListNode<T> head, int n) {
        ListNode<T> dummy = new ListNode<>(null, head);
        ListNode<T> fast = dummy, slow = dummy;
        for (int i = 0; i <= n; i++) {
            if (fast == null) return dummy.next;
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    /**
     * 去除有序链表中的重复节点
     */
    public static <T> ListNode<T> removeDuplicatesSorted(ListNode<T> head) {
        ListNode<T> cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val != null && cur.val.equals(cur.next.val)) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * 去除无序链表中的重复节点（保留第一次出现）
     */
    public static <T> ListNode<T> removeDuplicates(ListNode<T> head) {
        Set<T> seen = new HashSet<>();
        ListNode<T> dummy = new ListNode<>(null, head);
        ListNode<T> cur = dummy;
        while (cur.next != null) {
            if (!seen.add(cur.next.val)) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    // ==================== 变换操作 ====================

    /**
     * 反转链表
     */
    public static <T> ListNode<T> reverse(ListNode<T> head) {
        ListNode<T> prev = null;
        ListNode<T> cur = head;
        while (cur != null) {
            ListNode<T> next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    /**
     * 合并两个有序链表（升序，基于 Comparable）
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T extends Comparable<T>> ListNode<T> mergeSorted(ListNode<T> l1, ListNode<T> l2) {
        ListNode<T> dummy = new ListNode<>(null);
        ListNode<T> cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val.compareTo(l2.val) <= 0) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    /**
     * 获取链表中间节点（偶数长度时返回第二个中间节点）
     */
    public static <T> ListNode<T> middleNode(ListNode<T> head) {
        ListNode<T> slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // ==================== 检测操作 ====================

    /**
     * 检测链表是否有环
     */
    public static <T> boolean hasCycle(ListNode<T> head) {
        ListNode<T> slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /**
     * 获取环的入口节点，无环返回 null
     */
    public static <T> ListNode<T> detectCycle(ListNode<T> head) {
        ListNode<T> slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode<T> ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    /**
     * 判断链表是否是回文结构
     */
    public static <T> boolean isPalindrome(ListNode<T> head) {
        if (head == null || head.next == null) return true;
        // 找中间节点
        ListNode<T> slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 反转后半部分
        ListNode<T> secondHalf = reverse(slow);
        ListNode<T> copy = secondHalf;
        ListNode<T> first = head;
        boolean result = true;
        while (copy != null) {
            if (!first.val.equals(copy.val)) {
                result = false;
                break;
            }
            first = first.next;
            copy = copy.next;
        }
        // 恢复链表（可选）
        reverse(secondHalf);
        return result;
    }

    /**
     * 找两个链表的相交节点，无相交返回 null
     */
    public static <T> ListNode<T> getIntersectionNode(ListNode<T> headA, ListNode<T> headB) {
        if (headA == null || headB == null) return null;
        ListNode<T> a = headA, b = headB;
        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }
        return a;
    }
}
