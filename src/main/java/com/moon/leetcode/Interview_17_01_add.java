package com.moon.leetcode;

/**
 * 面试题 17.01. 不用加号的加法
 * <p>
 * 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
 * <p>
 * 示例:
 * <p>
 * 输入: a = 1, b = 1
 * 输出: 2
 * <p>
 * 提示：
 * <p>
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 */
public class Interview_17_01_add {
    public static int add(int a, int b) {
        while(b != 0) {
            //本位
            int temp1 = a ^ b;
            //进位
            int temp2 = (a & b) << 1;
            a = temp1;
            b = temp2;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(add(6, 5));
    }
}
