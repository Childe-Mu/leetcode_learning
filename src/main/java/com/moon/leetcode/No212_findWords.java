package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 212. 单词搜索 II
 * <p>给定一个 <code>m x n</code> 二维字符网格 <code>board</code><strong> </strong>和一个单词（字符串）列表 <code>words</code>，找出所有同时在二维网格和字典中出现的单词。</p>
 *
 * <p>单词必须按照字母顺序，通过 <strong>相邻的单元格</strong> 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/07/search1.jpg" style="width: 322px; height: 322px;" />
 * <pre>
 * <strong>输入：</strong>board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * <strong>输出：</strong>["eat","oath"]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/07/search2.jpg" style="width: 162px; height: 162px;" />
 * <pre>
 * <strong>输入：</strong>board = [["a","b"],["c","d"]], words = ["abcb"]
 * <strong>输出：</strong>[]
 * </pre>
 * <p>
 * p> </p>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == board.length</code></li>
 * <li><code>n == board[i].length</code></li>
 * <li><code>1 <= m, n <= 12</code></li>
 * <li><code>board[i][j]</code> 是一个小写英文字母</li>
 * <li><code>1 <= words.length <= 3 * 10<sup>4</sup></code></li>
 * <li><code>1 <= words[i].length <= 10</code></li>
 * <li><code>words[i]</code> 由小写英文字母组成</li>
 * <li><code>words</code> 中的所有字符串互不相同</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字典树</li><li>数组</li><li>字符串</li><li>回溯</li><li>矩阵</li></div></div><br><div><li>👍 517</li><li>👎 0</li></div>
 */
public class No212_findWords {

