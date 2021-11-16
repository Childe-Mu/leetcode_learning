package com.moon.leetcode;

import java.util.HashMap;
import java.util.Map;

// 677. 键值映射
//实现一个 MapSum 类，支持两个方法，insert 和 sum：
//
//
// MapSum() 初始化 MapSum 对象
// void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 ke
//y 已经存在，那么原来的键值对将被替代成新的键值对。
// int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
//
//
//
//
// 示例：
//
//
//输入：
//["MapSum", "insert", "sum", "insert", "sum"]
//[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
//输出：
//[null, null, 3, null, 5]
//
//解释：
//MapSum mapSum = new MapSum();
//mapSum.insert("apple", 3);
//mapSum.sum("ap");           // return 3 (apple = 3)
//mapSum.insert("app", 2);
//mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
//
//
//
//
// 提示：
//
//
// 1 <= key.length, prefix.length <= 50
// key 和 prefix 仅由小写英文字母组成
// 1 <= val <= 1000
// 最多调用 50 次 insert 和 sum
//
// Related Topics 设计 字典树 哈希表 字符串
// 👍 179 👎 0
public class No677_MapSum {
    static class MapSum_v1 {
        TrieNode root;

        public MapSum_v1() {
            root = new TrieNode();
        }

        public void insert(String key, int val) {
            char[] cs = key.toCharArray();
            TrieNode t = root;
            for (char c : cs) {
                if (t.children[c - 'a'] == null) {
                    t.children[c - 'a'] = new TrieNode();
                }
                t = t.children[c - 'a'];
            }
            t.v = val;
        }

        public int sum(String prefix) {
            char[] cs = prefix.toCharArray();
            TrieNode t = root;
            for (char c : cs) {
                TrieNode cur = t.children[c - 'a'];
                if (cur == null) {
                    return 0;
                }
                t = cur;
            }
            return dfs(t);
        }

        public int dfs(TrieNode node) {
            if (node == null) {
                return 0;
            }
            int sum = node.v;
            for (TrieNode t : node.children) {
                if (t == null) {
                    continue;
                }
                sum += dfs(t);
            }
            return sum;
        }

        private static class TrieNode {
            TrieNode[] children = new TrieNode[26];
            int v = 0;
        }
    }

    static class MapSum_v2 {
        TrieNode root;
        Map<String, Integer> map;

        public MapSum_v2() {
            root = new TrieNode();
            map = new HashMap<>();
        }

        public void insert(String key, int val) {
            Integer old = map.getOrDefault(key, 0);
            int d = val - old;
            map.put(key, val);
            char[] cs = key.toCharArray();
            TrieNode t = root;
            for (char c : cs) {
                if (t.children[c - 'a'] == null) {
                    t.children[c - 'a'] = new TrieNode();
                }
                t = t.children[c - 'a'];
                t.v += d;
            }
        }

        public int sum(String prefix) {
            char[] cs = prefix.toCharArray();
            TrieNode t = root;
            for (char c : cs) {
                TrieNode cur = t.children[c - 'a'];
                if (cur == null) {
                    return 0;
                }
                t = cur;
            }
            return t.v;
        }

        private static class TrieNode {
            TrieNode[] children = new TrieNode[26];
            int v = 0;
        }
    }

    public static void main(String[] args) {
        // MapSum_v1 mapSumV1 = new MapSum_v1();
        // mapSumV1.insert("app", 3);
        // System.out.println(mapSumV1.sum("app"));           // return 5 (apple + app = 3 + 2 = 5)
        // mapSumV1.insert("ap", 2);
        // System.out.println(mapSumV1.sum("ap"));

        MapSum_v2 mapSumV2 = new MapSum_v2();
        mapSumV2.insert("app", 3);
        System.out.println(mapSumV2.sum("app"));
        mapSumV2.insert("ap", 2);
        System.out.println(mapSumV2.sum("ap"));
        mapSumV2.insert("app", 2);
        System.out.println(mapSumV2.sum("a"));
    }
}
