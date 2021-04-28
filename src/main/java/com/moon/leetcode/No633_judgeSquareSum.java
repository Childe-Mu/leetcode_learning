package com.moon.leetcode;

import java.util.HashSet;
import java.util.Set;

// 633. 平方数之和
//
//给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
//
// 示例 1：
//
// 输入：c = 5
//输出：true
//解释：1 * 1 + 2 * 2 = 5
//
// 示例 2：
//
// 输入：c = 3
//输出：false
//
// 示例 3：
//
// 输入：c = 4
//输出：true
//
// 示例 4：
//
// 输入：c = 2
//输出：true
//
// 示例 5：
//
// 输入：c = 1
//输出：true
//
// 提示：
//
// 0 <= c <= 231 - 1
//
// Related Topics 数学
// 👍 216 👎 0
public class No633_judgeSquareSum {
    public static void main(String[] args) {
        System.out.println(new No633_judgeSquareSum().judgeSquareSum_v1(5));
    }

    public boolean judgeSquareSum_v1(int c) {
        Set<Integer> set = new HashSet<>();
        int sqrt = (int) Math.sqrt(c) + 1;
        for (int i = 0; i < sqrt; i++) {
            set.add(i * i);
            if (set.contains(c - i * i)) {
                return true;
            }
        }
        return false;
    }

    public boolean judgeSquareSum_v2(int c) {
        int left = 0;
        int right = (int) Math.sqrt(c);
        while (left <= right) {
            int sum = left * left + right * right;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }

    /**
     * 费马平方和定理告诉我们：
     * <p>
     * 一个非负整数 cc 如果能够表示为两个整数的平方和，当且仅当 cc 的所有形如 4k + 34k+3 的质因子的幂均为偶数。
     * <p>
     * 证明请见 这里 http://wstein.org/edu/124/lectures/lecture21/lecture21/node2.html。
     * <p>
     * 因此我们需要对 cc 进行质因数分解，再判断所有形如 4k + 34k+3 的质因子的幂是否均为偶数即可。
     */
    public boolean judgeSquareSum_v3(int c) {
        for (int base = 2; base * base <= c; base++) {
            // 如果不是因子，枚举下一个
            if (c % base != 0) {
                continue;
            }
            // 计算 base 的幂
            int exp = 0;
            while (c % base == 0) {
                c /= base;
                exp++;
            }
            // 根据 Sum of two squares theorem 验证
            if (base % 4 == 3 && exp % 2 != 0) {
                return false;
            }
        }
        // 例如 11 这样的用例，由于上面的 for 循环里 base * base <= c ，base == 11 的时候不会进入循环体
        // 因此在退出循环以后需要再做一次判断
        return c % 4 != 3;
    }
}
