package com.serendipity.utils;

import com.serendipity.model.Person;
import com.serendipity.node.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName LinkedListUtil
 * Description TODO
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
}
