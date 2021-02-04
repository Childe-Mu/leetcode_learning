package com.moon.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 402. 移掉K位数字
 * <p>
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * <p>
 * 注意:
 * <p>
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * <p>
 * 示例 1 :
 * <p>
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * <p>
 * 示例 2 :
 * <p>
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * <p>
 * 示例 3 :
 * <p>
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 */
public class No402_removeKDigits {
    public static String removeKDigits(String num, int k) {
        Deque<Character> q = new LinkedList<>();
        int len = num.length();
        for (int i = 0; i < len; i++) {
            char c = num.charAt(i);
            while (!q.isEmpty() && k > 0 && q.peekLast() > c) {
                q.pollLast();
                k--;
            }
            q.offerLast(c);
        }
        for (int i = 0; i < k; i++) {
            q.pollLast();
        }
        StringBuilder res = new StringBuilder();
        boolean isZero = true;
        while (!q.isEmpty()) {
            Character c = q.pollFirst();
            if (isZero && c == '0') {
                continue;
            }
            isZero = false;
            res.append(c);
        }
        return res.length() == 0 ? "0" : res.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeKDigits("1234567890", 9));
    }
}
