package com.moon.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// 剑指 Offer 49. 丑数
//
//我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
//
// 示例:
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
//
// 说明:
//
// 1 是丑数。
// n 不超过1690。
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/
// Related Topics 哈希表 数学 动态规划 堆（优先队列）
// 👍 179 👎 0
public class Offer_49_nthUglyNumber {
    public static void main(String[] args) {
        System.out.println(new Offer_49_nthUglyNumber().nthUglyNumber_v2(10));
    }

    public int nthUglyNumber(int n) {
        int cnt = 1;
        PriorityQueue<Long> queue = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        set.add(1L);
        queue.offer(1L);
        while (!queue.isEmpty()) {
            long pre = queue.poll();
            set.remove(pre);
            if (cnt++ == n) {
                return (int) pre;
            }
            if (set.add(pre * 2)) {
                queue.offer(pre * 2);
            }
            if (set.add(pre * 3)) {
                queue.offer(pre * 3);
            }
            if (set.add(pre * 5)) {
                queue.offer(pre * 5);
            }
        }
        return -1;
    }

    public int nthUglyNumber_v2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }
}
