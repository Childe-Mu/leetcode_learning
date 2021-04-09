package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

// 32. 最长有效括号
//
//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
//
// 示例 1：
//
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
//
// 示例 2：
//
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
//
// 示例 3：
//
//输入：s = ""
//输出：0
//
// 提示：
//
// 0 <= s.length <= 3 * 104
// s[i] 为 '(' 或 ')'
//
// Related Topics 字符串 动态规划
// 👍 1257 👎 0
public class No32_longestValidParentheses {
    public static void main(String[] args) {
        System.out.println(new No32_longestValidParentheses().longestValidParentheses_v3("())(())"));
    }

    /**
     * 动态规划
     */
    public int longestValidParentheses_v1(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] f = new int[n + 1];
        int max = 0;
        for (int i = 1; i < n; i++) {
//            if (chars[i] == ')') {
//                if (chars[i - 1] == '(') {
//                    max = Math.max(f[i + 1] = f[i - 1] + 2, max);
//                } else {
//                    int j = i - f[i] - 1;
//                    if (j >= 0) {
//                        max = Math.max(f[i + 1] = chars[j] == '(' ? f[j] + f[i] + 2 : 0, max);
//                    } else {
//                        f[i + 1] = 0;
//                    }
//                }
//            } else {
//                f[i + 1] = 0;
//            }
            if (chars[i] == ')') {
                int j = i - f[i] - 1;
                if (j >= 0) {
                    max = Math.max(f[i + 1] = chars[j] == '(' ? f[j] + f[i] + 2 : 0, max);
                }
            }
        }
        return max;
    }

    /**
     * 统计左右括号
     */
    public int longestValidParentheses_v2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int left = 0, right = 0, max = 0;
        for (char c : chars) {
            if (c == ')') {
                right++;
            } else {
                left++;
            }
            if (left == right) {
                max = Math.max(max, left * 2);
            } else if (left < right) {
                left = right = 0;
            }
        }

        left = right = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (chars[i] == ')') {
                right++;
            } else {
                left++;
            }
            if (left == right) {
                max = Math.max(max, left * 2);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return max;
    }

    /**
     * 栈
     */
    public int longestValidParentheses_v3(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    public int longestValidParentheses_v4(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int left = 0;
        int right = 0;
        int max = 0;
        int curr = 0;
        Stack<int[]> stack = new Stack<>();
        while (i < chars.length) {
            if (chars[i] == '(') {
                if (right > 0) {
                    int[] x = new int[3];
                    x[0] = curr;
                    max = Math.max(curr, max);
                    x[1] = left - right;
                    stack.push(x);
                    left = 0;
                    right = 0;
                    curr = 0;
                }
                left++;
            } else {
                right++;
                if (right > left) {
                    while (!stack.isEmpty() && left < right) {
                        int[] x = stack.pop();
                        curr += x[0];
                        left += x[1];
                    }
                    if (left < right) {
                        max = Math.max(curr, max);
                        right = 0;
                        left = 0;
                        curr = 0;
                    } else {
                        curr += 2;
                    }
                } else {
                    curr += 2;
                }
            }
            i++;
        }
        return Math.max(curr, max);
    }
}
