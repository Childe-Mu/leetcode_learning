package com.moon.leetcode.offer;

// 剑指 Offer 12. 矩阵中的路径
//
//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
//
// 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
//
// 示例 1：
//
//输入：board = [
//              ["A","B","C","E"],
//              ["S","F","C","S"],
//              ["A","D","E","E"]
// ],
// word = "ABCCED"
//输出：true
//
// 示例 2：
//
//输入：board = [["a","b"],["c","d"]], word = "abcd"
//输出：false
//
// 提示：
//
// 1 <= board.length <= 200
// 1 <= board[i].length <= 200
// board 和 word 仅由大小写英文字母组成
//
// 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/
// Related Topics 深度优先搜索
// 👍 302 👎 0
public class Offer_12_exist {

    private boolean[] status;

    public static void main(String[] args) {
        System.out.println(new Offer_12_exist().exist_v1(
                new char[][]{
                        {'A', 'B', 'C', 'E'},
                        {'S', 'F', 'C', 'S'},
                        {'A', 'D', 'E', 'E'}
                },
                "ABCCED"));
    }

    public boolean exist_v1(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int o = word.length();
        if (o > m * n) {
            return false;
        }
        status = new boolean[o];
        boolean[][] used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    traverse(board, i, j, 0, word.toCharArray(), used);
                    if (status[o - 1]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void traverse(char[][] board, int i, int j, int k, char[] words, boolean[][] used) {
        int m = board.length;
        int n = board[0].length;
        int o = words.length;
        if (i < 0 || i >= m || j < 0 || j >= n || k >= o || used[i][j] || board[i][j] != words[k]) {
            return;
        }
        used[i][j] = true;
        status[k] = true;
        traverse(board, i + 1, j, k + 1, words, used);
        traverse(board, i - 1, j, k + 1, words, used);
        traverse(board, i, j + 1, k + 1, words, used);
        traverse(board, i, j - 1, k + 1, words, used);
        if (status[o - 1] || status[k + 1]) {
            return;
        }
        used[i][j] = false;
        status[k] = false;
    }

    public boolean exist_v2(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) {
            return false;
        }
        if (k == word.length - 1) {
            return true;
        }
        board[i][j] = '\0';
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1)
                || dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i, j - 1, k + 1);
        board[i][j] = word[k];
        return res;
    }
}