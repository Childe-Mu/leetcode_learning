package com.moon.leetcode;

import java.util.HashMap;
import java.util.Map;

// 1074. 元素和为目标值的子矩阵数量
//
//给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
//
// 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
//
//
// 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵
//也不同。
//
// 示例 1：
//
//输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
//输出：4
//解释：四个只含 0 的 1x1 子矩阵。
//
// 示例 2：
//
//输入：matrix = [[1,-1],[-1,1]], target = 0
//输出：5
//解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
//
// 示例 3：
//
//输入：matrix = [[904]], target = 0
//输出：0
//
// 提示：
//
// 1 <= matrix.length <= 100
// 1 <= matrix[0].length <= 100
// -1000 <= matrix[i] <= 1000
// -10^8 <= target <= 10^8
//
// Related Topics 数组 动态规划 Sliding Window
// 👍 146 👎 0
public class No1074_numSubmatrixSumTarget {
    public static void main(String[] args) {
        System.out.println(new No1074_numSubmatrixSumTarget().numSubmatrixSumTarget(new int[][]{{0, 1, 1, 1, 0, 1}, {0, 0, 0, 0, 0, 1}, {0, 0, 1, 0, 0, 1}, {1, 1, 0, 1, 1, 0}, {1, 0, 0, 1, 0, 0}}, 0));
    }

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] + matrix[i][j] - sum[i][j];
            }
        }
        int ans = 0;

        if (m > n) {
            for (int i = 1; i <= n; i++) {
                for (int j = i; j <= n; j++) {
                    Map<Integer, Integer> map = new HashMap<>();
                    for (int k = 1; k <= m; k++) {
                        int cur = sum[k][j] - sum[k][i - 1];
                        if (cur == target) {
                            ans++;
                        }
                        if (map.containsKey(cur - target)) {
                            ans += map.get(cur - target);
                        }
                        map.put(cur, map.getOrDefault(cur, 0) + 1);
                    }
                }
            }
        } else {
            for (int i = 1; i <= m; i++) {
                for (int j = i; j <= m; j++) {
                    Map<Integer, Integer> map = new HashMap<>();
                    for (int k = 1; k <= n; k++) {
                        int cur = sum[j][k] - sum[i - 1][k];
                        if (cur == target) {
                            ans++;
                        }
                        if (map.containsKey(cur - target)) {
                            ans += map.get(cur - target);
                        }
                        map.put(cur, map.getOrDefault(cur, 0) + 1);
                    }
                }
            }
        }
        return ans;
    }
}
