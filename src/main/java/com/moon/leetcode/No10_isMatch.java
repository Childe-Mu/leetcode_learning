package com.moon.leetcode;

import java.util.ArrayList;
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
        System.out.println(new No10_isMatch().isMatch("aaa", "a"));
    }

    public boolean isMatch(String s, String p) {
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
        for (int i = 0; i < m; i++) {
            f[i][0] = true;
        }
        for (int i = 0; i < m; ) {
            for (int j = 0; j < nn; ) {
                if (pp.get(j).equals(".") || pp.get(j).charAt(0) == s.charAt(i)) {
                    f[i + 1][j + 1] = f[i][j];
                    i++;
                    j++;
                } else if (pp.get(j).equals(".*")) {
                    f[i + 1][j + 1] = f[i + 1][j++] || f[i++][j + 1];
                } else if (pp.get(j).charAt(1) == '*') {
                    if (s.charAt(i) == pp.get(j).charAt(0)) {
                        f[i + 1][j + 1] = f[i + 1][j++] || f[i][j + 1];
                    } else {
                        f[i + 1][j + 1] = f[i + 1][j];
                    }
                }
            }
        }
        return f[m][nn];
    }
}
