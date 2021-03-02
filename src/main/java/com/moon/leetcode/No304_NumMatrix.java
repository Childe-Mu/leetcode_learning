package com.moon.leetcode;

/**
 * 304. 二维区域和检索 - 矩阵不可变
 * <p>
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
 * <p>
 * Range Sum Query 2D
 * 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
 * <p>
 * 示例：
 * <p>
 * 给定 matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 * <p>
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * <p>
 * 提示：
 * <p>
 * 你可以假设矩阵不可变。
 * 会多次调用 sumRegion 方法。
 * 你可以假设 row1 ≤ row2 且 col1 ≤ col2 。
 */
public class No304_NumMatrix {

    private final int[][] sum;

    public No304_NumMatrix(int[][] matrix) {
        int n1 = matrix.length;
        int n2 = n1 == 0 ? 0 : matrix[0].length;
        sum = new int[n1 + 1][n2 + 1];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                sum[i + 1][j + 1] = matrix[i][j] + sum[i][j + 1] + sum[i + 1][j] - sum[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row2 + 1][col2 + 1] - sum[row1][col2 + 1] - sum[row2 + 1][col1] + sum[row1][col1];
    }

    public static void main(String[] args) {
//        int[][] ints = {
//                {3, 0, 1, 4, 2},
//                {5, 6, 3, 2, 1},
//                {1, 2, 0, 1, 5},
//                {4, 1, 0, 1, 7},
//                {1, 0, 3, 0, 5}
//        };
//        System.out.println(new No304_NumMatrix(ints).sumRegion(1, 1, 2, 2));

        System.out.println(new int[][]{{}}[0].length);
    }
}
