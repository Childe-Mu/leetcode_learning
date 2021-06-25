package com.moon.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 剑指 Offer 48. 最长不含重复字符的子字符串
//
//请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
//
// 示例 1:
//
// 输入: "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
// 示例 2:
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
// 示例 3:
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
// 提示：
//
// s.length <= 40000
// 注意：本题与主站 3 题相同：https://leetcode-cn.com/problems/longest-substring-without-rep
//eating-characters/
// Related Topics 哈希表 双指针 滑动窗口
// 👍 224 👎 0
public class Offer_48_lengthOfLongestSubstring {
    public static void main(String[] args) {
        System.out.println(new Offer_48_lengthOfLongestSubstring().lengthOfLongestSubstring_v2("abcabcbb"));
    }

    public int lengthOfLongestSubstring_v1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        int l = 0;
        int r = 0;
        int ans = 0;
        while (r < n) {
            char c = s.charAt(r);
            Integer i = map.put(c, r);
            if (i != null && l <= i) {
                ans = Math.max(ans, r - l);
                l = i + 1;
            }
            r++;
        }
        return Math.max(ans, r - l);
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

    public int lengthOfLongestSubstring_v3(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int i = -1, res = 0;
        for (int j = 0; j < s.length(); j++) {
            if (dic.containsKey(s.charAt(j))) {
                i = Math.max(i, dic.get(s.charAt(j))); // 更新左指针 i
            }
            dic.put(s.charAt(j), j); // 哈希表记录
            res = Math.max(res, j - i); // 更新结果
        }
        return res;
    }
}
