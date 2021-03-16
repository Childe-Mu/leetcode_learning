package com.moon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * <p>
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * <p>
 * 示例 2：
 * <p>
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class No54_spiralOrder {
    public static List<Integer> spiralOrder_v1(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int status = 0;
        int i, j;
        int top = 0, bottom = matrix.length, left = 0, right = matrix[0].length;
        while (top < bottom && left < right) {
            if (status == 0) {
                for (j = left; j < right; j++) {
                    res.add(matrix[top][j]);
                }
                status = 1;
                top++;
            } else if (status == 1) {
                for (i = top; i < bottom; i++) {
                    res.add(matrix[i][right - 1]);
                }
                status = 2;
                right--;
            } else if (status == 2) {
                for (j = right - 1; j >= left; j--) {
                    res.add(matrix[bottom - 1][j]);
                }
                status = 3;
                bottom--;
            } else {
                for (i = bottom - 1; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                status = 0;
                left++;
            }
        }
        return res;
    }

    public List<Integer> spiralOrder_v2(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]);
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }

    public static void main(String[] args) {
        System.out.println(spiralOrder_v1(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
    }
}

