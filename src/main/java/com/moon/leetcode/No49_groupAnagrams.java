package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 49. 字母异位词分组
 * <p>
 * /**
 * <p>给你一个字符串数组，请你将 <strong>字母异位词</strong> 组合在一起。可以按任意顺序返回结果列表。</p>
 *
 * <p><strong>字母异位词</strong> 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> strs = <code>["eat", "tea", "tan", "ate", "nat", "bat"]</code>
 * <strong>输出: </strong>[["bat"],["nat","tan"],["ate","eat","tea"]]</pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> strs = <code>[""]</code>
 * <strong>输出: </strong>[[""]]
 * </pre>
 * <p>
 * p><strong>示例 3:</strong></p>
 * <p>
 * pre>
 * <strong>输入:</strong> strs = <code>["a"]</code>
 * <strong>输出: </strong>[["a"]]</pre>
 * <p>
 * p>&nbsp;</p>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= strs.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>0 &lt;= strs[i].length &lt;= 100</code></li>
 * <li><code>strs[i]</code>&nbsp;仅包含小写字母</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>排序</li></div></div><br><div><li>👍 823</li><li>👎 0</li></div>
 */
public class No49_groupAnagrams {
    public List<List<String>> groupAnagrams_v1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] cnt = new int[26];
            for (char c : str.toCharArray()) {
                cnt[c - 'a']++;
            }
            StringBuilder builder = new StringBuilder();
            for (int i : cnt) {
                builder.append(i).append("-");
            }
            map.computeIfAbsent(builder.toString(), o -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams_v2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] cs = str.toCharArray();
            Arrays.sort(cs);
            map.computeIfAbsent(new String(cs), o -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
