package com.moon.leetcode;

/**
 * 746. 使用最小花费爬楼梯
 * <p>
 * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
 * <p>
 * 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
 * <p>
 * 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
 * <p>
 * 示例 1：
 * <p>
 * 输入：cost = [10, 15, 20]
 * 输出：15
 * 解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出：6
 * 解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。
 * <p>
 * 提示：
 * <p>
 * cost 的长度范围是 [2, 1000]。
 * cost[i] 将会是一个整型数据，范围为 [0, 999] 。
 */
public class No746_minCostClimbingStairs {
    public static int minCostClimbingStairs_v1(int[] cost) {
        int n = cost.length;
        int[] f = new int[n];
        f[0] = cost[0];
        f[1] = cost[1];
        for (int i = 2; i < n; i++) {
            f[i] = Math.min(f[i - 1], f[i - 2]) + cost[i];
        }
        return Math.min(f[n - 1], f[n - 2]);
    }

    public static int minCostClimbingStairs_v2(int[] cost) {
        int n = cost.length;
        int preTwo = cost[0];
        int preOne = cost[1];
        for (int i = 2; i < n; i++) {
            int temp = preOne;
            preOne = Math.min(preTwo, preOne) + cost[i];
            preTwo = temp;
        }
        return Math.min(preTwo, preOne);
    }

    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs_v2(new int[]{0, 0, 0, 1}));
    }
}
