package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 10. 正则表达式匹配
 * <p>
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * <p>
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 示例 2:
 * <p>
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * <p>
 * 示例 4：
 * <p>
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * <p>
 * 示例 5：
 * <p>
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 */
public class No10_isMatch {
    public static void main(String[] args) {
//        System.out.println(new No10_isMatch().isMatch_v2("aa", "a*"));
        System.out.println(new No10_isMatch().isMatch_v4("a", "ab*a"));
    }

    /**
     * v1 递归 暴力寻解
     */
    public boolean isMatch_v1(String s, String p) {
        int n = p.length();
        List<String> pp = new ArrayList<>();
        for (int i = 0; i < n; ) {
            if (i + 1 < n && p.charAt(i + 1) == '*') {
                pp.add("" + p.charAt(i) + p.charAt(i + 1));
                i += 2;
            } else {
                pp.add("" + p.charAt(i));
                i++;
            }
        }
        return match(s, 0, pp, 0);
    }

    private boolean match(String s, int i, List<String> p, int j) {
        System.out.println("i=" + i + " j=" + j);
        if (j == p.size()) {
            return i == s.length();
        } else if (i == s.length()) {
            return p.get(j).length() == 2 && match(s, i, p, j + 1);
        } else {
            String pp = p.get(j);
            boolean b = pp.charAt(0) == '.' || pp.charAt(0) == s.charAt(i);
            System.out.println(s.charAt(i) + " match " + p.get(j));
            if (pp.length() == 2) {
                if (b) {
                    return match(s, i, p, j + 1) || match(s, i + 1, p, j);
                } else {
                    return match(s, i, p, j + 1);
                }
            } else {
                if (b) {
                    return match(s, i + 1, p, j + 1);
                } else {
                    return false;
                }
            }
        }
    }

    /**
     * v1.1 优化递归代码合并判断
     */
    public boolean isMatch_v1_1(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        boolean match = !s.isEmpty() && ((s.charAt(0) == p.charAt(0)) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch_v1_1(s, p.substring(2)) || (match && isMatch_v1_1(s.substring(1), p));
        }
        return match && isMatch_v1_1(s.substring(1), p.substring(1));
    }

    /**
     * v1.2 在1.1的基础上，进一步优化递归代码
     * <p>
     * 通过charAt的方式访问要比char[]的方式慢很多，作为对比，这里附上通过toCharArray转换的版本，前者用时109ms，后者17ms，可见差距之大。这里主要应该是因为charAt方法多了一层栈的深度（需要进出对象）。
     */
    public boolean isMatch_v1_2(String s, String p) {
        char[] ss = s.toCharArray(), pp = p.toCharArray();
        return isMatchChar(ss, 0, pp, 0);
    }

    public boolean isMatchChar(char[] s, int s1, char[] p, int p1) {
        if (p1 >= p.length) return s1 >= s.length;
        boolean match = s1 < s.length && ((s[s1] == p[p1]) || p[p1] == '.');
        if (p.length - p1 >= 2 && p[p1 + 1] == '*')
            return isMatchChar(s, s1, p, p1 + 2) || (match && isMatchChar(s, s1 + 1, p, p1));
        return match && isMatchChar(s, s1 + 1, p, p1 + 1);
    }

    /**
     * v2，记忆化搜索
     */
    public boolean isMatch_v2(String s, String p) {
        int n = p.length();
        List<String> pp = new ArrayList<>();
        for (int i = 0; i < n; ) {
            if (i + 1 < n && p.charAt(i + 1) == '*') {
                pp.add("" + p.charAt(i) + p.charAt(i + 1));
                i += 2;
            } else {
                pp.add("" + p.charAt(i));
                i++;
            }
        }
        int[][] mem = new int[s.length() + 1][pp.size() + 1];
        int res = matchForMem(s, 0, pp, 0, mem);
        System.out.println(Arrays.deepToString(mem));
        return res == 1;
    }

    private int matchForMem(String s, int i, List<String> p, int j, int[][] mem) {
        System.out.println("i=" + i + " j=" + j);
        if (mem[i][j] != 0) {
            System.out.println("mem");
            return mem[i][j];
        } else if (j == p.size()) {
            mem[i][j] = i == s.length() ? 1 : -1;
        } else if (i == s.length()) {
            if (p.get(j).length() == 2) {
                mem[i][j] = matchForMem(s, i, p, j + 1, mem);
            } else {
                mem[i][j] = -1;
            }
        } else {
            String pp = p.get(j);
            boolean b = pp.charAt(0) == '.' || pp.charAt(0) == s.charAt(i);
            System.out.println(s.charAt(i) + " match " + p.get(j));
            if (pp.length() == 2) {
                if (b) {
                    mem[i][j] = (matchForMem(s, i, p, j + 1, mem) == 1)
                            || (matchForMem(s, i + 1, p, j, mem) == 1) ? 1 : -1;
                } else {
                    mem[i][j] = matchForMem(s, i, p, j + 1, mem);
                }
            } else {
                if (b) {
                    mem[i][j] = matchForMem(s, i + 1, p, j + 1, mem);
                } else {
                    mem[i][j] = -1;
                }
            }
        }
        return mem[i][j];
    }

    /**
     * 动态规划
     */
    public boolean isMatch_v3(String s, String p) {
        int m = s.length();
        int n = p.length();
        List<String> pp = new ArrayList<>();
        for (int i = 0; i < n; ) {
            if (i + 1 < n && p.charAt(i + 1) == '*') {
                pp.add("" + p.charAt(i) + p.charAt(i + 1));
                i += 2;
            } else {
                pp.add("" + p.charAt(i));
                i++;
            }
        }
        int nn = pp.size();
        boolean[][] f = new boolean[m + 1][nn + 1];
        f[0][0] = true;
        int z = 0;
        while (z < nn && pp.get(z).length() == 2) {
            f[0][z + 1] = true;
            z++;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < nn; j++) {
                if (pp.get(j).equals(".*")) {
                    f[i + 1][j + 1] = f[i + 1][j] || f[i][j + 1];
                } else if (pp.get(j).length() == 2) {
                    if (s.charAt(i) == pp.get(j).charAt(0)) {
                        f[i + 1][j + 1] = f[i + 1][j] || f[i][j + 1];
                    } else {
                        f[i + 1][j + 1] = f[i + 1][j];
                    }
                } else if (pp.get(j).equals(".") || pp.get(j).charAt(0) == s.charAt(i)) {
                    f[i + 1][j + 1] = f[i][j];
                }
            }
        }
        return f[m][nn];
    }


    public boolean isMatch_v4(String s, String p) {
        int m = s.length();
        int n = p.length();
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i < n; i++) {
            f[0][i + 1] = pArr[i] == '*' && f[0][i - 1];
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pArr[j] == '*') {
                    if (sArr[i] == pArr[j - 1] || pArr[j - 1] == '.') {
                        f[i + 1][j + 1] = f[i + 1][j - 1] || f[i][j + 1];
                    } else {
                        f[i + 1][j + 1] = f[i + 1][j - 1];
                    }
                } else {
                    if (sArr[i] == pArr[j] || pArr[j] == '.') {
                        f[i + 1][j + 1] = f[i][j];
                    }
//                    else {
//                        f[i + 1][j + 1] = false;
//                    }
                }
            }
        }

        return f[m][n];
    }


}
