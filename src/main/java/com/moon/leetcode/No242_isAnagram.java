package com.moon.leetcode;

import java.util.Arrays;

/**
 * 242. 有效的字母异位词
 * <p>
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * <p>
 * 说明:
 * 你可以假设字符串只包含小写字母。
 */
public class No242_isAnagram {

    // public static boolean isAnagram(String s, String t) {
    //     if (s.length() != t.length()) {
    //         return false;
    //     }
    //     char[] ss = s.toCharArray();
    //     char[] tt = t.toCharArray();
    //     Arrays.sort(ss);
    //     Arrays.sort(tt);
    //     // for (int i = 0; i < s.length(); i++) {
    //     //     if (ss[i] != tt[i]) {
    //     //         return false;
    //     //     }
    //     // }
    //     // return true;
    //     return Arrays.equals(ss, tt);
    // }

    // private static boolean isAnagram(String s, String t) {
    //     if (s == null || t == null) {
    //         return false;
    //     }
    //     if (s.length() != t.length()) {
    //         return false;
    //     }
    //     int[] count = new int[26];
    //     for (int i = 0; i < s.length(); i++) {
    //         count[s.charAt(i) - 'a']++;
    //         count[t.charAt(i) - 'a']--;
    //     }
    //     for (int i : count) {
    //         if (i != 0) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    // private static boolean isAnagram(String s, String t) {
    //     if (s == null || t == null) {
    //         return false;
    //     }
    //     if (s.length() != t.length()) {
    //         return false;
    //     }
    //     HashMap<Character, Integer> m = new HashMap<>();
    //     for (int i = 0; i < s.length(); i++) {
    //         if (m.containsKey(s.charAt(i))) {
    //             Integer temp = m.get(s.charAt(i));
    //             m.put(s.charAt(i), ++temp);
    //         } else {
    //             m.put(s.charAt(i), 1);
    //         }
    //         if (m.containsKey(t.charAt(i))) {
    //             Integer temp = m.get(t.charAt(i));
    //             m.put(t.charAt(i), --temp);
    //         } else {
    //             m.put(t.charAt(i), -1);
    //         }
    //     }
    //     for (Integer i : m.values()) {
    //         if (i != 0) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    private static boolean isAnagram(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        if (s.equals(t)) {
            return true;
        }
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        Arrays.sort(ss);
        Arrays.sort(tt);
        return Arrays.hashCode(ss) == Arrays.hashCode(tt);
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        boolean res = isAnagram(s, t);
        System.out.println(res);
    }
}