    private boolean[][] u;
    private int m;
    private int n;
    private char[][] b;
    private int[][] direct = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) {
        System.out.println(new Solution_v3().findWords(
                new char[][]{{'o', 'a', 'b', 'n'}, {'o', 't', 'a', 'e'}, {'a', 'h', 'k', 'r'}, {'a', 'f', 'l', 'v'}},
                new String[]{"oa", "oaa"}));
    }

    public List<String> findWords(char[][] board, String[] words) {
        m = board.length;
        n = board[0].length;
        u = new boolean[m][n];
        b = board;
        List<String> res = new ArrayList<>();
        Map<Character, List<String>> map = new HashMap<>();
        for (String word : words) {
            map.computeIfAbsent(word.charAt(0), o -> new ArrayList<>()).add(word);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i][j];
                List<String> list = map.getOrDefault(c, new ArrayList<>());
                Iterator<String> iter = list.iterator();
                while (iter.hasNext()) {
                    String w = iter.next();
                    u[i][j] = true;
                    if (dfs(w.toCharArray(), 1, i, j)) {
                        res.add(w);
                        iter.remove();
                    }
                    u[i][j] = false;
                }
            }
        }
        return res;
    }

    private boolean dfs(char[] cs, int k, int i, int j) {
        if (k == cs.length) {
            return true;
        }
        boolean res = false;
        for (int[] d : direct) {
            int x = i + d[0];
            int y = j + d[1];
            if (0 <= x && x < m && 0 <= y && y < n && !u[x][y] && cs[k] == b[x][y]) {
                u[x][y] = true;
                res = dfs(cs, k + 1, x, y);
                u[x][y] = false;
                if (res) {
                    return res;
                }
            }
        }
        return res;
    }

    private static class Solution_v1 {
        Set<String> set = new HashSet<>();
        List<String> ans = new ArrayList<>();
        char[][] board;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int n, m;
        boolean[][] vis = new boolean[15][15];

        public List<String> findWords(char[][] _board, String[] words) {
            board = _board;
            m = board.length;
            n = board[0].length;
            set.addAll(Arrays.asList(words));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    vis[i][j] = true;
                    sb.append(board[i][j]);
                    dfs(i, j, sb);
                    vis[i][j] = false;
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
            return ans;
        }

        void dfs(int i, int j, StringBuilder sb) {
            if (sb.length() > 10) return;
            if (set.contains(sb.toString())) {
                ans.add(sb.toString());
                set.remove(sb.toString());
            }
            for (int[] d : dirs) {
                int dx = i + d[0];
                int dy = j + d[1];
                if (dx < 0 || dx >= m || dy < 0 || dy >= n || vis[dx][dy]) {
                    continue;
                }
                vis[dx][dy] = true;
                sb.append(board[dx][dy]);
                dfs(dx, dy, sb);
                vis[dx][dy] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    private static class Solution_v2 {
        Set<String> set = new HashSet<>();
        char[][] board;
        int n, m;
        TrieNode root = new TrieNode();
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] vis = new boolean[15][15];

        void insert(String s) {
            TrieNode p = root;
            for (int i = 0; i < s.length(); i++) {
                int u = s.charAt(i) - 'a';
                if (p.tns[u] == null) {
                    p.tns[u] = new TrieNode();
                }
                p = p.tns[u];
            }
            p.s = s;
        }

        public List<String> findWords(char[][] _board, String[] words) {
            board = _board;
            m = board.length;
            n = board[0].length;
            for (String w : words) {
                insert(w);
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int u = board[i][j] - 'a';
                    if (root.tns[u] != null) {
                        vis[i][j] = true;
                        dfs(i, j, root.tns[u]);
                        vis[i][j] = false;
                    }
                }
            }
            List<String> ans = new ArrayList<>();
            for (String s : set) ans.add(s);
            return ans;
        }

        void dfs(int i, int j, TrieNode node) {
            if (node.s != null) {
                set.add(node.s);
            }
            for (int[] d : dirs) {
                int x = i + d[0];
                int y = j + d[1];
                if (x < 0 || x >= m || y < 0 || y >= n || vis[x][y]) {
                    continue;
                }
                int u = board[x][y] - 'a';
                if (node.tns[u] != null) {
                    vis[x][y] = true;
                    dfs(x, y, node.tns[u]);
                    vis[x][y] = false;
                }
            }
        }

        private static class TrieNode {
            String s;
            TrieNode[] tns = new TrieNode[26];
        }
    }

    private static class Solution_v3 {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        public List<String> findWords(char[][] board, String[] words) {
            Trie trie = new Trie();
            for (String word : words) {
                trie.insert(word);
            }
            Set<String> ans = new HashSet<>();
            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[0].length; ++j) {
                    dfs(board, trie, i, j, ans);
                }
            }
            return new ArrayList<>(ans);
        }

        public void dfs(char[][] board, Trie now, int i, int j, Set<String> ans) {
            if (!now.children.containsKey(board[i][j])) {
                return;
            }
            char ch = board[i][j];
            Trie nxt = now.children.get(ch);
            if (Objects.nonNull(nxt.word)) {
                ans.add(nxt.word);
                nxt.word = null;
            }

            if (!nxt.children.isEmpty()) {
                board[i][j] = '#';
                for (int[] d : dirs) {
                    int x = i + d[0];
                    int y = j + d[1];
                    if (0 <= x && x < board.length && 0 <= y && y < board[0].length) {
                        dfs(board, nxt, x, y, ans);
                    }
                }
                board[i][j] = ch;
            } else {
                now.children.remove(ch);
            }
        }

        private static class Trie {
            String word;
            Map<Character, Trie> children;
            boolean isWord;

            public Trie() {
                this.word = null;
                this.children = new HashMap<>();
            }

            public void insert(String word) {
                Trie cur = this;
                for (int i = 0; i < word.length(); ++i) {
                    char c = word.charAt(i);
                    if (!cur.children.containsKey(c)) {
                        cur.children.put(c, new Trie());
                    }
                    cur = cur.children.get(c);
                }
                cur.word = word;
            }
        }
    }
}
