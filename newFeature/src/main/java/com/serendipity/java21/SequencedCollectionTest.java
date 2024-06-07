package com.serendipity.java21;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SequencedCollectionTest {

    @Test
    public void test() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(1);
        arrayList.addFirst(0);
        arrayList.addLast(2);

        Integer first = arrayList.getFirst();
        Integer last = arrayList.getLast();

        System.out.println(first);
        System.out.println(last);

        List<Integer> reversed = arrayList.reversed();
        // [2,1,0]
        System.out.println(reversed);
        // [0,1,2]
        System.out.println(arrayList);

        arrayList.addLast(3);

        // [0,1,2,3]
        System.out.println(arrayList);
        // [3,2,1,0]
        System.out.println(reversed);

        Integer i = arrayList.removeFirst();
        Integer i1 = arrayList.removeLast();

        System.out.println(arrayList);
        System.out.println(reversed);
    }


    public static void main(String[] args) {

    }
}
