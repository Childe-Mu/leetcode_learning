package com.moon.leetcode;

import java.util.Arrays;

// 1220. 统计元音字母序列的数目
//给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
//
//
// 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
// 每个元音 'a' 后面都只能跟着 'e'
// 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
// 每个元音 'i' 后面 不能 再跟着另一个 'i'
// 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
// 每个元音 'u' 后面只能跟着 'a'
//
//
// 由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
//
//
//
// 示例 1：
//
// 输入：n = 1
//输出：5
//解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。
//
//
// 示例 2：
//
// 输入：n = 2
//输出：10
//解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
//
//
// 示例 3：
//
// 输入：n = 5
//输出：68
//
//
//
// 提示：
//
//
// 1 <= n <= 2 * 10^4
//
// Related Topics 动态规划
// 👍 132 👎 0
public class No1220_countVowelPermutation {

    private int MOD = (int) 1e9 + 7;

    public int countVowelPermutation_v1(int n) {
        long mod = (long) 1e9 + 7;
        long[][] f = new long[2][5];
        Arrays.fill(f[0], 1);
        for (int i = 1; i < n; i++) {
            f[1][0] = (f[0][1] + f[0][2] + f[0][4]) % mod;
            f[1][1] = (f[0][0] + f[0][2]) % mod;
            f[1][2] = (f[0][1] + f[0][3]) % mod;
            f[1][3] = (f[0][2]) % mod;
            f[1][4] = (f[0][2] + f[0][3]) % mod;
            System.arraycopy(f[1], 0, f[0], 0, 5);
        }
        long[] t = f[0];
        return (int) ((t[0] + t[1] + t[2] + t[3] + t[4]) % mod);
    }

    public int countVowelPermutation_v2(int n) {
        long[][] ans = new long[][]{
                {1}, {1}, {1}, {1}, {1}
        };
        long[][] mat = new long[][]{
                {0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0},
        };
        int x = n - 1;
        while (x != 0) {
            if ((x & 1) != 0) ans = mul(mat, ans);
            mat = mul(mat, mat);
            x >>= 1;
        }
        long sum = 0;
        for (int i = 0; i < 5; i++) sum += ans[i][0];
        return (int) (sum % MOD);
    }

    private long[][] mul(long[][] a, long[][] b) {
        int r = a.length, c = b[0].length, z = b.length;
        long[][] ans = new long[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < z; k++) {
                    ans[i][j] += a[i][k] * b[k][j];
                    ans[i][j] %= MOD;
                }
            }
        }
        return ans;
    }
}
