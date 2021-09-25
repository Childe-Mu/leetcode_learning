package com.moon.leetcode;

import java.util.Arrays;

// 552. 学生出勤记录 II
//
//可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
//
// 'A'：Absent，缺勤
// 'L'：Late，迟到
// 'P'：Present，到场
//
//
// 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
//
//
// 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
// 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
//
//
// 给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 109 + 7
//取余 的结果。
//
//
//
// 示例 1：
//
//
//输入：n = 2
//输出：8
//解释：
//有 8 种长度为 2 的记录将被视为可奖励：
//"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
//只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：3
//
//
// 示例 3：
//
//
//输入：n = 10101
//输出：183236316
//
//
//
//
// 提示：
//
//
// 1 <= n <= 105
//
// Related Topics 动态规划
// 👍 232 👎 0
public class No552_checkRecord {
    int mod = (int) 1e9 + 7;
    int[][][] cache;

    public int checkRecord_v1(int n) {
        cache = new int[n + 1][2][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(cache[i][j], -1);
            }
        }
        return dfs(n, 0, 0);
    }

    /**
     * @param u 当前还剩下多少位需要决策
     * @param a 当前方案中 A 的总出现次数
     * @param l 当前方案中结尾 L 的连续出现次数
     */
    private int dfs(int u, int a, int l) {
        if (a >= 2) {
            return 0;
        }
        if (l >= 3) {
            return 0;
        }
        if (cache[u][a][l] != -1) {
            return cache[u][a][l];
        }
        // A
        long ans = dfs(u - 1, a + 1, 0);
        // L
        ans += dfs(u - 1, a, l + 1);
        // P
        ans += dfs(u - 1, a, 0);
        return (int) (ans % mod);
    }

    // 从 f[u][acnt][lcnt] 往回找所依赖的状态
    public int checkRecord_v2(int n) {
        int[][][] f = new int[n + 1][2][3];
        f[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j == 1 && k == 0) { // A
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j - 1][0]) % mod;
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j - 1][1]) % mod;
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j - 1][2]) % mod;
                    }
                    if (k != 0) { // L
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j][k - 1]) % mod;
                    }
                    if (k == 0) { // P
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j][0]) % mod;
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j][1]) % mod;
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j][2]) % mod;
                    }
                }
            }
        }
        int ans = 0;
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 3; k++) {
                ans += f[n][j][k];
                ans %= mod;
            }
        }
        return ans;
    }

    public int checkRecord_v3(int n) {
        final int MOD = 1000000007;
        int[][][] dp = new int[n + 1][2][3]; // 长度，A 的数量，结尾连续 L 的数量
        dp[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            // 以 P 结尾的数量
            for (int j = 0; j <= 1; j++) {
                for (int k = 0; k <= 2; k++) {
                    dp[i][j][0] = (dp[i][j][0] + dp[i - 1][j][k]) % MOD;
                }
            }
            // 以 A 结尾的数量
            for (int k = 0; k <= 2; k++) {
                dp[i][1][0] = (dp[i][1][0] + dp[i - 1][0][k]) % MOD;
            }
            // 以 L 结尾的数量
            for (int j = 0; j <= 1; j++) {
                for (int k = 1; k <= 2; k++) {
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j][k - 1]) % MOD;
                }
            }
        }
        int sum = 0;
        for (int j = 0; j <= 1; j++) {
            for (int k = 0; k <= 2; k++) {
                sum = (sum + dp[n][j][k]) % MOD;
            }
        }
        return sum;
    }

    public int checkRecord_v4(int n) {
        final int MOD = (int) 1e9 + 7;
        int[][] dp = new int[2][3]; // A 的数量，结尾连续 L 的数量
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int[][] dpNew = new int[2][3];
            // 以 P 结尾的数量
            for (int j = 0; j <= 1; j++) {
                for (int k = 0; k <= 2; k++) {
                    dpNew[j][0] = (dpNew[j][0] + dp[j][k]) % MOD;
                }
            }
            // 以 A 结尾的数量
            for (int k = 0; k <= 2; k++) {
                dpNew[1][0] = (dpNew[1][0] + dp[0][k]) % MOD;
            }
            // 以 L 结尾的数量
            for (int j = 0; j <= 1; j++) {
                for (int k = 1; k <= 2; k++) {
                    dpNew[j][k] = (dpNew[j][k] + dp[j][k - 1]) % MOD;
                }
            }
            dp = dpNew;
        }
        int sum = 0;
        for (int j = 0; j <= 1; j++) {
            for (int k = 0; k <= 2; k++) {
                sum = (sum + dp[j][k]) % MOD;
            }
        }
        return sum;
    }
}
