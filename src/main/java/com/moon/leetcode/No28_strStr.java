package com.moon.leetcode;

/**
 * 28. 实现 strStr()
 * <p>
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * <p>
 * 输出: 2
 * <p>
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * <p>
 * 输出: -1
 * <p>
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */
public class No28_strStr {
    /**
     * 使用内置函数，但是没有考察算法的意义（可以参考内置函数的实现）
     */
//    public static int strStr(String haystack, String needle) {
//        return haystack.indexOf(needle);
//    }

    /**
     * 子串逐一比较 - 线性时间复杂度
     */
//    public static int strStr(String haystack, String needle) {
//        if (haystack.equals(needle)) {
//            return 0;
//        }
//        int l = needle.length();
//        for (int i = 0; i < haystack.length() - l + 1; i++) {
//            if (haystack.substring(i, i + l).equals(needle)) {
//                return i;
//            }
//        }
//        return -1;
//    }

    /**
     * 双指针 - 线性时间复杂度
     */
//    public static int strStr(String haystack, String needle) {
//        int L = needle.length(), n = haystack.length();
//        if (L == 0) return 0;
//
//        int pn = 0;
//        while (pn < n - L + 1) {
//            // 1.先找到首字母匹配的位置
//            while (pn < n - L + 1 && haystack.charAt(pn) != needle.charAt(0)) ++pn;
//            // 2.然后依次比较后续字母，考虑到可能只匹配了前面部分字母，haystack就到结尾了，所以记录匹配的长度currLen
//            int currLen = 0, pL = 0;
//            while (pL < L && pn < n && haystack.charAt(pn) == needle.charAt(pL)) {
//                ++pn;
//                ++pL;
//                ++currLen;
//            }
//            // 3.如果匹配的长度等于needle的长度，说明匹配完全，返回第一步首字母的位置
//            if (currLen == L) return pn - L;
//            // 4.否则，就返回到第一步首字母的位置往后一位的位置，然后继续寻找首字母匹配的位置
//            pn = pn - currLen + 1;
//        }
//        return -1;
//    }

    /**
     * 双指针-优化 - 线性时间复杂度
     */
    public static int strStr_v4(String haystack, String needle) {
        int m = haystack.length(), n = needle.length();
        if (n == 0) return 0;
        for (int i = 0; i <= m - n; i++) {
            int l = 0;
            for (int j = 0; j < n; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) break;
                l++;
            }
            if (l == n) {
                return i;
            }
        }
        return -1;
    }

    public static int strStr_v5(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) {
            return 0;
        }
        int[] pi = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr_v4("mississippi", "ppi"));
    }
}
