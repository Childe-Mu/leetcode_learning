package com.moon.leetcode;

// 剑指 Offer 58 - I. 翻转单词顺序
//
//输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，
//则输出"student. a am I"。
//
// 示例 1：
//
// 输入: "the sky is blue"
//输出: "blue is sky the"
//
// 示例 2：
//
// 输入: "  hello world!  "
//输出: "world! hello"
//解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
//
// 示例 3：
//
// 输入: "a good   example"
//输出: "example good a"
//解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
//
// 说明：
//
// 无空格字符构成一个单词。
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
//
// 注意：本题与主站 151 题相同：https://leetcode-cn.com/problems/reverse-words-in-a-string/
//
// 注意：此题对比原题有改动
// Related Topics 字符串
// 👍 88 👎 0
public class Offer_58_1_reverseWords {
    public static void main(String[] args) {
        System.out.println(new Offer_58_1_reverseWords().reverseWords_v2(" "));
    }

    public String reverseWords_v1(String s) {
        String[] tmp = s.split(" ");
        int n = tmp.length;
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            if (tmp[i].equals("")) {
                continue;
            }
            if (i == n - 1) {
                sb.append(tmp[i]);
            } else {
                sb.append(" ").append(tmp[i]);
            }
        }
        return sb.toString();
    }

    public String reverseWords_v2(String s) {
        char[] chars = s.toCharArray();
        int j;
        StringBuilder sb = new StringBuilder();
        for (int i = chars.length - 1; i >= 0; i--) {
            while (i >= 0 && chars[i] == ' ') {
                i--;
            }
            j = i + 1;
            while (i >= 0 && chars[i] != ' ') {
                i--;
            }
            sb.append(s, i + 1, j).append(" ");
        }
        return sb.toString().trim();
    }
}
