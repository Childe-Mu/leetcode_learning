package com.moon.leetcode;

import java.util.Arrays;

/**
 * 132. 分割回文串 II
 * <p>
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 * <p>
 * 返回符合要求的 最少分割次数 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就s分割成 ["aa","b"] 这样两个回文子串。
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "a"
 * 输出：0
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "ab"
 * 输出：1
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 */
public class No132_minCut {

    public int minCut(String s) {
        int n = s.length();
        // f[i][j](0<=j<=i<=n)表示字符串s中，下标j到i的字符串是否为回文字符串
        // 故:
        // f[i][j] = true (i=j)
        // f[i][j] = s.charAt(i) == s.charAt(j) (i-j==1)
        // f[i][j] = f[i-1][j+1] && s.charAt(i) == s.charAt(j) (i-j>=2)
        boolean[][] f = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j) {
                    f[i][j] = true;
                } else if (i - j == 1 && s.charAt(i) == s.charAt(j)) {
                    f[i][j] = true;
                } else if (f[i - 1][j + 1] && i - j >= 2 && s.charAt(i) == s.charAt(j)) {
                    f[i][j] = true;
                }
            }
        }

        // ff[i]表示字符串s的子串si(下标0-i)最少切分数
        // 0<=j<i<=n,如果子串s(j+1,i)是一个回文串，那么ff[i]=min(ff[j])+1
        // 即，要找到一个0<=j<i，并且ff[j]最小
        int[] ff = new int[n];
        Arrays.fill(ff, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            if (f[i][0]) {
                ff[i] = 0;
            } else {
                for (int j = 0; j < i; j++) {
                    if (f[i][j + 1]) {
                        ff[i] = Math.min(ff[i], ff[j] + 1);
                    }
                }
            }
        }

        return ff[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new No132_minCut().minCut("aaabaa"));
    }
}
