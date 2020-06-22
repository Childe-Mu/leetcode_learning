package com.moon.leetcode;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 392. 判断子序列<br/>
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。<br/>
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。<br/>
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
 * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。<br/>
 * <br/>
 * 示例 1:<br/>
 * s = "abc", t = "ahbgdc"<br/>
 * 返回 true.<br/>
 * <br/>
 * 示例 2:<br/>
 * s = "axc", t = "ahbgdc"<br/>
 * 返回 false.<br/>
 * <br/>
 * 后续挑战 :<br/>
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？<br/>
 */
public class No392_isSubsequence {
    // /**
    //  * 贪心算法
    //  */
    // public static boolean isSubsequence(String s, String t) {
    //     if (s == null || s.length() == 0 || t == null || t.length() == 0) {
    //         return false;
    //     }
    //     char[] ss = s.toCharArray();
    //     char[] tt = t.toCharArray();
    //     int i = 0;
    //     for (char c : tt) {
    //         if (ss[i] == c) {
    //             i++;
    //         }
    //     }
    //     return i == s.length();
    // }

    /**
     * 进阶<br/>
     * <br/>
     * 匹配一串字符需要 O(n) ，n 为 t 的长度。如果有大量输入的 S，称作 S1 , S2 , ... , Sk 其中 k >= 10 亿，
     * 你需要依次检查它们是否为 T 的子序列，这时候处理每一个子串都需要扫描一遍 T 是很费时的。<br/>
     * <br/>
     * 在这种情况下，我们需要在匹配前对 T 做预处理，利用一个二维数组记录每个位置的下一个要匹配的字符的位置，
     * 这里的字符是'a' ~ 'z'，所以这个数组的大小是 dp[n][26]，n 为 T 的长度。那么每处理一个子串只需要扫描一遍 Si 即可，
     * 因为在数组的帮助下我们对 T 是“跳跃”扫描的。比如下面匹配 "ada" 的例子，只需要“跳跃”三次。<br/>
     */
    public static boolean isSubsequence(String s, String t) {
        // 预处理
        // 开头加一个空字符作为匹配入口
        t = " " + t;
        int n = t.length();
        // 记录每个位置的下一个ch的位置
        int[][] dp = new int[n][6];
        // 外层循环循环26个字母，每循环一次，则完成一个字母的位置寻找设置，比如第一次完成 'a',
        for (char ch = 0; ch < 6; ch++) {
            int p = -1;
            // 内层循环，遍历整个字符串，循环查找字母 ch 在每个字符后面的位置
            // 从后往前记录dp aebfdc
            for (int i = n - 1; i >= 0; i--) {
                dp[i][ch] = p;
                if (t.charAt(i) == ch + 'a') {
                    p = i;
                }
            }
            System.out.println(ArrayUtils.toString(dp));
        }
        // System.out.println(ArrayUtils.toString(dp));
        // 匹配
        int i = 0;
        // 跳跃遍历
        for (char ch : s.toCharArray()) {
            i = dp[i][ch - 'a'];
            if (i == -1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isSubsequence("abc", "aebfdc"));
    }
}