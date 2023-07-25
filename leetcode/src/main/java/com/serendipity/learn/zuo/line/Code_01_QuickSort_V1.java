package com.serendipity.learn.zuo.line;
import com.serendipity.utils.*;
/**
 * 快排1.0
 */
public class Code_01_QuickSort_V1 {
    /*
       算法描述:采用递归算法，不断去确定数组最后一个元素的位置。最终使得数组有序
       partition算法描述：
       首先划分值边界下标=l-1，遍历下标为l
       在遍历下标index < r 的条件下遍历
            如果找到一个元素比右值小，则把这个元素和划分值的下一个元素进行交换，同时下标+1
            如果遍历的元素比右值大，遍历下标+1

       当遍历所有的元素之后，把右值与划分值的下一个元素进行交换
     */
    public void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        process(array, 0, array.length - 1);
    }

    public void process(int[] nums, int l, int r) {
        if (l >= r) return;
        int M = partition(nums, l, r);
        process(nums, l, M - 1);
        process(nums, M + 1, r);
    }

    //数组在l 和r上做划分值，比nums[r]小的在左边，比nums[r]大的在右边 小 < num[r] <大
    public int partition(int[] nums, int l, int r) {
        if (l > r) return -1;
        if (l == r) return l;

        //比nums[r]小的左边界
        int less = l - 1;
        //遍历下标
        int index = l;
        while (index < r) {
            // 如果当前元素比划分值小，当前元素与左边界的下一个元素交换
            if (nums[index] < nums[r]) {
                ArrayUtils.swap(nums, ++less, index++);
            } else {
                index++;
            }
        }
        //将最后nums[r]与左边界的最后一个元素交换
        ArrayUtils.swap(nums, ++less, r);
        return less;
    }

    //-----------------------复习------------------------------------
    public void sort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public void process2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = partition2(arr, l, r);
        process2(arr, l, mid - 1);
        process2(arr, mid + 1, r);
    }

    public int partition2(int[] arr, int l, int r) {
        if (l == r) return l;
        int index = l;
        int less = l - 1;
        while (index < r) {
            if (arr[index] < arr[r]) {
                less++;
                ArrayUtils.swap(arr, index, less);
                index++;
            } else {
                index++;
            }
        }
        less++;
        ArrayUtils.swap(arr, r, less);
        return less;
    }


    //快排1.0
    public static void sort3(int[] array) {

        if (array == null || array.length == 0) {
            return;
        }
        sort3(array, 0, array.length - 1);
    }

    public static void sort3(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = partition3(array, start, end);
        sort3(array, start, mid - 1);
        sort3(array, mid + 1, end);
    }

    public static int partition3(int[] array, int start, int end) {
        if (start == end) {
            return start;
        }
        int pre = start - 1;
        int i = start;

        while (i < end) {
            if (array[i] <= array[end]) {
                ArrayUtils.swap(array, i, ++pre);
            }
            i++;
        }
        ArrayUtils.swap(array, ++pre, end);
        return pre;
    }


    public static void main(String[] args) {
        int[] ints = ArrayUtils.generateRandomArray(10, 200, 5);
        ArrayUtils.printArr(ints);
        new Code_01_QuickSort_V1().sort3(ints);
        ArrayUtils.printArr(ints);
    }
}
