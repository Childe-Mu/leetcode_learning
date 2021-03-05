package com.moon.leetcode;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * <p>
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 示例 1：
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * <p>
 * 示例 2：
 * <p>
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * <p>
 * 示例 3：
 * <p>
 * 输入：coins = [1], amount = 0
 * 输出：0
 * <p>
 * 示例 4：
 * <p>
 * 输入：coins = [1], amount = 1
 * 输出：1
 * <p>
 * 示例 5：
 * <p>
 * 输入：coins = [1], amount = 2
 * 输出：2
 * <p>
 * 提示：
 * <p>
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 */
public class No322_coinChange {
    public static int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] f = new int[amount + 1];
        Arrays.fill(f, max);
        f[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    f[i] = Math.min(f[i - coin] + 1, f[i]);
                }
            }
        }
        return f[amount] == max ? -1 : f[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{2}, 0));
    }
}
