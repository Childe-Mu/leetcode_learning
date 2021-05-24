package com.moon.leetcode;

// 664. 奇怪的打印机
//
//有台奇怪的打印机有以下两个特殊要求：
//
// 打印机每次只能打印由 同一个字符 组成的序列。
// 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
//
// 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
//
// 示例 1：
//
//输入：s = "aaabbb"
//输出：2
//解释：首先打印 "aaa" 然后打印 "bbb"。
//
// 示例 2：
//
//输入：s = "aba"
//输出：2
//解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
//
// 提示：
//
// 1 <= s.length <= 100
// s 由小写英文字母组成
//
// Related Topics 深度优先搜索 动态规划
// 👍 139 👎 0
public class No664_strangePrinter {
    public static void main(String[] args) {
        System.out.println(new No664_strangePrinter().strangePrinter("aba"));
    }

    public int strangePrinter(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        char[] chars = s.toCharArray();
        for (int j = 0; j < n; j++) {
            f[j][j] = 1;
            for (int i = j - 1; i >= 0; i--) {
                if (chars[i] == chars[j]) {
                    f[i][j] = f[i][j - 1];
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        min = Math.min(min, f[i][k] + f[k + 1][j]);
                    }
                    f[i][j] = min;
                }
            }
        }
        return f[0][n - 1];
    }
}
