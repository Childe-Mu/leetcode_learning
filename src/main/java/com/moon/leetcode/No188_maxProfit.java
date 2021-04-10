package com.moon.leetcode;

import java.util.Arrays;

// 188. 买卖股票的最佳时机 IV
//
//给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//
// 示例 1：
//
//输入：k = 2, prices = [2,4,1]
//输出：2
//解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
//
// 示例 2：
//
//输入：k = 2, prices = [3,2,6,5,0,3]
//输出：7
//解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
//     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3
//。
//
// 提示：
//
// 0 <= k <= 100
// 0 <= prices.length <= 1000
// 0 <= prices[i] <= 1000
//
// Related Topics 动态规划
// 👍 481 👎 0
public class No188_maxProfit {
    public static void main(String[] args) {
        System.out.println(new No188_maxProfit().maxProfit(2, new int[]{3, 1, 2, 1, 2}));
    }

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        // k最大可以是100，但是如果n/2小于100，实际上做100次买入卖出，是没有意义的，（只能在同一天买入卖出）
        k = Math.min(k, n / 2);
        if (n == 0 || k == 0) {
            return 0;
        }
        int[] buy = new int[k];
        int[] sell = new int[k];
        // 初始化，第1天进行k次买入卖出操作
        Arrays.fill(buy, -prices[0]);
        for (int price : prices) {
            for (int i = 0; i < k; i++) {
                // 第i次买入后的利润，取决于，
                // 1.今天之前第i次买入已经操作了，那就继续持有，
                // 2.今天之前第i次没有买入，那就买入今天的，今天的买入后的利润，是在第i-1次卖出后的利润上计算的
                // 特别的，如果是第一天，那么利润为0
                buy[i] = Math.max(buy[i], (i > 0 ? sell[i - 1] : 0) - price);
                // 第i次卖出后的利润，取决于，
                // 1.今天之前第i次卖出已经操作了，那就不操作，利润还是原来的，
                // 2.今天之前第i次没有卖出，那就今天卖出，利润为第i次买入以后的利润，加上今天的价格
                sell[i] = Math.max(sell[i], buy[i] + price);

                // 注意，buy操作一定在sell操作之前，否则，逻辑上就讲不通，虽然因为取大值操作，导致结果是正确的
            }
        }
        return sell[k - 1];
    }
}
