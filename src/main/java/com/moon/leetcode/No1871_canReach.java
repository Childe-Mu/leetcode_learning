package com.moon.leetcode;

// 1871. 跳跃游戏 VII
//给你一个下标从 0 开始的二进制字符串 s 和两个整数 minJump 和 maxJump 。一开始，你在下标 0 处，且该位置的值一定为 '0' 。当同时
//满足如下条件时，你可以从下标 i 移动到下标 j 处：
//
// i + minJump <= j <= min(i + maxJump, s.length - 1) 且 s[j] == '0'.
//
// 如果你可以到达 s 的下标 s.length - 1 处，请你返回 true ，否则返回 false 。
//
// 示例 1：
//
//输入：s = "011010", minJump = 2, maxJump = 3
//输出：true
//解释：
//第一步，从下标 0 移动到下标 3 。
//第二步，从下标 3 移动到下标 5 。
//
// 示例 2：
//
//输入：s = "01101110", minJump = 2, maxJump = 3
//输出：false
//
// 提示：
//
// 2 <= s.length <= 105
// s[i] 要么是 '0' ，要么是 '1'
// s[0] == '0'
// 1 <= minJump <= maxJump < s.length
//
// Related Topics 贪心算法 广度优先搜索 Line Sweep
// 👍 20 👎 0
public class No1871_canReach {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        char[] chars = s.toCharArray();
        if (chars[n - 1] == '1') {
            return false;
        }
        boolean[] f = new boolean[n];
        f[0] = true;
        int count = 1;
        for (int i = minJump; i < n; i++) {
            if (chars[i] == '0' && count > 0) {
                f[i] = true;
            }
            if (i >= maxJump && f[i - maxJump]) {
                count--;
            }
            if (f[i - minJump + 1]) {
                count++;
            }
        }
        return f[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new No1871_canReach().canReach("011010", 2, 3));
    }
}
