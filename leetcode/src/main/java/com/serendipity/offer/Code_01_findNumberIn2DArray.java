package com.serendipity.offer;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/8/31 10:41 上午
 * FileName: Code_01_findNumberIn2DArray
 * Description: com.toOffer_v2
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 现有矩阵 matrix 如下：
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * <p>
 * 给定 target = 20，返回 false。
 * <p>
 *  
 * <p>
 * 限制：
 * <p>
 * 0 <= n <= 1000
 * <p>
 * 0 <= m <= 1000
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code_01_findNumberIn2DArray {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int i1 = 0; i1 < matrix.length; i1++) {
            for (int i = matrix[0].length - 1; i >= 0; i--) {
                if (matrix[i1][i] == target) return true;
                if (matrix[i1][i] > target) continue;
                if (matrix[i1][i] < target) break;
            }
        }
        return false;
    }
}
