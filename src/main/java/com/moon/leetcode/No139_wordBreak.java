package com.moon.leetcode;

import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 139. 单词拆分
//
//给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
//
// 说明：
//
// 拆分时可以重复使用字典中的单词。
// 你可以假设字典中没有重复的单词。
//
// 示例 1：
//
// 输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
//
// 示例 2：
//
// 输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
//   注意你可以重复使用字典中的单词。
//
// 示例 3：
//
// 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
//
// Related Topics 动态规划
// 👍 925 👎 0
public class No139_wordBreak {
    public boolean wordBreak_v1(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        return traverse(s, set);
    }

    private boolean traverse(String s, Set<String> set) {
        if (s.length() == 0) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.substring(0, i + 1))) {
                if (traverse(s.substring(i + 1), set)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean wordBreak_v2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && set.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }

    public static void main(String[] args) {
        System.out.println(new No139_wordBreak().wordBreak_v1("catsanddog", Lists.newArrayList("cats", "dog", "sand", "and", "cat")));
    }

    public boolean wordBreak_v3(String s, List<String> wordDict) {
        int n = s.length(), maxLen = 0;
        Set<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
            maxLen = Math.max(maxLen, str.length());
        }
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0 && j >= i - maxLen; j--) {
                if (f[j] && set.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }
}
