package com.moon.leetcode;

import java.util.HashSet;
import java.util.Set;

// 345. 反转字符串中的元音字母
//
//给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
//
// 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
//
//
//
// 示例 1：
//
//
//输入：s = "hello"
//输出："holle"
//
//
// 示例 2：
//
//
//输入：s = "leetcode"
//输出："leotcede"
//
//
//
// 提示：
//
//
// 1 <= s.length <= 3 * 105
// s 由 可打印的 ASCII 字符组成
//
// Related Topics 双指针 字符串
// 👍 206 👎 0
public class No345_reverseVowels {
    public static void main(String[] args) {
        System.out.println(new No345_reverseVowels().reverseVowels("leetcode"));
    }

    public String reverseVowels(String s) {
        Set<Character> set = new HashSet<>();
        char[] cs = new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        for (char c : cs) {
            set.add(c);
        }
        char[] chars = s.toCharArray();
        int n = chars.length;
        int l = 0, r = n - 1;
        while (l < r) {
            while (l < r && !set.contains(chars[l])) {
                l++;
            }
            while (l < r && !set.contains(chars[r])) {
                r--;
            }
            char t = chars[l];
            chars[l] = chars[r];
            chars[r] = t;
            l++;
            r--;
        }
        return new String(chars);
    }
}
