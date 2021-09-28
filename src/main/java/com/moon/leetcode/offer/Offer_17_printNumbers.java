package com.moon.leetcode.offer;

import java.util.Arrays;

// 剑指 Offer 17. 打印从1到最大的n位数
//
//输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
//
// 示例 1:
//
// 输入: n = 1
//输出: [1,2,3,4,5,6,7,8,9]
//
// 说明：
//
// 用返回一个整数列表来代替打印
// n 为正整数
//
// Related Topics 数学
// 👍 107 👎 0
public class Offer_17_printNumbers {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Offer_17_printNumbers().printNumbers(3)));
    }

    public int[] printNumbers(int n) {
        int len = 1;
        while (n > 0) {
            len *= 10;
            n--;
        }
        int[] res = new int[len - 1];
        for (int i = 1; i < len; i++) {
            res[i - 1] = i;
        }
        return res;
    }
}
