package com.moon.leetcode;

import java.util.Stack;

/**
 * 1047. 删除字符串中的所有相邻重复项
 * <p>
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * <p>
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * <p>
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * <p>
 * 示例：
 * <p>
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又
 * 只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 * <p>
 * 提示：
 * <p>
 * 1 <= S.length <= 20000
 * S 仅由小写英文字母组成。
 */
public class No1074_removeDuplicates {
    /**
     * 双指针
     */
    public static String removeDuplicates_v1(String S) {
        if (S.length() == 1) {
            return S;
        }
        int left = 0, right = 1;
        StringBuilder sb = new StringBuilder(S);
        while (right < sb.length()) {
            if (sb.charAt(left) == sb.charAt(right)) {
                sb.delete(left, right + 1);
                left = left == 0 ? 0 : left - 1;
                right = left == 0 ? 1 : right - 1;
            } else {
                left++;
                right++;
            }
        }
        return sb.toString();
    }

    /**
     * 栈
     */
    public static String removeDuplicates_v2(String S) {
        Stack<Character> stack = new Stack<>();
        for (char c : S.toCharArray()) {
            if (!stack.empty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }

    /**
     * 栈(v2升级版)
     */
    public static String removeDuplicates_v3(String S) {
        StringBuilder stack = new StringBuilder();
        int top = -1;
        for (int i = 0; i < S.length(); ++i) {
            char ch = S.charAt(i);
            if (top >= 0 && stack.charAt(top) == ch) {
                stack.deleteCharAt(top);
                --top;
            } else {
                stack.append(ch);
                ++top;
            }
        }
        return stack.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates_v2("abbaca"));
    }
}
