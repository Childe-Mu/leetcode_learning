package com.moon.leetcode;

// 518. 零钱兑换 II
//
//给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
//
// 示例 1:
//
// 输入: amount = 5, coins = [1, 2, 5]
//输出: 4
//解释: 有四种方式可以凑成总金额:
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
//
// 示例 2:
//
// 输入: amount = 3, coins = [2]
//输出: 0
//解释: 只用面额2的硬币不能凑成总金额3。
//
// 示例 3:
//
// 输入: amount = 10, coins = [10]
//输出: 1
//
// 注意:
//
// 你可以假设：
//
// 0 <= amount (总金额) <= 5000
// 1 <= coin (硬币面额) <= 5000
// 硬币种类不超过 500 种
// 结果符合 32 位符号整数
//
// 👍 437 👎 0
public class No518_change {
    public static void main(String[] args) {
        System.out.println(new No518_change().change_v2(5, new int[]{1, 2, 5}));
    }

    public int change_v1(int amount, int[] coins) {
        int n = coins.length;
        int[][] f = new int[n + 1][amount + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int coin = coins[i - 1];
            for (int j = 0; j <= amount; j++) {
                if (j < coin) {
                    f[i][j] = f[i - 1][j];
                } else {
                    f[i][j] = f[i - 1][j] + f[i][j - coin];
                }
            }
        }
        return f[n][amount];
    }

    public int change_v2(int amount, int[] coins) {
        int n = coins.length;
        int[] f = new int[amount + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            int coin = coins[i - 1];
            for (int j = coin; j <= amount; j++) {
                f[j] = f[j] + f[j - coin];
            }
        }
        return f[amount];
    }
}
