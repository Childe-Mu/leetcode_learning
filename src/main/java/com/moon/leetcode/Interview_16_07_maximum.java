package com.moon.leetcode;

/**
 * 面试题 16.07. 最大数值
 * <p>
 * 编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。
 * <p>
 * 示例：
 * <p>
 * 输入： a = 1, b = 2
 * 输出： 2
 */
public class Interview_16_07_maximum {
    public static int maximum(int a, int b) {
        return (int) ((Math.abs((long) a - (long) b) + (long) a + (long) b) / 2);
    }

    public static int maximum_v2(int a, int b) {
        return (int) ((Math.sqrt(Math.pow(((long) a - (long) b), 2)) + (long) a + (long) b) / 2);
    }


    public static int maximum_v3(int a, int b) {

        long x = (long) a - (long) b;
        //正数flag = 0，负数flag = -1, a > b时，k=0, a<b时，k= -1;
        int k = (int) (x >> 63);
        return (1 + k) * a - b * k;
    }

    public static void main(String[] args) {
        System.out.println(maximum_v2(1, 2));
    }
}
