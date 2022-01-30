package com.moon.leetcode;

import java.util.*;

/**
 * 884.两句话中的不常见单词
 * <p><strong>句子</strong> 是一串由空格分隔的单词。每个 <strong>单词</strong><em> </em>仅由小写字母组成。</p>
 *
 * <p>如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 <strong>没有出现</strong> ，那么这个单词就是 <strong>不常见的</strong><em> </em>。</p>
 *
 * <p>给你两个 <strong>句子</strong> <code>s1</code> 和 <code>s2</code> ，返回所有 <strong>不常用单词</strong> 的列表。返回列表中单词可以按 <strong>任意顺序</strong> 组织。</p>
 *
 * <p>&nbsp;</p>
 *
 * <ol>
 * </ol>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s1 = "this apple is sweet", s2 = "this apple is sour"
 * <strong>输出：</strong>["sweet","sour"]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s1 = "apple apple", s2 = "banana"
 * <strong>输出：</strong>["banana"]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s1.length, s2.length &lt;= 200</code></li>
 * <li><code>s1</code> 和 <code>s2</code> 由小写英文字母和空格组成</li>
 * <li><code>s1</code> 和 <code>s2</code> 都不含前导或尾随空格</li>
 * <li><code>s1</code> 和 <code>s2</code> 中的所有单词间均由单个空格分隔</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 146</li><li>👎 0</li></div>
 */

public class No884_uncommonFromSentences {
    public String[] uncommonFromSentences_v1(String s1, String s2) {
        String[] a = s1.split(" ");
        String[] b = s2.split(" ");
        Map<String, Integer> m1 = new HashMap<>();
        Map<String, Integer> m2 = new HashMap<>();
        for (String s : a) {
            m1.put(s, m1.getOrDefault(s, 0) + 1);
        }
        for (String s : b) {
            m2.put(s, m2.getOrDefault(s, 0) + 1);
        }
        Set<String> set = new HashSet<>(m1.keySet());
        for (String s : m2.keySet()) {
            m1.remove(s);
        }
        for (String s : set) {
            m2.remove(s);
        }
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : m1.entrySet()) {
            if (entry.getValue() == 1) {
                res.add(entry.getKey());
            }
        }
        for (Map.Entry<String, Integer> entry : m2.entrySet()) {
            if (entry.getValue() == 1) {
                res.add(entry.getKey());
            }
        }
        return res.toArray(new String[0]);
    }

    public String[] uncommonFromSentences_v2(String s1, String s2) {
        Map<String, Integer> m1 = new HashMap<>();
        insert(s1, m1);
        insert(s2, m1);
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : m1.entrySet()) {
            if (entry.getValue() == 1) {
                res.add(entry.getKey());
            }
        }
        return res.toArray(new String[0]);
    }

    private void insert(String s, Map<String, Integer> m1) {
        String[] a = s.split(" ");
        for (String ss : a) {
            m1.put(ss, m1.getOrDefault(ss, 0) + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new No884_uncommonFromSentences().uncommonFromSentences_v2("this apple is sweet", "this apple is sour")));
    }
}
