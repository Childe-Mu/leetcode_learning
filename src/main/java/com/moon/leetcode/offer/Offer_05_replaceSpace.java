package com.moon.leetcode.offer;

// 剑指 Offer 05. 替换空格
//
//请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
//
// 示例 1：
//
// 输入：s = "We are happy."
//输出："We%20are%20happy."
//
// 限制：
//
// 0 <= s 的长度 <= 10000
// 👍 94 👎 0
public class Offer_05_replaceSpace {
    public static void main(String[] args) {
        char[] value = new char[3];
        value[0] = 'a';
        value[1] = 0;
        System.out.println(new String(value));
    }

    public String replaceSpace_v1(String s) {
        char[] chars = new char[s.length() * 3];
        int i = 0;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                chars[i++] = '%';
                chars[i++] = '2';
                chars[i++] = 'd';
            } else {
                chars[i++] = c;
            }
        }
        return new String(chars, 0, i);
    }

    public String replaceSpace_v3(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
