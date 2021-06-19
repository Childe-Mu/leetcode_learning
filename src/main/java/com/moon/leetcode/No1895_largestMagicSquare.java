package com.moon.leetcode;

// 1895. 最大的幻方
//
//一个 k x k 的 幻方 指的是一个 k x k 填满整数的方格阵，且每一行、每一列以及两条对角线的和 全部相等 。幻方中的整数 不需要互不相同 。显然，
//每个 1 x 1 的方格都是一个幻方。
//
// 给你一个 m x n 的整数矩阵 grid ，请你返回矩阵中 最大幻方 的 尺寸 （即边长 k）。
//
// 示例 1：
//
// 输入：grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
//输出：3
//解释：最大幻方尺寸为 3 。
//每一行，每一列以及两条对角线的和都等于 12 。
//- 每一行的和：5+1+6 = 5+4+3 = 2+7+3 = 12
//- 每一列的和：5+5+2 = 1+4+7 = 6+3+3 = 12
//- 对角线的和：5+4+3 = 6+4+2 = 12
//
// 示例 2：
//
// 输入：grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
//输出：2
//
// 提示：
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 50
// 1 <= grid[i][j] <= 106
//
// Related Topics 数组 动态规划
// 👍 3 👎 0
public class No1895_largestMagicSquare {
    public static void main(String[] args) {
        System.out.println(new No1895_largestMagicSquare().largestMagicSquare_v2(new int[][]{
                {5, 1, 3, 1},
                {9, 3, 3, 1},
                {1, 3, 3, 8}}));
    }

    public int largestMagicSquare_v1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int o = Math.min(m, n);
        for (int k = o; k > 1; k--) {
            for (int i = 0; i < m - k + 1; i++) {
                for (int j = 0; j < n - k + 1; j++) {
                    if (checkSquare(grid, k, i, j)) {
                        return k;
                    }
                }
            }
        }
        return 1;
    }

    private boolean checkSquare(int[][] grid, int k, int i, int j) {
        int[] row = new int[k];
        int[] col = new int[k];
        int s1 = 0;
        int s2 = 0;
        for (int x = 0; x < k; x++) {
            for (int y = 0; y < k; y++) {
                int g = grid[i + x][j + y];
                row[x] += g;
                col[y] += g;
                if (y == x) {
                    s1 += g;
                }
                if (y == -x + k - 1) {
                    s2 += g;
                }
            }
        }
        if (row[0] == col[0] && col[0] == s1 && s1 == s2) {
            for (int x = 1; x < k; x++) {
                if (row[x] != row[x - 1] || col[x] != col[x - 1]) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public int largestMagicSquare_v2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // 每一行的前缀和
        int[][] rowSum = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rowSum[i + 1][j + 1] = rowSum[i + 1][j] + grid[i][j];
            }
        }
        // 每一列的前缀和
        int[][] colSum = new int[m + 1][n + 1];
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                colSum[i + 1][j + 1] = colSum[i][j + 1] + grid[i][j];
            }
        }

        // 从大到小枚举边长 edge
        int min = Math.min(m, n);
        for (int k = min; k >= 2; k--) {
            // 枚举正方形的左上角位置 (i,j)
            for (int i = 0; i <= m - k; i++) {
                for (int j = 0; j <= n - k; j++) {
                    if (check(grid, k, i, j, rowSum, colSum)) {
                        return k;
                    }
                }
            }
        }

        return 1;
    }

    private boolean check(int[][] grid, int k, int i, int j, int[][] rowSum, int[][] colSum) {
        // 计算每一行、列、对角线的值应该是多少（以第一行为样本）
        int std = rowSum[i + 1][j + k] - rowSum[i + 1][j];
        // 枚举每一行并用前缀和直接求和
        for (int x = i; x < i + k; ++x) {
            if (rowSum[x + 1][j + k] - rowSum[x + 1][j] != std) {
                return false;
            }
        }
        // 枚举每一列并用前缀和直接求和
        for (int y = j; y < j + k; ++y) {
            if (colSum[i + k][y + 1] - colSum[i][y + 1] != std) {
                return false;
            }
        }
        // d1 和 d2 分别表示两条对角线的和
        // 这里 d 表示 diagonal
        // 这里 d 表示 diagonal
        int d1 = 0, d2 = 0;
        // 不使用前缀和，直接遍历求和
        for (int x = 0; x < k; ++x) {
            d1 += grid[i + x][j + x];
            d2 += grid[i + x][j + k - x - 1];
        }
        return d1 == std && d2 == std;
    }
}
  