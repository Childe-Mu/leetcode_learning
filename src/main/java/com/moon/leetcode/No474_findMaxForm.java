package com.moon.leetcode;

// 474. 一和零
//给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
//
// 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
//
// 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
//
// 示例 1：
//
//输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
//输出：4
//解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
//其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于
//n 的值 3 。
//
// 示例 2：
//
//输入：strs = ["10", "0", "1"], m = 1, n = 1
//输出：2
//解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
//
// 提示：
//
// 1 <= strs.length <= 600
// 1 <= strs[i].length <= 100
// strs[i] 仅由 '0' 和 '1' 组成
// 1 <= m, n <= 100
//
// Related Topics 动态规划
// 👍 486 👎 0
public class No474_findMaxForm {
    public int findMaxForm_v1(String[] strs, int m, int n) {
        int o = strs.length;
        int[][] cnt = new int[o][2];
        for (int i = 0; i < o; i++) {
            for (char c : strs[i].toCharArray()) {
                cnt[i][c - '0']++;
            }
        }
        int[][][] f = new int[o + 1][m + 1][n + 1];
        for (int k = 0; k < o; k++) {
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    if (cnt[k][0] > i || cnt[k][1] > j) {
                        f[k + 1][i][j] = f[k][i][j];
                    } else {
                        f[k + 1][i][j] = Math.max(f[k][i][j], f[k][i - cnt[k][0]][j - cnt[k][1]] + 1);
                    }
                }
            }
        }
        return f[o][m][n];
    }

    public int findMaxForm_v2(String[] strs, int m, int n) {
        int o = strs.length;
        int[][] cnt = new int[o][2];
        for (int i = 0; i < o; i++) {
            for (char c : strs[i].toCharArray()) {
                cnt[i][c - '0']++;
            }
        }
        int[][] f = new int[m + 1][n + 1];
        for (int k = 0; k < o; k++) {
            for (int i = m; i > -1; i--) {
                for (int j = n; j > -1; j--) {
                    if (cnt[k][0] <= i && cnt[k][1] <= j) {
                        f[i][j] = Math.max(f[i][j], f[i - cnt[k][0]][j - cnt[k][1]] + 1);
                    }
                }
            }
        }
        return f[m][n];
    }

    public int findMaxForm_v3(String[] strs, int m, int n) {
        int[][] f = new int[m + 1][n + 1];
        for (String str : strs) {
            int[] cnt = new int[2];
            for (char c : str.toCharArray()) {
                cnt[c - '0']++;
            }
            for (int i = m; i > -1; i--) {
                for (int j = n; j > -1; j--) {
                    if (cnt[0] <= i && cnt[1] <= j) {
                        f[i][j] = Math.max(f[i][j], f[i - cnt[0]][j - cnt[1]] + 1);
                    }
                }
            }
        }
        return f[m][n];
    }


    public int findMaxForm_v4(String[] strs, int m, int n) {
        int[][] f = new int[m + 1][n + 1];
        for (String str : strs) {
            int[] cnt = new int[2];
            for (char c : str.toCharArray()) {
                cnt[c - '0']++;
            }
            for (int i = m; i >= cnt[0]; i--) {
                for (int j = n; j >= cnt[1]; j--) {
                    f[i][j] = Math.max(f[i][j], f[i - cnt[0]][j - cnt[1]] + 1);
                }
            }
        }
        return f[m][n];
    }

    public static void main(String[] args) {
        System.out.println(new No474_findMaxForm().findMaxForm_v2(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
    }
}
