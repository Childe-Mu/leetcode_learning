package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// 1190. 反转每对括号间的子串
//
//给出一个字符串 s（仅含有小写英文字母和括号）。
//
// 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
//
// 注意，您的结果中 不应 包含任何括号。
//
// 示例 1：
//
// 输入：s = "(abcd)"
//输出："dcba"
//
// 示例 2：
//
// 输入：s = "(u(love)i)"
//输出："iloveu"
//
// 示例 3：
//
// 输入：s = "(ed(et(oc))el)"
//输出："leetcode"
//
// 示例 4：
//
// 输入：s = "a(bcdefghijkl(mno)p)q"
//输出："apmnolkjihgfedcbq"
//
// 提示：
//
// 0 <= s.length <= 2000
// s 中只有小写英文字母和括号
// 我们确保所有括号都是成对出现的
//
// Related Topics 栈
// 👍 148 👎 0
public class No1190_reverseParentheses {
    public static void main(String[] args) {
        System.out.println(new No1190_reverseParentheses().reverseParentheses_v3("1(ed(et(oc))el)2"));
    }

    public String reverseParentheses_v1(String s) {
        char[] chars = s.toCharArray();
        Deque<Character> deque = new ArrayDeque<>();
        int n = chars.length;
        StringBuilder builder = new StringBuilder();
        for (char aChar : chars) {
            if (aChar == ')') {
                List<Character> tmp = new ArrayList<>();
                while (!deque.isEmpty() && deque.peekLast() != '(') {
                    tmp.add(deque.pollLast());
                }
                deque.pollLast();
                for (Character c : tmp) {
                    deque.offerLast(c);
                }
            } else {
                deque.offerLast(aChar);
            }
        }
        while (!deque.isEmpty()) {
            builder.append(deque.pollFirst());
        }
        return builder.toString();
    }

    public String reverseParentheses_v2(String s) {
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if (ch == ')') {
                sb.reverse();
                sb.insert(0, stack.pop());
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public String reverseParentheses_v3(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                int j = stack.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        int index = 0, step = 1;
        while (index < n) {
            if (chars[index] == '(' || chars[index] == ')') {
                index = pair[index];
                step = -step;
            } else {
                sb.append(chars[index]);
            }
            index += step;
        }
        return sb.toString();
    }
}
