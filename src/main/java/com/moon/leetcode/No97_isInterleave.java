package com.moon.leetcode;

// 97. 交错字符串
//
//给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
//
// 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
//
//
// s = s1 + s2 + ... + sn
// t = t1 + t2 + ... + tm
// |n - m| <= 1
// 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
//
//
// 提示：a + b 意味着字符串 a 和 b 连接。
//
//
//
// 示例 1：
//
//
//输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//输出：true
//
//
// 示例 2：
//
//
//输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//输出：false
//
//
// 示例 3：
//
//
//输入：s1 = "", s2 = "", s3 = ""
//输出：true
//
//
//
//
// 提示：
//
//
// 0 <= s1.length, s2.length <= 100
// 0 <= s3.length <= 200
// s1、s2、和 s3 都由小写英文字母组成
//
// Related Topics 字符串 动态规划
// 👍 419 👎 0

public class No97_isInterleave {
    public static void main(String[] args) {
        System.out.println(new No97_isInterleave().isInterleave_v3("ab", "bc", "abbc"));
    }

    public boolean isInterleave_v1(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return traverse(s1.toCharArray(), 0, s2.toCharArray(), 0, s3.toCharArray(), 0);
    }

    private boolean traverse(char[] c1, int i, char[] c2, int j, char[] c3, int index) {
        if (index == c3.length) {
            return true;
        }
        boolean valid = false;
        if (i < c1.length && c3[index] == c1[i]) {
            valid = traverse(c1, i + 1, c2, j, c3, index + 1);
        }
        if (j < c2.length && c3[index] == c2[j]) {
            valid = valid || traverse(c1, i, c2, j + 1, c3, index + 1);
        }
        return valid;
    }

    public boolean isInterleave_v2(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        Boolean[][] mem = new Boolean[m + 1][n + 1];
        return traverseByMem(s1.toCharArray(), 0, s2.toCharArray(), 0, s3.toCharArray(), 0, mem);
    }

    private boolean traverseByMem(char[] c1, int i, char[] c2, int j, char[] c3, int index, Boolean[][] mem) {
        if (mem[i][j] != null) {
            return mem[i][j];
        }
        if (index == c3.length) {
            return mem[i][j] = false;
        }
        boolean valid = false;

        if (i < c1.length && c3[index] == c1[i]) {
            valid = traverseByMem(c1, i + 1, c2, j, c3, index + 1, mem);
        }
        if (j < c2.length && c3[index] == c2[j]) {
            valid = valid || traverseByMem(c1, i, c2, j + 1, c3, index + 1, mem);
        }
        return mem[i][j] = valid;
    }

    public boolean isInterleave_v3(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i < m; i++) {
            f[i + 1][0] = f[i][0] && s1.charAt(i) == s3.charAt(i);
        }
        for (int j = 0; j < n; j++) {
            f[0][j + 1] = f[0][j] && s2.charAt(j) == s3.charAt(j);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int p = i + j + 1;
//                if (s1.charAt(i) == s3.charAt(p) && s2.charAt(j) == s3.charAt(p)) {
//                    f[i + 1][j + 1] = f[i][j + 1] || f[i + 1][j];
//                } else if (s1.charAt(i) == s3.charAt(p) && s2.charAt(j) != s3.charAt(p)) {
//                    f[i + 1][j + 1] = f[i][j + 1];
//                } else if (s1.charAt(i) != s3.charAt(p) && s2.charAt(j) == s3.charAt(p)) {
//                    f[i + 1][j + 1] = f[i + 1][j];
//                } else {
//                    f[i + 1][j + 1] = false;
//                }
                f[i + 1][j + 1] = (s1.charAt(i) == s3.charAt(p) && f[i][j + 1]) || (s2.charAt(j) == s3.charAt(p) && f[i + 1][j]);
            }
        }
        return f[m][n];
    }
}
