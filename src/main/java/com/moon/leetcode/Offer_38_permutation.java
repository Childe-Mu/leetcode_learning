package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
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

    char[] c;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Offer_38_permutation().permutation_v2("abc")));
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

    public String[] permutation_v1(String s) {
        Deque<Character> path = new ArrayDeque<>();
        res = new ArrayList<>();
        used = new boolean[s.length()];
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        backtrack(chars, s.length(), 0, path);
        return res.toArray(new String[0]);
    }

    public String[] permutation_v2(String s) {
        c = s.toCharArray();
        res = new LinkedList<>();
        dfs(0);
        return res.toArray(new String[0]);
    }

    void dfs(int x) {
        if (x == c.length - 1) {
            res.add(String.valueOf(c));      // 添加排列方案
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            char e = c[i];
            if (set.contains(e)) {
                continue; // 重复，因此剪枝
            }
            set.add(e);
            swap(i, x);                      // 交换，将 c[i] 固定在第 x 位
            dfs(x + 1);                      // 开启固定第 x + 1 位字符
            swap(i, x);                      // 恢复交换
        }
    }

    void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }

}
