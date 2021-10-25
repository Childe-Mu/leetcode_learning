package com.moon.leetcode;

import com.moon.leetcode.offer.Offer_44_findNthDigit;

// 400. 第 N 位数字
//
//在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 位数字。
//
// 注意：n 是正数且在 32 位整数范围内（n < 231）。
//
// 示例 1：
//
//
//输入：3
//输出：3
//
// 示例 2：
//
//输入：11
//输出：0
//解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
//
// Related Topics 数学
// 👍 152 👎 0
public class No400_findNthDigit {
    public static void main(String[] args) {
        System.out.println(new Offer_44_findNthDigit().findNthDigit(10));
    }

    public int findNthDigit(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) {
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit;
        return String.valueOf(num).charAt((n - 1) % digit) - '0';
    }
}
