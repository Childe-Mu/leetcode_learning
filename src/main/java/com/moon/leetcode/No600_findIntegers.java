package com.moon.leetcode;

/**
 * 600. 不含连续1的非负整数
 * <p>
 * <p>给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含&nbsp;<strong>连续的1&nbsp;</strong>的个数。</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre><strong>输入:</strong> 5
 * <strong>输出:</strong> 5
 * <strong>解释:</strong>
 * 下面是带有相应二进制表示的非负整数&lt;= 5：
 * 0 : 0
 * 1 : 1
 * 2 : 10
 * 3 : 11
 * 4 : 100
 * 5 : 101
 * 其中，只有整数3违反规则（有两个连续的1），其他5个满足规则。</pre>
 * <p>
 * p><strong>说明:</strong> 1 &lt;= n &lt;= 10<sup>9</sup></p>
 * <div><div>Related Topics</div><div><li>动态规划</li></div></div><br>
 */
public class No600_findIntegers {
    static int N = 50;
    // f[i][j] 为考虑二进制长度为 i，而且最高位为 j（0 or 1）时的合法数个数
    static int[][] f = new int[N][2];

    static {
        f[1][0] = 1;
        f[1][1] = 2;
        for (int i = 1; i < N - 1; i++) {
            f[i + 1][0] = f[i][1];
            f[i + 1][1] = f[i][0] + f[i][1];
        }
    }

    public static void main(String[] args) {
        System.out.println(new No600_findIntegers().findIntegers_v2(5));
    }

    int getLen(int n) {
        for (int i = 31; i >= 0; i--) {
            if (((n >> i) & 1) == 1) {
                return i;
            }
        }
        return 0;
    }

    public int findIntegers_v1(int n) {
        int len = getLen(n);
        int ans = 0;
        int pre = 0;
        for (int i = len; i >= 0; i--) {
            // 当前位是 0 还是 1
            int cur = ((n >> i) & 1);
            // 如果当前位是 1，那么填 0 的话，后面随便填都符合，将方案数累加
            if (cur == 1) {
                ans += f[i + 1][0];
            }
            // 出现连续位为 1，分支结束，方案数被计算完
            if (pre == 1 && cur == 1) {
                break;
            }
            pre = cur;
            // 遍历结束，给结果+1，表示其自身
            if (i == 0) {
                ans++;
            }
        }
        return ans;
    }

    public int findIntegers_v2(int n) {
        int[] dp = new int[32];
        // dp[0]表示一个空的字典树，实际上没有意义，但是因为dp[2]=2，为了方便计算，设置dp[0]=1，实际上dp[0]用不到
        dp[0] = dp[1] = 1;
        for (int i = 2; i < 32; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        int pre = 0;
        int ans = 0;
        for (int i = 5; i >= 0; i--) {
            if (((n >> i) & 1) == 1) {
                // 之所以用dp[i + 1]，是因为第i位是1的时候，表示字典树的层高有i+1层
                ans += dp[i + 1];
                if (pre == 1) {
                    break;
                }
                pre = 1;
            } else {
                pre = 0;
            }
            if (i == 0) {
                ans++;
            }
        }
        return ans;
    }
}
