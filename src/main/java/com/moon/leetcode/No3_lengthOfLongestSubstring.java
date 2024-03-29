package com.moon.leetcode;

import java.util.HashSet;
import java.util.Set;

// 3. 无重复字符的最长子串
//
//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
// 示例 1:
//
//输入: s = "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
// 示例 2:
//
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
// 示例 3:
//
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
// 示例 4:
//
//输入: s = ""
//输出: 0
//
// 提示：
//
// 0 <= s.length <= 5 * 104
// s 由英文字母、数字、符号和空格组成
//
// Related Topics 哈希表 双指针 字符串 Sliding Window
// 👍 5382 👎 0
public class No3_lengthOfLongestSubstring {
    public static void main(String[] args) {
        System.out.println(new No3_lengthOfLongestSubstring().lengthOfLongestSubstring_v1("auga"));
    }

    public int lengthOfLongestSubstring_v1(String s) {
        int n = s.length();
        if (n <= 1) {
            return n;
        }
        Set<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();
        int l = 0, r = 0, max = 1;
        while (r < n) {
            if (set.contains(chars[r])) {
                while (l < r && chars[l] != chars[r]) {
                    set.remove(chars[l]);
                    l++;
                }
                set.remove(chars[l]);
                l++;
            } else {
                set.add(chars[r]);
                r++;
            }
            max = Math.max(max, r - l);
        }
        return max;
    }

    public int lengthOfLongestSubstring_v2(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}
