package com.moon.leetcode;

import java.util.Arrays;

// 1449. 数位成本和为目标值的最大数字
//
//给你一个整数数组 cost 和一个整数 target 。请你返回满足如下规则可以得到的 最大 整数：
//
// 给当前结果添加一个数位（i + 1）的成本为 cost[i] （cost 数组下标从 0 开始）。
// 总成本必须恰好等于 target 。
// 添加的数位中没有数字 0 。
//
// 由于答案可能会很大，请你以字符串形式返回。
//
// 如果按照上述要求无法得到任何整数，请你返回 "0" 。
//
// 示例 1：
//
//输入：cost = [4,3,2,5,6,7,2,5,5], target = 9
//输出："7772"
//解释：添加数位 '7' 的成本为 2 ，添加数位 '2' 的成本为 3 。所以 "7772" 的代价为 2*3+ 3*1 = 9 。 "977" 也是满足要
//求的数字，但 "7772" 是较大的数字。
// 数字     成本
//  1  ->   4
//  2  ->   3
//  3  ->   2
//  4  ->   5
//  5  ->   6
//  6  ->   7
//  7  ->   2
//  8  ->   5
//  9  ->   5
//
// 示例 2：
//
//输入：cost = [7,6,5,5,5,6,8,7,8], target = 12
//输出："85"
//解释：添加数位 '8' 的成本是 7 ，添加数位 '5' 的成本是 5 。"85" 的成本为 7 + 5 = 12 。
//
// 示例 3：
//
//输入：cost = [2,4,6,2,4,6,4,4,4], target = 5
//输出："0"
//解释：总成本是 target 的条件下，无法生成任何整数。
//
// 示例 4：
//
//输入：cost = [6,10,15,40,40,40,40,40,40], target = 47
//输出："32211"
//
// 提示：
//
// cost.length == 9
// 1 <= cost[i] <= 5000
// 1 <= target <= 5000
//
// Related Topics 字符串 动态规划
// 👍 118 👎 0
public class No1449_largestNumber {
    public static void main(String[] args) {
        System.out.println(new No1449_largestNumber().largestNumber_v2(new int[]{4, 3, 2, 5, 6, 7, 2, 5, 5}, 9));
    }

    public String largestNumber_v1(int[] cost, int target) {
        int[][] f = new int[10][target + 1];
        for (int i = 0; i < 10; ++i) {
            Arrays.fill(f[i], Integer.MIN_VALUE);
        }
        int[][] from = new int[10][target + 1];
        f[0][0] = 0;
        for (int i = 0; i < 9; ++i) {
            int c = cost[i];
            for (int j = 0; j <= target; ++j) {
                if (j < c) {
                    f[i + 1][j] = f[i][j];
                    from[i + 1][j] = j;
                } else {
                    if (f[i][j] > f[i + 1][j - c] + 1) {
                        f[i + 1][j] = f[i][j];
                        from[i + 1][j] = j;
                    } else {
                        f[i + 1][j] = f[i + 1][j - c] + 1;
                        from[i + 1][j] = j - c;
                    }
                }
            }
        }
        if (f[9][target] < 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int i = 9, j = target;
        while (i > 0) {
            if (j == from[i][j]) {
                --i;
            } else {
                sb.append(i);
                j = from[i][j];
            }
        }
        return sb.toString();
    }

    public String largestNumber_v2(int[] cost, int t) {
        int[] f = new int[t + 1];
        Arrays.fill(f, Integer.MIN_VALUE);
        f[0] = 0;
        for (int i = 1; i <= 9; i++) {
            int u = cost[i - 1];
            for (int j = u; j <= t; j++) {
                f[j] = Math.max(f[j], f[j - u] + 1);
            }
        }
        if (f[t] < 0) return "0";
        StringBuilder ans = new StringBuilder();
        for (int i = 9, j = t; i >= 1; i--) {
            int u = cost[i - 1];
            while (j >= u && f[j] == f[j - u] + 1) {
                ans.append(i);
                j -= u;
            }
        }
        return ans.toString();
    }

    public String largestNumber_v3(int[] cost, int target) {
        String[] dp = new String[target + 1];
        //0代表不能合成
        Arrays.fill(dp, "0");
        //    base case
        dp[0] = "";
        for (int i = 1; i < 10; i++) {
            int t = cost[i - 1];
            for (int j = t; j <= target; j++) {
                // 先检验转移状态dp[j-t]能否合成
                String k = dp[j - t];
                // 如果能合成比较两个字符串大小
                if (!k.equals("0")) {
                    String temp = i + k;
                    // 先比较长度
                    if (dp[j].length() < temp.length()) {
                        dp[j] = temp;
                    }
                    // 长度相同时调用compareTo方法
                    if (dp[j].length() == temp.length() && dp[j].compareTo(temp) < 0) {
                        dp[j] = temp;
                    }
                }
            }
        }
        return dp[target];
    }
}
