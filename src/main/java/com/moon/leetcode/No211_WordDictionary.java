package com.moon.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 211. 添加与搜索单词 - 数据结构设计
 * <p>
 * <p>请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。</p>
 * <p>
 * <p>实现词典类 <code>WordDictionary</code> ：</p>
 *
 * <ul>
 * <li><code>WordDictionary()</code> 初始化词典对象</li>
 * <li><code>void addWord(word)</code> 将 <code>word</code> 添加到数据结构中，之后可以对它进行匹配</li>
 * <li><code>bool search(word)</code> 如果数据结构中存在字符串与 <code>word</code> 匹配，则返回 <code>true</code> ；否则，返回  <code>false</code> 。<code>word</code> 中可能包含一些 <code>'.'</code> ，每个 <code>.</code> 都可以表示任何一个字母。</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * <strong>输出：</strong>
 * [null,null,null,null,false,true,true,true]
 *
 * <strong>解释：</strong>
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= word.length <= 500</code></li>
 * <li><code>addWord</code> 中的 <code>word</code> 由小写英文字母组成</li>
 * <li><code>search</code> 中的 <code>word</code> 由 '.' 或小写英文字母组成</li>
 * <li>最多调用 <code>50000</code> 次 <code>addWord</code> 和 <code>search</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>设计</li><li>字典树</li><li>字符串</li></div></div><br><div><li>👍 343</li><li>👎 0</li></div>
 */
public class No211_WordDictionary {
    private Trie trie;

    public No211_WordDictionary() {
        this.trie = new Trie();
    }

    public static void main(String[] args) {
        No211_WordDictionary d = new No211_WordDictionary();
        d.addWord("abc");
        d.addWord("dad");
        d.addWord("mad");
        System.out.println(d.search("a"));
        System.out.println(d.search("bad"));
        System.out.println(d.search(".ad"));
        System.out.println(d.search("b.."));
    }

    public void addWord(String word) {
        char[] cs = word.toCharArray();
        Trie t = this.trie;
        int n = cs.length;
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            t = t.children.computeIfAbsent(c, o -> new Trie());
        }
        t.isEnd = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, this.trie);
    }

    private boolean dfs(String word, int index, Trie t) {
        if (index == word.length()) {
            return t.isEnd;
        }
        char ch = word.charAt(index);
        if (Character.isLetter(ch)) {
            Trie child = t.children.get(ch);
            return child != null && dfs(word, index + 1, child);
        } else {
            for (Trie child : t.children.values()) {
                if (child != null && dfs(word, index + 1, child)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static class Trie {
        Map<Character, Trie> children;
        private boolean isEnd;

        public Trie() {
            this.children = new HashMap<>();
            this.isEnd = false;
        }
    }
}
