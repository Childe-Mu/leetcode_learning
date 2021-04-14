package com.moon.leetcode;

import java.util.Arrays;

// 剑指 Offer 29. 顺时针打印矩阵
//
//输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
//
// 示例 1：
//
// 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
//
// 示例 2：
//
// 输入：matrix =[[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
//
// 限制：
//
// 0 <= matrix.length <= 100
// 0 <= matrix[i].length <= 100
//
// 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
// Related Topics 数组
// 👍 228 👎 0
public class Offer_29_spiralOrder {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Offer_29_spiralOrder().spiralOrder(new int[][]{{9, 10, 11, 12}})));
    }

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] res = new int[m * n];
        int l = 0, r = n - 1;
        int t = 0, b = m - 1;
        int count = 0, status = 0;
        while (count < m * n) {
            if (status == 0) {
                for (int i = l; i <= r; i++) {
                    res[count++] = matrix[t][i];
                }
                t++;
                status = 1;
            } else if (status == 1) {
                for (int i = t; i <= b; i++) {
                    res[count++] = matrix[i][r];
                }
                r--;
                status = 2;
            } else if (status == 2) {
                for (int i = r; i >= l; i--) {
                    res[count++] = matrix[b][i];
                }
                b--;
                status = 3;
            } else {
                for (int i = b; i >= t; i--) {
                    res[count++] = matrix[i][l];
                }
                l++;
                status = 0;
            }
        }
        return res;
    }
}

