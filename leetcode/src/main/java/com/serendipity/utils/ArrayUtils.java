package com.serendipity.utils;


import java.util.Arrays;

/**
 * 对数器
 */
public class ArrayUtils {

    /**
     * 创建随机 int[] 数组，支持多种控制选项
     *
     * @param maxSize      数组最大长度（实际长度在 [1, maxSize] 之间随机）
     * @param maxValue     元素绝对值的最大值（含）
     * @param allowNeg     是否允许负数；true 时元素范围为 [-maxValue, maxValue]，false 时为 [0, maxValue]
     * @param sortOrder    排序方向：0=不排序，1=递增，-1=递减
     * @param allowDup     是否允许重复元素；false 时保证元素唯一（maxValue 需 >= maxSize）
     * @return 满足条件的随机 int 数组
     */
    public static int[] buildArray(int maxSize, int maxValue, boolean allowNeg, int sortOrder, boolean allowDup) {
        if (maxSize <= 0) throw new IllegalArgumentException("maxSize must be > 0");

        // 随机决定长度 [1, maxSize]
        int length = (int) (Math.random() * maxSize) + 1;
        int[] arr = new int[length];

        if (allowDup) {
            // 允许重复：直接随机填充
            for (int i = 0; i < length; i++) {
                arr[i] = randomValue(maxValue, allowNeg);
            }
        } else {
            // 不允许重复：用布尔标记已使用的值
            int range = allowNeg ? maxValue * 2 + 1 : maxValue + 1;
            if (range < length) throw new IllegalArgumentException(
                    "maxValue range (" + range + ") is smaller than required unique count (" + length + ")");
            java.util.Set<Integer> used = new java.util.HashSet<>();
            for (int i = 0; i < length; i++) {
                int v;
                do {
                    v = randomValue(maxValue, allowNeg);
                } while (!used.add(v));
                arr[i] = v;
            }
        }

        // 排序
        if (sortOrder == 1) {
            Arrays.sort(arr);
        } else if (sortOrder == -1) {
            Arrays.sort(arr);
            // 反转为递减
            for (int l = 0, r = arr.length - 1; l < r; l++, r--) {
                int tmp = arr[l];
                arr[l] = arr[r];
                arr[r] = tmp;
            }
        }
        return arr;
    }

    /**
     * 快捷方法：创建允许负数、允许重复、不排序的随机数组
     *
     * @param maxSize  最大长度
     * @param maxValue 元素绝对值最大值
     * @return 随机 int 数组
     */
    public static int[] buildRandom(int maxSize, int maxValue) {
        return buildArray(maxSize, maxValue, true, 0, true);
    }

    /**
     * 快捷方法：创建非负、允许重复、不排序的随机数组
     *
     * @param maxSize  最大长度
     * @param maxValue 元素最大值
     * @return 随机非负 int 数组
     */
    public static int[] buildPositiveRandom(int maxSize, int maxValue) {
        return buildArray(maxSize, maxValue, false, 0, true);
    }

    /**
     * 快捷方法：创建递增（升序）随机数组（元素唯一）
     *
     * @param maxSize  最大长度
     * @param maxValue 元素绝对值最大值
     * @param allowNeg 是否允许负数
     * @return 升序 int 数组
     */
    public static int[] buildAscending(int maxSize, int maxValue, boolean allowNeg) {
        return buildArray(maxSize, maxValue, allowNeg, 1, false);
    }

    /**
     * 快捷方法：创建递减（降序）随机数组（元素唯一）
     *
     * @param maxSize  最大长度
     * @param maxValue 元素绝对值最大值
     * @param allowNeg 是否允许负数
     * @return 降序 int 数组
     */
    public static int[] buildDescending(int maxSize, int maxValue, boolean allowNeg) {
        return buildArray(maxSize, maxValue, allowNeg, -1, false);
    }

    /** 生成一个随机值，allowNeg=true 时范围为 [-maxValue, maxValue] */
    private static int randomValue(int maxValue, boolean allowNeg) {
        int v = (int) (Math.random() * (maxValue + 1)); // [0, maxValue]
        if (allowNeg && Math.random() < 0.5) v = -v;
        return v;
    }

