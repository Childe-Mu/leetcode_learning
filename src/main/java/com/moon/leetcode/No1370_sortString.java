package com.moon.leetcode;

/**
 * 1370. 上升下降字符串
 * 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
 * <p>
 * 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
 * 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
 * 重复步骤 2 ，直到你没法从 s 中选择字符。
 * 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
 * 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
 * 重复步骤 5 ，直到你没法从 s 中选择字符。
 * 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
 * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
 * <p>
 * 请你返回将 s 中字符重新排序后的 结果字符串 。
 * <p>
 * 示例 1：
 * 输入：s = "aaaabbbbcccc"
 * 输出："abccbaabccba"
 * 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
 * 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
 * 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
 * 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
 * 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
 * <p>
 * 示例 2：
 * 输入：s = "rat"
 * 输出："art"
 * 解释：单词 "rat" 在上述算法重排序以后变成 "art"
 * <p>
 * 示例 3：
 * 输入：s = "leetcode"
 * 输出："cdelotee"
 * <p>
 * 示例 4：
 * 输入：s = "ggggggg"
 * 输出："ggggggg"
 * <p>
 * 示例 5：
 * 输入：s = "spo"
 * 输出："ops"
 * <p>
 * 提示：
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 */
public class No1370_sortString {
    public static String sortString(String s) {
        int count = 0;
        int[] lettersCount = new int[26];
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            lettersCount[c - 'a'] = ++lettersCount[c - 'a'];
        }
        boolean flag = true;
        while (true) {
            if (flag) {
                for (int i = 0; i < 26; i++) {
                    count = loopUtil(lettersCount, stringBuilder, i, count);
                }
            } else {
                for (int i = 25; i >= 0; i--) {
                    count = loopUtil(lettersCount, stringBuilder, i, count);
                }
            }
            if (count == s.length()) {
                break;
            }
            flag = !flag;
        }
        return stringBuilder.toString();
    }

    private static int loopUtil(int[] lettersCount, StringBuilder stringBuilder, int i, int count) {
        if (lettersCount[i] == 0) {
            return count;
        }
        stringBuilder.append((char) ('a' + i));
        lettersCount[i] = --lettersCount[i];
        return ++count;
    }

    // public String sortString(String s) {
    //     char[] letters = new char[26];
    //     StringBuilder stringBuilder = new StringBuilder();
    //     Map<Character, Integer> letterCount = new HashMap<>();
    //     for (int i = 0; i < s.length(); i++) {
    //         if (letterCount.containsKey(s.charAt(i))) {
    //             int countTemp = letterCount.get(s.charAt(i));
    //             letterCount.put(s.charAt(i), ++countTemp);
    //         } else {
    //             letterCount.put(s.charAt(i), 1);
    //         }
    //         letters[s.charAt(i) - 'a'] = s.charAt(i);
    //     }
    //     boolean flag = true;
    //     while (!letterCount.isEmpty()) {
    //         if (flag) {
    //             for (int i = 0; i < letters.length; i++) {
    //                 loopUtil(letters, stringBuilder, letterCount, i);
    //             }
    //         } else {
    //             for (int i = letters.length - 1; i >= 0; i--) {
    //                 loopUtil(letters, stringBuilder, letterCount, i);
    //             }
    //         }
    //         flag = !flag;
    //     }
    //     return stringBuilder.toString();
    // }
    // private static void loopUtil(char[] letters, StringBuilder result, Map<Character, Integer> letterCount, int i) {
    //     if (letters[i] == 0) {
    //         return;
    //     }
    //     if (letterCount.containsKey(letters[i])) {
    //         result.append(letters[i]);
    //         int countTemp = letterCount.get(letters[i]);
    //         if (countTemp == 1) {
    //             letterCount.remove(letters[i]);
    //             letters[i] = 0;
    //         } else {
    //             letterCount.put(letters[i], --countTemp);
    //         }
    //     }
    // }

    public static void main(String[] args) {
        String s = "aaaabbbbcccc";
        System.out.println(sortString(s));
    }
}
