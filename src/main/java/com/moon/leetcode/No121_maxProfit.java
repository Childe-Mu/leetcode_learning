package com.moon.leetcode;

/**
 * 121. 买卖股票的最佳时机
 * <p>
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * <p>
 * 示例 2：
 * <p>
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 提示：
 * <p>
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 */
public class No121_maxProfit {
    public static int maxProfit_v2(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else {
                maxProfit = Math.max(price - minPrice, maxProfit);
            }
        }
        return maxProfit;
    }

    public static int maxProfit_v3(int[] prices) {
        int len = prices.length;
        // 假设初始资金为10000元
        // 第一天如果买入持股，则身上剩余的钱为 10000 - 第一天股票价格
        int hold = 10000 - prices[0];
        // 第一天如果清仓不持股，则身上剩余的钱为10000
        int clean = 10000;
        // 从第 2 天开始遍历
        for (int i = 1; i < len; i++) {
            // 今天清仓（手上不持股，全部换成钱），
            // 来源于：
            // 1.昨天如果已经清仓了，那今天的资金就是昨天的，
            // 2.如果昨天还在持股（可能昨天买入，也可能更早之前，不管），今天清仓卖出，
            // 比较1,2的金额，取最大的，说明赚的多
            int curClean = Math.max(clean, hold + prices[i]);
            // 今天买入，或者继续持股
            // 来源于：
            // 1.昨天如果已经在持股了，那今天继续持股，资金就是昨天持股后剩下的，
            // 2.如果昨天没有持股，那今天买入，（因为只能买卖一次），所以认为今天手里的资金还是10000，减去今天的价格，就是剩下的钱
            // 比较1,2的金额，取最大的，说明但是买入的价格更便宜，更赚
            hold = Math.max(hold, 10000 - prices[i]);
            clean = curClean;
        }
        // 最后一天清仓后，减去起始资金
        return clean - 10000;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit_v3(new int[]{7, 6, 5, 4, 3}));
    }

    /**
     * 暴力
     */
    public int maxProfit_v1(int[] prices) {
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit) {
                    maxprofit = profit;
                }
            }
        }
        return maxprofit;
    }
}
