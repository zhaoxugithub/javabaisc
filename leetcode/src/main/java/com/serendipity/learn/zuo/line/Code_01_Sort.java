package com.serendipity.learn.zuo.line;

import com.serendipity.utils.*;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/2/18 19:47
 * FileName: Code_01_Sort
 * Description: com.datastruct.zuo.line
 */
public class Code_01_Sort {

    //归并排序
    public void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        sort(array, 0, array.length - 1);
    }

    public void sort(int[] array, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        sort(array, left, mid);
        sort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    public void merge(int[] array, int left, int mid, int right) {
        int[] helper = new int[right - left + 1];
        int p = left;
        int r = mid + 1;
        int i = 0;
        while (p <= mid && r <= right) {
            if (array[p] <= array[r]) {
                helper[i++] = array[p++];
            } else {
                helper[i++] = array[r++];
            }
        }
        while (p <= mid) {
            helper[i++] = array[p++];
        }

        while (r <= right) {
            helper[i++] = array[r++];
        }

        for (int j = 0; j < helper.length; j++) {
            array[left + j] = helper[j];
        }
    }

    //快排
    public void quickSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    public void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] ints = quickPartition(array, left, right);
        quickSort(array, left, ints[0]);
        quickSort(array, ints[1], right);
    }

    public int[] quickPartition(int[] array, int left, int right) {

        if (left > right) return new int[]{-1, -1};
        if (left == right) {
            return new int[]{left, right};
        }
        int p = left - 1;
        int r = right;
        int i = left;
        while (i < r) {
            if (array[i] < array[right]) {
                ArrayUtils.swap(array, i++, ++p);
            } else if (array[i] > array[right]) {
                //如果当前元素比最后一个元素大，把当前元素和右侧区域左边界的前一个元素交换位置
                ArrayUtils.swap(array, i, --r);
            } else {
                i++;
            }
        }
        ArrayUtils.swap(array, right, r++);
        return new int[]{p, r};
    }


    //堆排序
    public void heapSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            heapInsert(array, i);
        }
        int heapSize = array.length;
        ArrayUtils.swap(array, 0, --heapSize);

        while (heapSize > 0) {
            heapFy(array, 0, heapSize);
            ArrayUtils.swap(array, 0, --heapSize);
        }
    }

    /**
     * 来一个元素调整一下堆结构
     * 从当前元素往上移动
     *
     * @param array
     * @param index 待插入的元素的下标
     *              <p>
     *              <p>
     *              <p>
     *              index：孩子
     *              (index-1)/2:父亲
     */
    public void heapInsert(int[] array, int index) {
        // 如果child比parent大
        while (array[index] > array[(index - 1) / 2]) {
            //父节点和孩子节点交换位置
            ArrayUtils.swap(array, index, (index - 1) / 2);
            //下标往上移动
            index = (index - 1) / 2;
        }
    }

    /**
     * 删除大根堆的堆顶元素，剩下元素还要保证是大根堆
     * 往下看
     *
     * @param array
     * @param index     待删除的元素下标
     * @param heapSize：
     */
    public void heapFy(int[] array, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            //选取左右最大的孩子的下标
            int largest = array[left + 1] > array[left] && left + 1 < heapSize ? left + 1 : left;
            largest = array[index] < array[largest] ? largest : index;

            if (largest == index) {
                break;
            }

            ArrayUtils.swap(array, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void main(String[] args) {
        Code_01_Sort code_01_sort = new Code_01_Sort();
        int[] ints = ArrayUtils.generateRandomArray(10, 200, 5);
        ArrayUtils.printArr(ints);
        code_01_sort.heapSort(ints);
        ArrayUtils.printArr(ints);
    }
}
