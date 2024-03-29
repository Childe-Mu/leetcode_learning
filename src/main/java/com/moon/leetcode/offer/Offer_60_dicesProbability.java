package com.moon.leetcode.offer;

import java.util.Arrays;

// 剑指 Offer 60. n个骰子的点数
//
//把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
//
// 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
//
// 示例 1:
//
// 输入: 1
//输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
//
// 示例 2:
//
// 输入: 2
//输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0
//.05556,0.02778]
//
// 限制：
//
// 1 <= n <= 11
// Related Topics 数学 动态规划 概率与统计
// 👍 259 👎 0
public class Offer_60_dicesProbability {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Offer_60_dicesProbability().dicesProbability_v2(2)));
    }

    public double[] dicesProbability_v1(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        for (int i = 2; i <= n; i++) {
            double[] tmp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    tmp[j + k] += dp[j] / 6.0;
                }
            }
            dp = tmp;
        }
        return dp;
    }

    public double[] dicesProbability_v2(int n) {
        double p = (double) 1 / 6;
        double[] res = new double[n * 6 + 1];
        double[] tmp = new double[n * 6 + 1];
        Arrays.fill(res, 1, 7, p);
        for (int i = 2; i <= n; i++) {
            for (int j = i * 6; j >= i; j--) {
                for (int k = 1; k <= 6; k++) {
                    if (i - 1 <= j - k && j - k <= (i - 1) * 6) {
                        tmp[j] += res[j - k] * p;
                    }
                }
            }
            double[] t = tmp;
            tmp = res;
            res = t;
            Arrays.fill(tmp, 0);
        }
        return Arrays.copyOfRange(res, n, n * 6 + 1);
    }
}
