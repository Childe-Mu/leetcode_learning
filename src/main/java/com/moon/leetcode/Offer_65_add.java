package com.moon.leetcode;

// 剑指 Offer 65. 不用加减乘除做加法
//
//写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
//
// 示例:
//
// 输入: a = 1, b = 1
//输出: 2
//
// 提示：
//
// a, b 均可能是负数或 0
// 结果不会溢出 32 位整数
//
// 👍 159 👎 0
public class Offer_65_add {
    public static void main(String[] args) {
        System.out.println(new Offer_65_add().add_v2(7, 1));
    }

    public int add_v1(int a, int b) {
        while (b != 0) {
            int m = (a & b) << 1;
            int n = a ^ b;
            a = n;
            b = m;
        }
        return a;
    }

    public int add_v2(int a, int b) {
        if (b == 0) {
            return a;
        }
        // 转换成非进位和 + 进位
        return add_v2(a ^ b, (a & b) << 1);
    }
}
