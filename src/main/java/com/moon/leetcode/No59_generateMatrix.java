package com.moon.leetcode;

import java.util.Arrays;

/**
 * 59. 螺旋矩阵 II
 * <p>
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：[[1]]
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 20
 */
public class No59_generateMatrix {
    public static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int[][] direct = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int row = 0, column = 0, directIndex = 0;
        for (int i = 1; i <= n * n; i++) {
            res[row][column] = i;
            int nextRow = direct[directIndex][0] + row;
            int nextColumn = direct[directIndex][1] + column;
            if (nextRow < 0 || nextRow >= n || nextColumn < 0 || nextColumn >= n || res[nextRow][nextColumn] != 0) {
                directIndex = (directIndex + 1) % 4;
            }
            row = direct[directIndex][0] + row;
            column = direct[directIndex][1] + column;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(generateMatrix(3)));
    }
}
