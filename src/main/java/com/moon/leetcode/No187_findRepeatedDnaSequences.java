package com.moon.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 187. 重复的DNA序列
 * <p>
 * /**
 * <p>所有 DNA 都由一系列缩写为 <code>'A'</code>，<code>'C'</code>，<code>'G'</code> 和 <code>'T'</code> 的核苷酸组成，例如：<code>"ACGAATTCCG"</code>。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。</p>
 *
 * <p>编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 <code>s</code> 中出现次数超过一次。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * <strong>输出：</strong>["AAAAACCCCC","CCCCCAAAAA"]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <p>
 * pre>
 * <strong>输入：</strong>s = "AAAAAAAAAAAAA"
 * <strong>输出：</strong>["AAAAAAAAAA"]
 * </pre>
 * <p>
 * p> </p>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= s.length <= 10<sup>5</sup></code></li>
 * <li><code>s[i]</code> 为 <code>'A'</code>、<code>'C'</code>、<code>'G'</code> 或 <code>'T'</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>位运算</li><li>哈希表</li><li>字符串</li><li>滑动窗口</li><li>哈希函数</li><li>滚动哈希</li></div></div><br><div><li>👍 235</li><li>👎 0</li></div>
 */
public class No187_findRepeatedDnaSequences {
    static final int L = 10;
    static Map<Character, Integer> bin = new HashMap<Character, Integer>() {{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};

    public static void main(String[] args) {
        System.out.println(new No187_findRepeatedDnaSequences().findRepeatedDnaSequences_v2("AAAAAAAAAAA"));
    }

    public List<String> findRepeatedDnaSequences_v1(String s) {
        int n = s.length();
        if (n < 10) {
            return new ArrayList<>();
        }
        char[] cs = s.toCharArray();
        int[] convert = new int[26];
        convert[0] = 1;
        convert['C' - 'A'] = 2;
        convert['G' - 'A'] = 3;
        convert['T' - 'A'] = 4;
        Set<String> res = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= n - 10; i++) {
            int t = 0;
            for (int j = 0; j < 10; j++) {
                t = t * 10 + convert[cs[i + j] - 'A'];
            }
            if (set.contains(t)) {
                res.add(new String(cs, i, 10));
            } else {
                set.add(t);
            }
        }
        return new ArrayList<>(res);
    }

    public List<String> findRepeatedDnaSequences_v2(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        if (n <= L) {
            return ans;
        }
        int x = 0;
        for (int i = 0; i < L - 1; ++i) {
            x = (x << 2) | bin.get(s.charAt(i));
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i <= n - L; ++i) {
            x = ((x << 2) | bin.get(s.charAt(i + L - 1))) & ((1 << (L * 2)) - 1);
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            if (cnt.get(x) == 2) {
                ans.add(s.substring(i, i + L));
            }
        }
        return ans;
    }
}
