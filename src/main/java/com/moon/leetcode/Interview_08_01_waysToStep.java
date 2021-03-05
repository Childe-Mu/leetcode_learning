package com.moon.leetcode;

/**
 * 面试题 08.01. 三步问题
 * <p>
 * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
 * <p>
 * 示例1:
 * <p>
 * 输入：n = 3
 * 输出：4
 * 说明: 有四种走法
 * <p>
 * 示例2:
 * <p>
 * 输入：n = 5
 * 输出：13
 * <p>
 * 提示:
 * <p>
 * n范围在[1, 1000000]之间
 */
public class Interview_08_01_waysToStep {
    public static int waysToStep_v1(int n) {
        int[] f = new int[n];
        f[0] = 1;
        f[1] = 2;
        f[2] = 4;
        for (int i = 3; i < n; i++) {
            f[i] = ((f[i - 1] + f[i - 2]) % 1000000007 + f[i - 3]) % 1000000007;
        }
        return f[n - 1];
    }

    public static int waysToStep_v2(int n) {
        int[] f = new int[3];
        f[0] = 1;
        f[1] = 2;
        f[2] = 4;
        if (n < 3) {
            return f[n - 1];
        }
        for (int i = 3; i < n; i++) {
            int temp = f[2];
            f[2] = ((temp + f[1]) % 1000000007 + f[0]) % 1000000007;
            f[0] = f[1];
            f[1] = temp;
        }
        return f[2];
    }

    public static void main(String[] args) {
        System.out.println(waysToStep_v2(5));
    }
}
