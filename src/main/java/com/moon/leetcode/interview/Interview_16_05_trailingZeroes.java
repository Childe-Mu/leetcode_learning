package com.moon.leetcode.interview;

/**
 * 面试题 16.05. 阶乘尾数
 * <p>
 * 设计一个算法，算出 n 阶乘有多少个尾随零。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 * <p>
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: 1
 * 解释:5! = 120, 尾数中有 1 个零.
 * <p>
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 */
public class Interview_16_05_trailingZeroes {
    public static int trailingZeroes(int n) {
        int res = 0;
        while (n > 0) {
            n = n / 5;
            res += n;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(trailingZeroes(25));
    }
}
