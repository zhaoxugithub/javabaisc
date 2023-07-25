package com.serendipity.learn.zuo.recursive;


import com.serendipity.utils.ArrayUtils;

public class Partition {
    /**
     * arr[L...R] 玩荷兰国旗问题的划分，以arr[R] 做划分值
     * <arr[R]放在左侧 ==arr[R]放中间   >arr[R]放右边
     * 返回等于左右边界
     *
     * @return
     */
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (arr == null || L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;
        int index = L;
        int more = R + 1;
        int num = arr[R];
        while (index < more) {
            //如果遍历值比预选值小
            if (arr[index] < num) {
                ArrayUtils.swap(arr, ++less, index++);
            } else if (arr[index] > num) {
                ArrayUtils.swap(arr, --more, index);
            } else {
                index++;
            }
        }
        return new int[]{less + 1, more};
    }
}
