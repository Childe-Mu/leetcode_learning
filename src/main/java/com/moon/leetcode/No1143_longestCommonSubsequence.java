package com.moon.leetcode;

import java.util.Arrays;

/**
 * 1143. 最长公共子序列
 * <p>
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * <p>
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * <p>
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * <p>
 * 示例 3：
 * <p>
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 * <p>
 * 提示：
 * <p>
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 */
public class No1143_longestCommonSubsequence {
    /**
     * 暴力 递归
     */
    public int longestCommonSubsequence_v1(String text1, String text2) {
        if (text1.equals(text2)) {
            return text1.length();
        }
        return traverse(text1.toCharArray(), 0, text2.toCharArray(), 0);
    }

    private static int traverse(char[] c1, int i, char[] c2, int j) {
        if (i == c1.length || j == c2.length) {
            return 0;
        }
        if (c1[i] == c2[j]) {
            return traverse(c1, i + 1, c2, j + 1) + 1;
        } else {
            int l1 = traverse(c1, i + 1, c2, j);
            int l2 = traverse(c1, i, c2, j + 1);
            int l3 = traverse(c1, i + 1, c2, j + 1);
            return Math.max(Math.max(l1, l2), l3);
        }
    }

    /**
     * 记忆化搜索
     */
    public int longestCommonSubsequence_v2(String text1, String text2) {
        if (text1.equals(text2)) {
            return text1.length();
        }
        int[][] mem = new int[text1.length()][text2.length()];
        for (int i = 0; i < text1.length(); i++) {
            Arrays.fill(mem[i], -1);
        }
        return traverseByMem(text1.toCharArray(), 0, text2.toCharArray(), 0, mem);
    }

    private static int traverseByMem(char[] c1, int i, char[] c2, int j, int[][] mem) {
        if (i == c1.length || j == c2.length) {
            return 0;
        }
        if (mem[i][j] != -1) {
            return mem[i][j];
        }
        if (c1[i] == c2[j]) {
            mem[i][j] = traverseByMem(c1, i + 1, c2, j + 1, mem) + 1;
        } else {
            int l1 = traverseByMem(c1, i + 1, c2, j, mem);
            int l2 = traverseByMem(c1, i, c2, j + 1, mem);
            mem[i][j] = Math.max(l1, l2);
        }
        return mem[i][j];
    }

    /**
     * 记忆化搜索
     */
    public int longestCommonSubsequence_v3(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        char[] c1 = text1.toCharArray();
        char[] c2 = text2.toCharArray();
        if (text1.equals(text2)) {
            return m;
        }
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (c1[i] == c2[j]) {
                    f[i + 1][j + 1] = f[i][j] + 1;
                } else {
                    int l1= f[i][j+1];
                    int l2 = f[i+1][j];
                    f[i + 1][j + 1] = Math.max(l1, l2);
                }
            }
        }
        return f[m][n];
    }

    public static void main(String[] args) {
        System.out.println(new No1143_longestCommonSubsequence().longestCommonSubsequence_v3("abcde", "ace"));
    }
}
