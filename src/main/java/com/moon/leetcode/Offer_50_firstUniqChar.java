package com.moon.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 剑指 Offer 50. 第一个只出现一次的字符
//
//在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
//
// 示例:
//
// s = "abaccdeff"
//返回 "b"
//
//s = ""
//返回 " "
//
// 限制：
//
// 0 <= s 的长度 <= 50000
// Related Topics 哈希表
// 👍 85 👎 0
public class Offer_50_firstUniqChar {
    public static void main(String[] args) {
        System.out.println(new Offer_50_firstUniqChar().firstUniqChar_v2("abaccdeff"));
    }

    public char firstUniqChar_v1(String s) {
        if (s.length() == 0) {
            return ' ';
        }
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 1) {
                set.add((char) ('a' + i));
            }
        }
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                return c;
            }
        }
        return ' ';
    }

    public char firstUniqChar_v2(String s) {
        if (s.length() == 0) {
            return ' ';
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : s.toCharArray()) {
            if (map.get(c) == 1) {
                return c;
            }
        }
        return ' ';
    }
}
