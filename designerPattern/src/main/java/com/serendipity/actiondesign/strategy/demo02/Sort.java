package com.serendipity.actiondesign.strategy.demo02;


public class Sort<T> {

    public void sort(T[] array, Comparator<T> comparator) {
        //for循环体内是需要排序的元素
        for (int i = 1; i < array.length; i++) {
            //下标指向已经拍好序的数组的最后一个元素
            int sortedLastIndex = i - 1;
            T temp = array[i];
            //遍历已经排序好的数组
            while (sortedLastIndex >= 0 && comparator.compareTo(temp, array[sortedLastIndex]) < 0) {
                sortedLastIndex--;
            }
            for (int j = i; j > sortedLastIndex + 1; j--) {
                array[j] = array[j - 1];
            }
            array[sortedLastIndex + 1] = temp;
        }
    }
}
