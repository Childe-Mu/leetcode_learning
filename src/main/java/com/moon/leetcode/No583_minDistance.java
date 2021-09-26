package com.moon.leetcode;

/**
 * 583. 两个字符串的删除操作
 * <p>给定两个单词&nbsp;<em>word1&nbsp;</em>和&nbsp;<em>word2</em>，找到使得&nbsp;<em>word1&nbsp;</em>和&nbsp;<em>word2&nbsp;</em>相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre><strong>输入:</strong> &quot;sea&quot;, &quot;eat&quot;
 * <strong>输出:</strong> 2
 * <strong>解释:</strong> 第一步将&quot;sea&quot;变为&quot;ea&quot;，第二步将&quot;eat&quot;变为&quot;ea&quot;
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ol>
 * <li>给定单词的长度不超过500。</li>
 * <li>给定单词中的字符只含有小写字母。</li>
 * </ol>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 312</li><li>👎 0</li></div>
 */
public class No583_minDistance {
    public int minDistance_v1(String w1, String w2) {
        char[] cs1 = w1.toCharArray();
        char[] cs2 = w2.toCharArray();
        int m = cs1.length;
        int n = cs2.length;
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cs1[i] == cs2[j]) {
                    f[i + 1][j + 1] = f[i][j] + 1;
                } else {
                    f[i + 1][j + 1] = Math.max(f[i][j + 1], f[i + 1][j]);
                }
            }
        }
        return m + n - f[m][n] * 2;
    }

    public int minDistance_v2(String w1, String w2) {
        char[] cs1 = w1.toCharArray();
        char[] cs2 = w2.toCharArray();
        int m = cs1.length;
        int n = cs2.length;
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            f[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            f[0][j] = j;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cs1[i] == cs2[j]) {
                    f[i + 1][j + 1] = f[i][j];
                } else {
                    f[i + 1][j + 1] = Math.min(f[i][j + 1] + 1, f[i + 1][j] + 1);
                }
            }
        }
        return f[m][n];
    }
}
