package com.serendipity.myold.exception.exce;


import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * ClassName ForeachOptimize
 * Description TODO
 * Author 11931
 * Date 2022-11-05:21:02
 * Version 1.0
 * 小小for循环，粘上集合就会出大问题
 **/
@SuppressWarnings("all")
public class ForeachOptimize {
    private static Collection<Integer> left = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
    private static Collection<Integer> right = Arrays.asList(1, 2, 3, 4, 5);
    /*
        集合迭代经常犯的错误
     */
    private static void wrongInterator() {
        // 传统的方式
        int[] xyz = new int[]{1, 2, 3, 4, 5};
        for (int i = 0; i != xyz.length; i++) {
            System.out.println(xyz[i]);
        }
        // 传统方式-迭代器
        for (Iterator<Integer> iterator = left.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
        // 嵌套迭代容易出现的问题
        for (Iterator<Integer> l = left.iterator(); l.hasNext(); ) {
            for (Iterator<Integer> r = right.iterator(); r.hasNext(); ) {
                // 会报出
                System.out.println(l.next() * r.next());
            }
        }
        // 正确的做法
        for (Iterator<Integer> l = left.iterator(); l.hasNext(); ) {
            Integer lnext = l.next();
            for (Iterator<Integer> r = right.iterator(); r.hasNext(); ) {
                System.out.println(lnext * r.next());
            }
        }
        for (Integer le : left) {
            for (Integer ri : right) {
                System.out.println(le * ri);
            }
        }

    }

    private static void square(int value) {
        System.out.println(value * value);
    }

    public static void main(String[] args) {
        // wrongInterator();

        for (Integer integer : left) {
            square(integer);
        }

        left.forEach(l -> square(l));
        left.forEach(ForeachOptimize::square);
    }
}
