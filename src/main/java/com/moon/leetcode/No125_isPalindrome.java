package com.moon.leetcode;

/**
 * 125. 验证回文串
 * <p>
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * <p>
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 */
public class No125_isPalindrome {
    public static boolean isPalindrome_v1(String s) {

        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    public static boolean isPalindrome_v2(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            if (!('a' <= cs[l] && cs[l] <= 'z') && !('A' <= cs[l] && cs[l] <= 'Z') && !('0' <= cs[l] && cs[l] <= '9')) {
                l++;
                continue;
            }
            if (!('a' <= cs[r] && cs[r] <= 'z') && !('A' <= cs[r] && cs[r] <= 'Z') && !('0' <= cs[r] && cs[r] <= '9')) {
                r--;
                continue;
            }
            int d1 = getDiff(cs[l]);
            int d2 = getDiff(cs[r]);

            if (d1 != d2) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    private static int getDiff(char c) {
        if ('a' <= c && c <= 'z') {
            return c - 'a';
        }
        if ('A' <= c && c <= 'Z') {
            return c - 'A';
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome_v1("A man, a plan, a canal: Panama"));
    }
}
