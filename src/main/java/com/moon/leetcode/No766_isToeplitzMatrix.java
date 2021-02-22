package com.moon.leetcode;

/**
 * 766. 托普利茨矩阵
 * <p>
 * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 * <p>
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * 输出：true
 * 解释：
 * 在上述矩阵中, 其对角线为:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
 * 各条对角线上的所有元素均相同, 因此答案是 True 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：matrix = [[1,2],[2,2]]
 * 输出：false
 * 解释：
 * 对角线 "[1, 2]" 上的元素不同。
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 20
 * 0 <= matrix[i][j] <= 99
 * <p>
 * 进阶：
 * <p>
 * 如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？
 * 如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？
 */
public class No766_isToeplitzMatrix {
    /**
     * 暴力遍历
     */
    public static boolean isToeplitzMatrix_v1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 优化v1,整个矩阵只遍历一次
     */
    public static boolean isToeplitzMatrix_v2(int[][] matrix) {
        int l1 = matrix.length;
        int l2 = matrix[0].length;
        for (int i = 0; i < l1; i++) {
            int temp = matrix[i][0];
            for (int j = i + 1, k = 1; j < l1 && k < l2; j++, k++) {
                if (temp != matrix[j][k]) {
                    return false;
                }
            }
        }
        for (int i = 1; i < l2; i++) {
            int temp = matrix[0][i];
            for (int j = 1, k = i + 1; j < l1 && k < l2; j++, k++) {
                if (temp != matrix[j][k]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[2][3];
        System.out.println(isToeplitzMatrix_v2(new int[][]{{1}, {2}}));
    }
}
