package com.moon.leetcode;

import java.util.Arrays;

/**
 * 629. K个逆序对数组
 * <p>给出两个整数&nbsp;<code>n</code>&nbsp;和&nbsp;<code>k</code>，找出所有包含从&nbsp;<code>1</code>&nbsp;到&nbsp;<code>n</code>&nbsp;的数字，且恰好拥有&nbsp;<code>k</code>&nbsp;个逆序对的不同的数组的个数。</p>
 *
 * <p>逆序对的定义如下：对于数组的第<code>i</code>个和第&nbsp;<code>j</code>个元素，如果满<code>i</code>&nbsp;&lt;&nbsp;<code>j</code>且&nbsp;<code>a[i]</code>&nbsp;&gt;&nbsp;<code>a[j]</code>，则其为一个逆序对；否则不是。</p>
 *
 * <p>由于答案可能很大，只需要返回 答案 mod 10<sup>9</sup>&nbsp;+ 7 的值。</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> n = 3, k = 0
 * <strong>输出:</strong> 1
 * <strong>解释:</strong>
 * 只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> n = 3, k = 1
 * <strong>输出:</strong> 2
 * <strong>解释:</strong>
 * 数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。
 * </pre>
 * <p>
 * <p><strong>说明:</strong></p>
 *
 * <ol>
 * <li>&nbsp;<code>n</code>&nbsp;的范围是 [1, 1000] 并且 <code>k</code> 的范围是 [0, 1000]。</li>
 * </ol>
 * <div><div>Related Topics</div><div><li>动态规划</li></div></div><br><div><li>👍 139</li><li>👎 0</li></div>
 */
public class No629_kInversePairs {
    int mod = (int) 1e9 + 7;

    public int kInversePairs_v1(int n, int k) {
        long[][] f = new long[n + 1][k + 1];
        long[][] sum = new long[n + 1][k + 1];
        f[1][0] = 1;
        Arrays.fill(sum[1], 1);
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                f[i][j] = j < i ? sum[i - 1][j] : sum[i - 1][j - (i - 1) - 1];
                f[i][j] = (f[i][j] + mod) % mod;
                sum[i][j] = j == 0 ? f[i][j] : sum[i][j - 1] + f[i][j];
                sum[i][j] = (sum[i][j] + mod) % mod;
            }
        }
        return (int) f[n][k];
    }

    public int kInversePairs_v2(int n, int k) {
        // dp[n][k] - dp[n][k-1] = dp[n-1][k] - dp[n-1][k-n] if k >= n else dp[n-1][k]
        int[] dp = new int[k + 1];
        dp[0] = 1;
        for (int i = 2; i <= n; i++) {
            int[] t = new int[k + 1];
            t[0] = 1;
            for (int j = 1; j <= k; j++) {
                t[j] = (t[j - 1] + dp[j]) % mod;
                if (j >= i) {
                    t[j] = (t[j] - dp[j - i] + mod) % mod;
                }
            }
            dp = t;
        }
        return dp[k];
    }

    //  f[i][j]   =               f[i-1][j] + ... + f[i-1][j-i+2] + f[i-1][j-i+1]
    //  f[i][j+1] = f[i-1][j+1] + f[i-1][j] + ... + f[i-1][j-i+2]
    //  f[i][j] = f[i-1][j] -f[i-1][j-i] + f[i][j-1]
    public int kInversePairs(int n, int k) {
        long[][] f = new long[n + 1][k + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            f[i][0] = 1;
            for (int j = 1; j <= k; j++) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
                if (j >= i) {
                    f[i][j] -= f[i - 1][j - i];
                }
                f[i][j] = (f[i][j] + mod) % mod;
            }
        }
        return (int) f[n][k];
    }
}
