package com.moon.leetcode.offer;

// 剑指 Offer 15. 二进制中1的个数
//
//请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入
//9，则该函数输出 2。
//
// 示例 1：
//
//输入：00000000000000000000000000001011
//输出：3
//解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
//
// 示例 2：
//
//输入：00000000000000000000000010000000
//输出：1
//解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
//
// 示例 3：
//
//输入：11111111111111111111111111111101
//输出：31
//解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
//
// 提示：
//
// 输入必须是长度为 32 的 二进制串 。
// 注意：本题与主站 191 题相同：https://leetcode-cn.com/problems/number-of-1-bits/
// Related Topics 位运算
// 👍 101 👎 0
public class Offer_15_hammingWeight {
    public static void main(String[] args) {
        System.out.println(new Offer_15_hammingWeight().hammingWeight_v2(0b11111111111111111111111111111101));
    }

    public int hammingWeight_v1(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    public int hammingWeight_v2(int n) {
        int count = 0;
        while (n != 0) {
            System.out.println(Integer.toBinaryString(n));
            if ((n & 1) == 1) {
                count++;
            }
            //注意，一定要无符号右移，即>>>，否则不对
            //一般情况下是补零，但在处理有符号数的时候会因计算机系统的不同而不同。有符号数高位是零，则右移时高位补零；
            // 如果是负数，即高位是1，那么，有的系统会移入1，称算术右移，有的会移入0，称逻辑右移。
            n = n >>> 1;
        }
        return count;
    }
}
