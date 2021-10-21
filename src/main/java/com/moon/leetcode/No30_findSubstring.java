package com.moon.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 30. 串联所有单词的子串
 * <p>
 * <p>给定一个字符串 <code>s</code><strong> </strong>和一些 <strong>长度相同</strong> 的单词 <code>words</code><strong> 。</strong>找出 <code>s</code><strong> </strong>中恰好可以由 <code>words</code><strong> </strong>中所有单词串联形成的子串的起始位置。</p>
 *
 * <p>注意子串要与 <code>words</code><strong> </strong>中的单词完全匹配，<strong>中间不能有其他字符 </strong>，但不需要考虑 <code>words</code><strong> </strong>中单词串联的顺序。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "barfoothefoobarman", words = ["foo","bar"]
 * <strong>输出：</strong><code>[0,9]</code>
 * <strong>解释：</strong>
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * <code><strong>输出：</strong>[]</code>
 * </pre>
 * <p>
 * p><strong>示例 3：</strong></p>
 * <p>
 * pre>
 * <strong>输入：</strong>s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * <strong>输出：</strong>[6,9,12]
 * </pre>
 * <p>
 * p> </p>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 10<sup>4</sup></code></li>
 * <li><code>s</code> 由小写英文字母组成</li>
 * <li><code>1 <= words.length <= 5000</code></li>
 * <li><code>1 <= words[i].length <= 30</code></li>
 * <li><code>words[i]</code> 由小写英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 557</li><li>👎 0</li></div>
 */
public class No30_findSubstring {
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> wordsMap = new HashMap<>();
        // 单词表
        for (String word : words) {
            Integer num = wordsMap.getOrDefault(word, 0) + 1;
            wordsMap.put(word, num);
        }
        // 单词表个数
        int size = words.length;
        // 每个单词长度
        int wordLen = words[0].length();
        // 单词表总长度, s至少要这么长才有可能是
        int total = wordLen * size;
        List<Integer> ans = new ArrayList<>();
        // 小于就不可能是
        if (s.length() < total) {
            // 这里不能返回null ... 坑1
            return ans;
        }
        char[] str = s.toCharArray();
        int strLen = s.length();
        // i + total <= strLen 限制最后的起始位置,不足量的不看了
        for (int i = 0; i < strLen && i + total <= strLen; i++) {
            // 每一个起始点开始,wordLen的字符串
            String part = new String(str, i, wordLen);
            // 在单词表内的数量
            Integer num = wordsMap.getOrDefault(part, 0);
            // 说明从i开始的长度wordLen字符串,是目标中一个
            if (num != 0) {
                // 看看从i开始total长的字符串是不是都是
                boolean has = this.f(str, i, new HashMap<>(wordsMap), wordLen, total);
                if (has) {
                    ans.add(i);
                }
            }
        }
        return ans;
    }

    private boolean f(char[] str, int i, HashMap<String, Integer> hashMap, int wordLen, int total) {
        // 长度不够
        if (str.length - i + 1 < total) {
            return false;
        }
        for (int j = i; j < i + total && j + wordLen <= i + total; j = j + wordLen) {
            String part = new String(str, j, wordLen);
            Integer num = hashMap.getOrDefault(part, 0);
            // 单词表中没有了,这段肯定不是
            if (num == 0) {
                return false;
            } else {
                hashMap.put(part, num - 1);
            }
        }
        return true;
    }
}
