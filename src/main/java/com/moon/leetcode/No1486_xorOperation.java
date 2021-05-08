package com.moon.leetcode;

// 1486. 数组异或操作
//
//给你两个整数，n 和 start 。
//
// 数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。
//
// 请返回 nums 中所有元素按位异或（XOR）后得到的结果。
//
// 示例 1：
//
// 输入：n = 5, start = 0
//输出：8
//解释：数组 nums 为 [0, 2, 4, 6, 8]，其中 (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8 。
//     "^" 为按位异或 XOR 运算符。
//
// 示例 2：
//
// 输入：n = 4, start = 3
//输出：8
//解释：数组 nums 为 [3, 5, 7, 9]，其中 (3 ^ 5 ^ 7 ^ 9) = 8.
//
// 示例 3：
//
// 输入：n = 1, start = 7
//输出：7
//
// 示例 4：
//
// 输入：n = 10, start = 5
//输出：2
//
// 提示：
//
// 1 <= n <= 1000
// 0 <= start <= 1000
// n == nums.length
//
// Related Topics 位运算 数组
// 👍 59 👎 0
public class No1486_xorOperation {
    public static void main(String[] args) {
        System.out.println(new No1486_xorOperation().xorOperation_v1(6, 5));
    }

    public int xorOperation_v1(int n, int start) {
        int prev = start;
        for (int i = 1; i < n; i++) {
            prev ^= (start + 2 * i);
        }
        return prev;
    }

    public int xorOperation_v2(int n, int start) {
        // 整体除以 2，利用 %4 结论计算 ans 中除「最低一位」的结果
        int s = start >> 1;
        int prefix = calc(s - 1) ^ calc(s + n - 1);
        // 利用「奇偶性」计算 ans 中的「最低一位」结果
        int mod = start % 2;
        int last = (n & 1) == 1 ? mod : 0;
//        int last = n & start & 1;
        return prefix << 1 | last;
    }

    int calc(int x) {
        if (x % 4 == 0) return x;
        else if (x % 4 == 1) return 1;
        else if (x % 4 == 2) return x + 1;
        else return 0;
    }
}



