package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

// 剑指 Offer 38. 字符串的排列
//
//输入一个字符串，打印出该字符串中字符的所有排列。
//
// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
//
// 示例:
//
// 输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
//
// 限制：
//
// 1 <= s 的长度 <= 8
// Related Topics 回溯算法
// 👍 264 👎 0
public class Offer_38_permutation {
    private List<String> res;
    private boolean[] used;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Offer_38_permutation().permutation("aac")));
    }

    public String[] permutation(String s) {
        Deque<Character> path = new ArrayDeque<>();
        res = new ArrayList<>();
        used = new boolean[s.length()];
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        backtrack(chars, s.length(), 0, path);
        return res.toArray(new String[0]);
    }

    private void backtrack(char[] chars, int n, int depth, Deque<Character> path) {
        if (depth == n) {
            res.add(toStr(path));
            return;
        }
        for (int j = 0; j < n; j++) {
            if (used[j]) {
                continue;
            }
            if (j > 0 && chars[j] == chars[j - 1] && used[j - 1]) {
                continue;
            }
            path.addLast(chars[j]);
            used[j] = true;
            backtrack(chars, n, depth + 1, path);
            used[j] = false;
            path.removeLast();
        }
    }

    private String toStr(Deque<Character> path) {
        StringBuilder sb = new StringBuilder();
        for (Character character : path) {
            sb.append(character);
        }
        return sb.toString();
    }
}
