package com.moon.leetcode;

/**
 * 67. 二进制求和<br/>
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。<br/>
 * 输入为 非空 字符串且只包含数字 1 和 0。<br/>
 * <br/>
 * 示例 1:<br/>
 * 输入: a = "11", b = "1"<br/>
 * 输出: "100"<br/>
 * <br/>
 * 示例 2:<br/>
 * 输入: a = "1010", b = "1011"<br/>
 * 输出: "10101"<br/>
 * <br/>
 * 提示：<br/>
 * 每个字符串仅由字符 '0' 或 '1' 组成。<br/>
 * 1 <= a.length, b.length <= 10^4<br/>
 * 字符串如果不是 "0" ，就都不含前导零。<br/>
 */
public class No67_addBinary {
    // /**
    //  * 如果在长整形范围内，此方法最简单
    //  */
    // public static String addBinary(String a, String b) {
    //     return Long.toBinaryString(Long.parseLong(a, 2) + Long.parseLong(b, 2));
    // }

    // /**
    //  * 超出长整形范围
    //  */
    // public static String addBinary(String a, String b) {
    //     if (a.length() < b.length()) {
    //         String temp = a;
    //         a = b;
    //         b = temp;
    //     }
    //     char[] aa = a.toCharArray();
    //     char[] bb = b.toCharArray();
    //     int temp = 0;
    //     StringBuilder sb = new StringBuilder();
    //     for (int i = 1; i <= a.length(); i++) {
    //         int x;
    //         if (i <= b.length()) {
    //             x = temp + bb[b.length() - i] - '0';
    //         } else {
    //             x = temp;
    //         }
    //         int sum = aa[a.length() - i] + x;
    //         if (sum == '0') {
    //             temp = 0;
    //             sb.insert(0, 0);
    //         } else if (sum == '1') {
    //             temp = 0;
    //             sb.insert(0, 1);
    //         } else if (sum == '2') {
    //             temp = 1;
    //             sb.insert(0, 0);
    //         } else {
    //             temp = 1;
    //             sb.insert(0, 1);
    //         }
    //     }
    //     if (temp == 1) {
    //         sb.insert(0, 1);
    //     }
    //     return sb.toString();
    // }

    /**
     * 精简算法,去掉第二个算法中无用且好费时间的赋值转换判断
     */
    public static String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            // 根据奇数偶数+0
            ans.append((char) ((carry & 1) + '0'));
            carry /= 2;
        }
        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("1111", "1111"));
        // StringBuilder sb = new StringBuilder("123");
        // sb.insert(0, "-");
        // sb.insert(0, "-");
        // System.out.println(sb.toString());
    }
}
