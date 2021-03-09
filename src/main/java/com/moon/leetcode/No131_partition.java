package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 131. 分割回文串
 * <p>
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * <p>
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "a"
 * 输出：[["a"]]
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 */
public class No131_partition {

//    boolean[][] f;
//    List<List<String>> ret = new ArrayList<>();
//    List<String> ans = new ArrayList<>();
//
//    public List<List<String>> partition(String s) {
//        int n = s.length();
//        // f[i][j](0<=j<=i<=n)表示字符串s中，下标j到i的字符串是否为回文字符串
//        // 故:
//        // f[i][j] = true (i=j)
//        // f[i][j] = s.charAt(i) == s.charAt(j) (i-j==1)
//        // f[i][j] = f[i-1][j+1] && s.charAt(i) == s.charAt(j) (i-j>=2)
//        Arrays.fill(f, true);
//        f = new boolean[n][n];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j <= i; j++) {
//                if (i == j) {
//                    f[i][j] = true;
//                } else if (i - j == 1 && s.charAt(i) == s.charAt(j)) {
//                    f[i][j] = true;
//                } else if (f[i - 1][j + 1] && i - j >= 2 && s.charAt(i) == s.charAt(j)) {
//                    f[i][j] = true;
//                }
//            }
//        }
//        dfs(s, 0);
//        return ret;
//    }
//
//    public void dfs(String s, int i) {
//        int n = s.length();
//        if (i == n) {
//            ret.add(new ArrayList<>(ans));
//            return;
//        }
//        for (int j = i; j < n; ++j) {
//            if (f[i][j]) {
//                ans.add(s.substring(i, j + 1));
//                dfs(s, j + 1);
//                ans.remove(ans.size() - 1);
//            }
//        }
//    }


    boolean[][] f;
    List<List<String>> ret = new ArrayList<List<String>>();
    List<String> ans = new ArrayList<String>();
    int n;

    public List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return ret;
    }

    public void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new No131_partition().partition("aab"));
    }
}
