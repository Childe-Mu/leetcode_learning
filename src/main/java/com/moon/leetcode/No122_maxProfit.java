package com.moon.leetcode;

import java.util.Arrays;

/**
 * 122. 买卖股票的最佳时机 II <br/>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。<br/>
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。<br/>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。<br/>
 * <p/>
 * 示例 1:<br/>
 * 输入: [7,1,5,3,6,4]<br/>
 * 输出: 7<br/>
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。<br/>
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。<br/>
 * <p/>
 * 示例 2:<br/>
 * 输入: [1,2,3,4,5]<br/>
 * 输出: 4<br/>
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。<br/>
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。<br/>
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。<br/>
 * <p/>
 * 示例 3:<br/>
 * 输入: [7,6,4,3,1]<br/>
 * 输出: 0<br/>
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。<br/>
 * <p/>
 * 提示：<br/>
 * 1 <= prices.length <= 3 * 10 ^ 4<br/>
 * 0 <= prices[i] <= 10 ^ 4<br/>
 */
public class No122_maxProfit {
    // /**
    //  * 一次遍历（解不含手续费最简单）
    //  * 连续交易，中心思想就是，当前股票价格大于前一天，则交易一笔，
    //  *
    //  * 时间复杂度：O(n)，遍历一次。
    //  * 空间复杂度：O(1)，需要常量的空间。
    //  */
    // public static int maxProfit(int[] prices) {
    //     int maxProfit = 0;
    //     for (int i = 0; i < prices.length - 1; i++) {
    //         if (prices[i+1] > prices[i]) {
    //             maxProfit += prices[i+1] - prices[i];
    //         }
    //     }
    //     return maxProfit;
    // }

    /**
     * 两层遍历
     * 峰谷法，寻找局部最大的价格差之后再交易，即价格局部形成峰谷
     * <p>
     * 时间复杂度：O(n)。遍历一次。
     * 空间复杂度：O(1)。需要常量的空间。
     */
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int valley;
        int peak;
        int i = 0;
        while (i < prices.length - 1) {
            // 寻找局部价格谷
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i];
            // 寻找局部价格峰
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = prices[i];
            maxProfit += peak - valley;
        }
        return maxProfit;
    }

    // public static void main(String[] args) {
    //     int[] prices = new int[]{7, 1, 5, 3, 6, 4};
    //     System.out.println(maxProfit(prices));
    // }
}