package com.moon.leetcode;

import java.util.Arrays;

/**
 * 338. 比特位计数
 * <p>
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: [0,1,1]
 * <p>
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * <p>
 * 进阶:
 * <p>
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 */
public class No338_countBits {
    /**
     * 内部接口
     */
    public int[] countBits_v1(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            res[i] = Integer.bitCount(i);
        }
        return res;
    }

    /**
     * 左移 按位与 1
     */
    public static int[] countBits_v2(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            int temp = i;
            int count = 0;
            while (temp > 0) {
                if ((temp & 1) == 1) {
                    count++;
                }
                temp = temp >>> 1;
            }
            res[i] = count;
        }
        return res;
    }

    /**
     * 按位与 性质：
     * 对于任意整数 x，令 x=x&(x-1)，该运算将 x 的二进制表示的最后一个 1 变成 0
     */
    public static int[] countBits_v3(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            int temp = i;
            int count = 0;
            while (temp > 0) {
                count++;
                temp = temp & (temp - 1);
            }
            res[i] = count;
        }
        return res;
    }

    /**
     * 动态规划——最高有效位
     */
    public static int[] countBits_v4(int num) {
        int[] bits = new int[num + 1];
        int highBit = 0;
        for (int i = 1; i <= num; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }

    /**
     * 动态规划——最低有效位
     */
    public static int[] countBits_v5(int num) {
        int[] bits = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }

    /**
     * 动态规划——最低有效位
     */
    public static int[] countBits_v6(int num) {
        int[] bits = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits_v6(8)));
    }
}
