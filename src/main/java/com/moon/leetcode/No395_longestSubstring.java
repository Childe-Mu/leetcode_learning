package com.moon.leetcode;

/**
 * 395. 至少有K个重复字符的最长子串
 * <p>
 * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * s = "aaabb", k = 3
 * <p>
 * 输出:
 * 3
 * <p>
 * 最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * <p>
 * 示例 2:
 * <p>
 * 输入:
 * s = "ababbc", k = 2
 * <p>
 * 输出:
 * 5
 * <p>
 * 最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 */
public class No395_longestSubstring {

    /**
     * 分治，递归
     */
    public static int longestSubstring_v1(String s, int k) {
        int n = s.length();
//        return dfs(s, 0, n - 1, k);
        return dfs1(s, k);
    }

    public static int dfs(String s, int left, int right, int k) {
        int[] cnt = new int[26];
        for (int i = left; i <= right; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        if (split == 0) {
            return right - left + 1;
        }

        int i = left;
        int ret = 0;
        while (i <= right) {
            while (i <= right && s.charAt(i) == split) {
                i++;
            }
            if (i > right) {
                break;
            }
            int start = i;
            while (i <= right && s.charAt(i) != split) {
                i++;
            }

            int length = dfs(s, start, i - 1, k);
            ret = Math.max(ret, length);
        }
        return ret;
    }

    public static int dfs1(String s, int k) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        if (split == 0) {
            return s.length();
        }

        int left = 0, len = s.length() - 1;
        int ret = 0;
        while (left <= len) {
            while (left <= len && s.charAt(left) == split) {
                left++;
            }
            if (left > len) {
                break;
            }
            int right = left;
            while (right <= len && s.charAt(right) != split) {
                right++;
            }

            int length = dfs1(s.substring(left, right), k);
            left = right;
            ret = Math.max(ret, length);
        }
        return ret;
    }

    /**
     * 滑动窗口
     */
    public static int longestSubstring_v2(String s, int k) {
        int n = s.length();
        int result = 0;
        for (int i = 1; i <= 26; i++) {
            int left = 0, right = 0, less = 0, total = 0;
            int[] count = new int[26];
            while (right < n) {
                int temp = ++count[s.charAt(right) - 'a'];
                if (temp == 1) {
                    less++;
                    total++;
                }
                if (temp == k) {
                    less--;
                }
                while (total > i) {
                    temp = --count[s.charAt(left) - 'a'];
                    if (temp == 0) {
                        less--;
                        total--;
                    }
                    if (temp == k - 1) {
                        less++;
                    }
                    left++;
                }
                if (less == 0) {
                    result = Math.max(result, right - left + 1);
                }
                right++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(longestSubstring_v2("aacbbb", 2));
    }
}
