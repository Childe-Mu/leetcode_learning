package com.moon.leetcode;

// 1869. 哪种连续子字符串更长
//
//给你一个二进制字符串 s 。如果字符串中由 1 组成的 最长 连续子字符串 严格长于 由 0 组成的 最长 连续子字符串，返回 true ；否则，返回 fa
//lse 。
//
//
// 例如，s = "110100010" 中，由 1 组成的最长连续子字符串的长度是 2 ，由 0 组成的最长连续子字符串的长度是 3 。
//
//
// 注意，如果字符串中不存在 0 ，此时认为由 0 组成的最长连续子字符串的长度是 0 。字符串中不存在 1 的情况也适用此规则。
//
//
//
// 示例 1：
//
//
//输入：s = "1101"
//输出：true
//解释：
//由 1 组成的最长连续子字符串的长度是 2："1101"
//由 0 组成的最长连续子字符串的长度是 1："1101"
//由 1 组成的子字符串更长，故返回 true 。
//
//
// 示例 2：
//
//
//输入：s = "111000"
//输出：false
//解释：
//由 1 组成的最长连续子字符串的长度是 3："111000"
//由 0 组成的最长连续子字符串的长度是 3："111000"
//由 1 组成的子字符串不比由 0 组成的子字符串长，故返回 false 。
//
//
// 示例 3：
//
//
//输入：s = "110100010"
//输出：false
//解释：
//由 1 组成的最长连续子字符串的长度是 2："110100010"
//由 0 组成的最长连续子字符串的长度是 3："110100010"
//由 1 组成的子字符串不比由 0 组成的子字符串长，故返回 false 。
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 100
// s[i] 不是 '0' 就是 '1'
//
// Related Topics 数组 双指针
// 👍 8 👎 0
public class No1869_checkZeroOnes {
    public boolean checkZeroOnes_v1(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        if (n == 1) {
            return chars[0] == '1';
        }
        int oneMax = 0;
        int zeroMax = 0;
        int i = 0;
        while (i < n) {
            int t1 = 0;
            if (chars[i] == '1') {
                i++;
                while (i < n && chars[i] == chars[i - 1]) {
                    i++;
                    t1++;
                }
                oneMax = Math.max(oneMax, t1 + 1);
            }
            i++;
        }
        i = 0;
        while (i < n) {
            int t1 = 0;
            if (chars[i] == '0') {
                i++;
                while (i < n && chars[i] == chars[i - 1]) {
                    i++;
                    t1++;
                }
                zeroMax = Math.max(zeroMax, t1 + 1);
            }
            i++;
        }
        return oneMax > zeroMax;
    }

    public boolean checkZeroOnes_v2(String s) {
        int mx0 = 0;
        int mx1 = 0;
        char prev = '#';   // 上个字符
        int cnt = 0;
        for (char ch : s.toCharArray()) {
            // 当前字符与上个字符相等
            if (ch == prev) {
                ++cnt;
            }
            // 当前字符与上个字符不相等
            else {
                if (prev == '0') {
                    mx0 = Math.max(mx0, cnt);
                } else if (prev == '1') {
                    mx1 = Math.max(mx1, cnt);
                }
                cnt = 1;
            }
            prev = ch;
        }
        // 字符串结尾的连续子串
        if (prev == '0') {
            mx0 = Math.max(mx0, cnt);
        } else if (prev == '1') {
            mx1 = Math.max(mx1, cnt);
        }
        return mx1 > mx0;
    }

}
