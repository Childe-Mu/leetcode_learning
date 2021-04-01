package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1006. 笨阶乘
 * <p>
 * 通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 *
 * 3 * 2 * 1。
 * <p>
 * 相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，加法(+)
 * 和减法(-)。
 * <p>
 * 例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。然而，这些运算仍然使用通常的算术运算顺序：我
 * 们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。
 * <p>
 * 另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。这保证结果是一个整数。
 * <p>
 * 实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。
 * <p>
 * 示例 1：
 * <p>
 * 输入：4
 * 输出：7
 * 解释：7 = 4 * 3 / 2 + 1
 * <p>
 * 示例 2：
 * <p>
 * 输入：10
 * 输出：12
 * 解释：12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
 * <p>
 * 提示：
 * <p>
 * 1 <= N <= 10000
 * -2^31 <= answer <= 2^31 - 1 （答案保证符合 32 位整数。）
 */
public class No1006_clumsy {
    public static void main(String[] args) {
        System.out.println(new No1006_clumsy().clumsy_v3(10));
    }

    public int clumsy(int N) {
        int ans = -1;
        int i = N;
        boolean f = true;
        while (i > 0) {
            int j = 3;
            int temp = i--;
            while (j > 0 && i > 0) {
                if (j == 3) {
                    temp = temp * i;
                } else if (j == 2) {
                    temp = temp / i;
                } else {
                    temp = f ? temp + i : temp - i;
                }
                j--;
                i--;
            }
            ans = f ? temp : ans - temp;
            f = false;
        }
        return ans;
    }

    public int clumsy_v2(int N) {
        if (N == 1) {
            return 1;
        } else if (N == 2) {
            return 2;
        } else if (N == 3) {
            return 6;
        } else if (N == 4) {
            return 7;
        }

        if (N % 4 == 0) {
            return N + 1;
        } else if (N % 4 <= 2) {
            return N + 2;
        } else {
            return N - 1;
        }
    }

    public int clumsy_v3(int N) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(N);
        N--;
        int index = 0;
        while (N > 0) {
            if (index % 4 == 0) {
                stack.push(stack.pop() * N);
            } else if (index % 4 == 1) {
                stack.push(stack.pop() / N);
            } else if (index % 4 == 2) {
                stack.push(N);
            } else {
                stack.push(-N);
            }
            N--;
            index++;
        }
        return stack.stream().mapToInt(p -> p).sum();
    }
}
