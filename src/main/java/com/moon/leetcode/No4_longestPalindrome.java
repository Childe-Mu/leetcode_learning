package com.moon.leetcode;

import java.util.Arrays;

/**
 * 5. 最长回文子串
 * <p>
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "a"
 * 输出："a"
 * <p>
 * 示例 4：
 * <p>
 * 输入：s = "ac"
 * 输出："a"
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 */
public class No4_longestPalindrome {
    public static String longestPalindrome_v1(String s) {
        int n = s.length();
        boolean[][] f = new boolean[n][n];
        for (boolean[] ff : f) {
            Arrays.fill(ff, true);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                //当且仅当其为空串（i<j），或者其长度为 1（i=j），或者首尾字符相同且 s[i+1..j-1] 为回文串
                f[i][j] = f[i - 1][j + 1] && s.charAt(i) == s.charAt(j);
            }
        }
        int l = 0, r = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (f[i][j] && i - j > max) {
                    max = i - j;
                    l = j;
                    r = i;
                }
            }
        }

        return s.substring(l, r + 1);
    }

    public static String longestPalindrome_v2(String s) {
        int n = s.length();
        boolean[][] f = new boolean[n][n];
        for (boolean[] ff : f) {
            Arrays.fill(ff, true);
        }
        int l = 0, r = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                //当且仅当其为空串（i<j），或者其长度为 1（i=j），或者首尾字符相同且 s[i+1..j-1] 为回文串
                f[i][j] = f[i - 1][j + 1] && s.charAt(i) == s.charAt(j);
                if (f[i][j] && i - j > max) {
                    max = i - j;
                    l = j;
                    r = i;
                }
            }
        }
        return s.substring(l, r + 1);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome_v2("babad"));
    }
}
