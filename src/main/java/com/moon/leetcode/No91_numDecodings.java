package com.moon.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 91. 解码方法
 * <p>
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * <p>
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"111" 可以将 "1" 中的每个 "1" 映射为 "A
 * " ，从而得到 "AAA" ，或者可以将 "11" 和 "1"（分别为 "K" 和 "A" ）映射为 "KA" 。注意，"06" 不能映射为 "F" ，因为 "
 * 6" 和 "06" 不同。
 * <p>
 * 给你一个只含数字的 非空 字符串 num ，请计算并返回 解码 方法的 总数 。
 * <p>
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "0"
 * 输出：0
 * 解释：没有字符映射到以 0 开头的数字。含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。由于没有字符，因此没有有效的方法对此进行
 * 解码，因为所有数字都需要映射。
 * <p>
 * 示例 4：
 * <p>
 * 输入：s = "06"
 * 输出：0
 * 解释："06" 不能映射到 "F" ，因为字符串开头的 0 无法指向一个有效的字符。
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 100
 * s 只包含数字，并且可能包含前导零。
 */
public class No91_numDecodings {
    int res = 0;

    public static void main(String[] args) {
        System.out.println(new No91_numDecodings().numDecodings_v2("111111111111111111111111111111111111111111111"));
    }

    /**
     * v1 暴力递归
     */
    public int numDecodings_v1(String s) {
        Set<String> set = new HashSet<>();
        for (int i = 1; i <= 26; i++) {
            set.add(i + "");
        }
        traverse_v1(s.toCharArray(), 0, set);
        return res;
    }

    private void traverse_v1(char[] chars, int index, Set<String> set) {
        if (index == chars.length) {
            res++;
            return;
        }
        if (index < chars.length && set.contains(String.valueOf(chars[index]))) {
            traverse_v1(chars, index + 1, set);
        }
        if (index + 1 < chars.length && set.contains(String.valueOf(chars[index]) + chars[index + 1])) {
            traverse_v1(chars, index + 2, set);
        }
    }

    /**
     * v2 记忆化搜索
     */
    public int numDecodings_v2(String s) {
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i <= 26; i++) {
            map.put(i + "", (char) (i + 'A' - 1) + "");
        }
        int[] mem = new int[s.length()];
        Arrays.fill(mem, -1);
        return traverse_v2(s.toCharArray(), 0, mem, map);
    }

    private int traverse_v2(char[] chars, int index, int[] mem, Map<String, String> map) {
        if (index == chars.length) {
            return 1;
        }
        if (mem[index] != -1) {
            return mem[index];
        }
        mem[index] = 0;
        if (index < chars.length && map.containsKey(String.valueOf(chars[index]))) {
            mem[index] += traverse_v2(chars, index + 1, mem, map);
        }
        if (index + 1 < chars.length && map.containsKey(String.valueOf(chars[index]) + chars[index + 1])) {
            mem[index] += traverse_v2(chars, index + 2, mem, map);
        }
        return mem[index];
    }

    /**
     * v3 动态规划
     */
    public int numDecodings_v3(String s) {
        Set<String> set = new HashSet<>();
        for (int i = 1; i <= 26; i++) {
            set.add(i + "");
        }
        char[] chars = s.toCharArray();
        if (!set.contains(String.valueOf(chars[0]))) {
            return 0;
        }
        int[] f = new int[s.length() + 1];
        f[0] = 1;
        f[1] = 1;
        for (int i = 1; i < s.length(); i++) {
            f[i + 1] += set.contains(String.valueOf(chars[i])) ? f[i] : 0;
            f[i + 1] += set.contains(String.valueOf(chars[i - 1]) + chars[i]) ? f[i - 1] : 0;
        }
        return f[s.length()];
    }
}
