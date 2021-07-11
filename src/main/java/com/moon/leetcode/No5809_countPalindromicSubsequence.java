package com.moon.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 5809. 长度为 3 的不同回文子序列
//
//给你一个字符串 s ，返回 s 中 长度为 3 的不同回文子序列 的个数。
//
// 即便存在多种方法来构建相同的子序列，但相同的子序列只计数一次。
//
// 回文 是正着读和反着读一样的字符串。
//
// 子序列 是由原字符串删除其中部分字符（也可以不删除）且不改变剩余字符之间相对顺序形成的一个新字符串。
//
//
// 例如，"ace" 是 "abcde" 的一个子序列。
//
//
//
//
// 示例 1：
//
//
//输入：s = "aabca"
//输出：3
//解释：长度为 3 的 3 个回文子序列分别是：
//- "aba" ("aabca" 的子序列)
//- "aaa" ("aabca" 的子序列)
//- "aca" ("aabca" 的子序列)
//
//
// 示例 2：
//
//
//输入：s = "adc"
//输出：0
//解释："adc" 不存在长度为 3 的回文子序列。
//
//
// 示例 3：
//
//
//输入：s = "bbcbaba"
//输出：4
//解释：长度为 3 的 4 个回文子序列分别是：
//- "bbb" ("bbcbaba" 的子序列)
//- "bcb" ("bbcbaba" 的子序列)
//- "bab" ("bbcbaba" 的子序列)
//- "aba" ("bbcbaba" 的子序列)
//
//
//
//
// 提示：
//
//
// 3 <= s.length <= 105
// s 仅由小写英文字母组成
//
// 👍 3 👎 0
public class No5809_countPalindromicSubsequence {
    public static void main(String[] args) {
        System.out.println(new No5809_countPalindromicSubsequence().countPalindromicSubsequence_v2("bbcbaba"));
    }

    public int countPalindromicSubsequence_v1(String s) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        char[] cs = s.toCharArray();
        int n = cs.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.computeIfAbsent(cs[i] - 'a', o -> new ArrayList<>());
            list.add(i);
        }
        for (int i = 0; i < 26; i++) {
            if (!map.containsKey(i)) {
                continue;
            }
            List<Integer> indexs1 = map.get(i);
            if (indexs1.size() < 2) {
                continue;
            }
            int one = indexs1.get(0);
            for (int j = 0; j < 26; j++) {
                if (!map.containsKey(j)) {
                    continue;
                }
                List<Integer> indexs2 = map.get(j);
                int two = Integer.MAX_VALUE;
                for (Integer index : indexs2) {
                    if (index > one) {
                        two = index;
                        break;
                    }
                }
                Integer three = indexs1.get(indexs1.size() - 1);
                if (three > two) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        int res = 0;
        // 枚举两侧字符
        for (char c = 'a'; c <= 'z'; ++c) {
            int l = 0, r = n - 1;
            // 寻找该字符第一次出现的下标
            while (l < n && cs[l] != c) {
                ++l;
            }
            // 寻找该字符最后一次出现的下标
            while (r >= 0 && cs[r] != c) {
                --r;
            }
            if (r - l < 2) {
                // 该字符未出现，或两下标中间的子串不存在
                continue;
            }
            // 利用哈希集合统计 s[l+1..r-1] 子串的字符总数，并更新答案
            Set<Character> charset = new HashSet<>();
            for (int k = l + 1; k < r; ++k) {
                charset.add(cs[k]);
            }
            res += charset.size();
        }
        return res;
    }

    public int countPalindromicSubsequence_v2(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        int res = 0;
        // 前缀/后缀字符状态数组
        int[] pre = new int[n];
        int[] suf = new int[n];
        for (int i = 0; i < n; ++i) {
            // 前缀 s[0..i-1] 包含的字符种类
            pre[i] = (i > 0 ? pre[i - 1] : 0) | (1 << (cs[i] - 'a'));
        }
        for (int i = n - 1; i >= 0; --i) {
            // 后缀 s[i+1..n-1] 包含的字符种类
            suf[i] = (i != n - 1 ? suf[i + 1] : 0) | (1 << (cs[i] - 'a'));
        }
        // 每种中间字符的回文子序列状态数组
        int[] ans = new int[26];
        for (int i = 1; i < n - 1; ++i) {
            ans[cs[i] - 'a'] |= (pre[i - 1] & suf[i + 1]);
        }
        // 更新答案
        for (int i = 0; i < 26; ++i) {
            res += Integer.bitCount(ans[i]);
        }
        return res;
    }
}
