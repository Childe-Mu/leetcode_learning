package com.moon.leetcode.offer;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

// 剑指 Offer 63. 股票的最大利润
//
//假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
//
// 示例 1:
//
// 输入: [7,1,5,3,6,4]
//输出: 5
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
//
// 示例 2:
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
//
// 限制：
//
// 0 <= 数组长度 <= 10^5
//
// 注意：本题与主站 121 题相同：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-s
//tock/
// Related Topics 数组 动态规划
// 👍 133 👎 0
public class Offer_63_maxProfit {
    public static void main(String[] args) {
        System.out.println(new Offer_63_maxProfit().maxProfit_v2(new int[]{7, 6, 4, 3, 10}));
    }

    public int maxProfit_v1(int[] prices) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        int ans = 0;
        for (int price : prices) {
            if (!queue.isEmpty() && queue.peek() < price) {
                ans = Math.max(price - queue.peek(), ans);
            } else {
                queue.offer(price);
            }
        }
        return ans;
    }

    public int maxProfit_v2(int[] prices) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int price : prices) {
            if (stack.isEmpty() || stack.peek() > price) {
                stack.push(price);
            } else {
                ans = Math.max(price - stack.peek(), ans);
            }
        }
        return ans;
    }

    public int maxProfit_v3(int[] prices) {
        int ans = 0;
        int min = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price < min) {
                min = price;
            } else {
                ans = Math.max(price - min, ans);
            }
        }
        return ans;
    }
}
