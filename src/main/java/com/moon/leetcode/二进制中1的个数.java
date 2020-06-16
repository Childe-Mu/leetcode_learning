package com.moon.leetcode;

import java.util.Scanner;

public class 二进制中1的个数 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        System.out.println(Integer.toString(N, 2));  // 输出二进制数

        // 解法一
        int count = 0;
        // 比对每一位
        for (int i = 0; i < 32; i++) {
            if ((N & (1 << i)) == (1 << i)) {
                count++;
            }
        }
        System.out.println(count);

        // 解法二
        count = 0;
        for (int i = 0; i < 32; i++) {
            // >>> 运算符用 0 填充高位；>> 运算符用符号位填充高位，没有<<<运算符
            if (((N >>> i) & 1) == 1) {
                count++;
            }
        }
        System.out.println(count);

        // 解法三  把一个整数n减去1，再和原来的整数与运算，会把该整数的最右边的1变成0，
        // 那么，一个整数的二进制中有多少个1，就可以进行多少次这样的操作。循环结束的条件是n为0
        count = 0;
        while (N != 0) {
            N = ((N - 1) & N);
            count++;
        }
        System.out.println(count);
    }
}