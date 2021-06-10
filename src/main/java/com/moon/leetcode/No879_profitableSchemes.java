package com.moon.leetcode;

// 879. 盈利计划
//
//集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
//
// 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
//
// 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
//
// 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
//
// 示例 1：
//
//输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
//输出：2
//解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
//总的来说，有两种计划。
//
// 示例 2：
//
//输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
//输出：7
//解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
//有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
//
// 提示：
//
// 1 <= n <= 100
// 0 <= minProfit <= 100
// 1 <= group.length <= 100
// 1 <= group[i] <= 100
// profit.length == group.length
// 0 <= profit[i] <= 100
//
// Related Topics 动态规划
// 👍 178 👎 0
public class No879_profitableSchemes {
    public static void main(String[] args) {
        System.out.println(new No879_profitableSchemes().profitableSchemes_v2(10, 5, new int[]{2, 3, 5}, new int[]{6, 7, 8}));
    }

    public int profitableSchemes_v1(int n, int minProfit, int[] group, int[] profit) {
        int mod = (int) 1e9 + 7;
        int m = group.length;
        long[][][] f = new long[m + 1][n + 1][minProfit + 1];
        for (int i = 0; i <= n; i++) {
            f[0][i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            int g = group[i - 1];
            int p = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    if (j < g) {
                        f[i][j][k] += f[i - 1][j][k] % mod;
                    } else {
                        f[i][j][k] += (f[i - 1][j][k] + f[i - 1][j - g][Math.max(k - p, 0)]) % mod;
                    }
                }
            }
        }
        return (int) f[m][n][minProfit];
    }

    public int profitableSchemes_v2(int n, int minProfit, int[] group, int[] profit) {
        int mod = (int) 1e9 + 7;
        int m = group.length;
        long[][] f = new long[n + 1][minProfit + 1];
        for (int i = 0; i <= n; i++) {
            f[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            int g = group[i - 1];
            int p = profit[i - 1];
            for (int j = n; j >= g; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    f[j][k] = (f[j][k] + f[j - g][Math.max(k - p, 0)]) % mod;
                }
            }
        }
        return (int) f[n][minProfit];
    }
}