    /**
     * 生成数组
     *
     * @param maxSize  数组的最大长度
     * @param maxValue 数组元素最大值
     * @param flag     0:正数，可重复；>0:正数，不可重复；<0:负数
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue, int flag) {
        // Math.random [0,1)
        // Math.random *N [0,N)
        // Math.random * (N+1)  [0,N]
        int length = 0;
        // 获取一个length=maxSize
        while (length < maxSize - 1) {
            length = (int) ((maxSize + 1) * Math.random());
        }
        Integer[] arr = new Integer[length];
        for (int i = 0; i < arr.length; i++) {
            int num = (int) ((maxValue + 1) * Math.random());
            if (flag > 0) {
                while (Arrays.asList(arr).contains(num)) {
                    num = (int) ((maxValue + 1) * Math.random());
                }
                arr[i] = num;
            } else if (flag == 0) {
                arr[i] = num;
            } else {
                arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random());
            }
        }
        int[] arr_int = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr_int[i] = arr[i];
        }
        return arr_int;
    }


    public static Integer[] intToInteger(int[] array) {
        return Arrays.stream(array).boxed().toArray(Integer[]::new);
    }

    /**
     * 数组拷贝
     *
     * @param arr
     * @return
     */
    public static int[] copyArray(int[] arr) {

        if (arr == null) {
            return null;
        }

        int[] copyArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copyArr[i] = arr[i];
        }
        return copyArr;
    }

    /**
     * 两个数组判断是否相等
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {

        if ((arr1 == null && arr2 != null) || (arr2 == null && arr1 != null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印数组
     *
     * @param arr
     */
    public static void printArr(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 数组元素交换位置
     *
     * @param array
     * @param i
     * @param j
     */
    private static void swap2(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }


    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 位运算
     */
    public static void bitOperation() {

        int a = 8;
        int b = 2;

        // a /(2^b)
        System.out.println(a >> b);
        // 无符号右移
        System.out.println(a >>> b);

        // 或运算
        System.out.println(a | b);

        System.out.println(6 ^ 5);

        int x = 10;
        System.out.println(x & ((~x) + 1));
        System.out.println(x & ((~x) | 1));
        System.out.println(~x & ((x) + 1));
        System.out.println(~x & ((x) | 1));
    }

    public static void main(String[] args) {
        bitOperation();

        System.out.println("\n========== 测试 buildArray 系列方法 ==========");

        // 1. 全参数控制：允许负数、允许重复、不排序
        System.out.println("\n[buildArray] 允许负数 | 允许重复 | 不排序:");
        int[] arr1 = buildArray(10, 20, true, 0, true);
        printArr(arr1);

        // 2. 全参数控制：不允许负数、不允许重复、升序
        System.out.println("\n[buildArray] 非负 | 不重复 | 升序:");
        int[] arr2 = buildArray(10, 50, false, 1, false);
        printArr(arr2);

        // 3. 全参数控制：允许负数、不允许重复、降序
        System.out.println("\n[buildArray] 允许负数 | 不重复 | 降序:");
        int[] arr3 = buildArray(10, 30, true, -1, false);
        printArr(arr3);

        // 4. 快捷方法：buildRandom（含负数、可重复、不排序）
        System.out.println("\n[buildRandom] 含负数 | 可重复 | 不排序:");
        int[] arr4 = buildRandom(8, 15);
        printArr(arr4);

        // 5. 快捷方法：buildPositiveRandom（非负、可重复、不排序）
        System.out.println("\n[buildPositiveRandom] 非负 | 可重复 | 不排序:");
        int[] arr5 = buildPositiveRandom(8, 15);
        printArr(arr5);

        // 6. 快捷方法：buildAscending（升序、元素唯一、允许负数）
        System.out.println("\n[buildAscending] 含负数 | 不重复 | 升序:");
        int[] arr6 = buildAscending(8, 20, true);
        printArr(arr6);

        // 7. 快捷方法：buildDescending（降序、元素唯一、不允许负数）
        System.out.println("\n[buildDescending] 非负 | 不重复 | 降序:");
        int[] arr7 = buildDescending(8, 20, false);
        printArr(arr7);

        // 8. 验证升序数组确实有序
        System.out.println("\n[验证升序] 是否严格不降序:");
        int[] sorted = buildAscending(10, 100, true);
        printArr(sorted);
        boolean ascending = true;
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] < sorted[i - 1]) { ascending = false; break; }
        }
        System.out.println("升序验证结果: " + ascending);

        // 9. 验证降序数组确实有序
        System.out.println("\n[验证降序] 是否严格不升序:");
        int[] desc = buildDescending(10, 100, true);
        printArr(desc);
        boolean descending = true;
        for (int i = 1; i < desc.length; i++) {
            if (desc[i] > desc[i - 1]) { descending = false; break; }
        }
        System.out.println("降序验证结果: " + descending);

        // 10. 验证不重复数组元素唯一
        System.out.println("\n[验证唯一] 不重复数组元素是否唯一:");
        int[] unique = buildArray(10, 50, true, 0, false);
        printArr(unique);
        java.util.Set<Integer> set = new java.util.HashSet<>();
        boolean allUnique = true;
        for (int v : unique) { if (!set.add(v)) { allUnique = false; break; } }
        System.out.println("唯一性验证结果: " + allUnique);
    }
}
