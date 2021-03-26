package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * 51. N 皇后
 * <p>
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * <p>
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：[["Q"]]
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 9
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 */
public class No51_solveNQueens {
    static String s;

    public static List<List<String>> solveNQueens_v1(int n) {
        char[] empty = new char[n];
        Arrays.fill(empty, '.');
        s = new String(empty);
        List<List<String>> res = new ArrayList<>();
        Deque<String> path = new ArrayDeque<>();
        // 列有没有被使用
        boolean[] usedColumn = new boolean[n];
        // 正斜线有没有被使用 “/”
        boolean[] usedDiagonal1 = new boolean[2 * n - 1];
        // 反斜线有没有被使用 “\”
        boolean[] usedDiagonal2 = new boolean[2 * n - 1];
        backtrack(n, 0, path, usedColumn, usedDiagonal1, usedDiagonal2, res);
        return res;
    }

    private static void backtrack(int n, int depth, Deque<String> path, boolean[] usedColumn, boolean[] usedDiagonal1, boolean[] usedDiagonal2, List<List<String>> res) {
        if (depth == n) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (usedColumn[i] || usedDiagonal1[depth + i] || usedDiagonal2[n - 1 - depth + i]) {
                continue;
            }
            path.addLast(new StringBuilder(s).replace(i, i + 1, "Q").toString());
            usedColumn[i] = true;
            usedDiagonal1[depth + i] = true;
            usedDiagonal2[n - 1 - depth + i] = true;
            backtrack(n, depth + 1, path, usedColumn, usedDiagonal1, usedDiagonal2, res);
            usedDiagonal2[n - 1 - depth + i] = false;
            usedDiagonal1[depth + i] = false;
            usedColumn[i] = false;
            path.removeLast();
        }
    }

    public static List<List<String>> solveNQueens_v2(int n) {
        char[] empty = new char[n];
        Arrays.fill(empty, '.');
        s = new String(empty);
        List<List<String>> res = new ArrayList<>();
        Deque<String> path = new ArrayDeque<>();
        backtrack(n, 0, 0, 0, 0, res, path);
        return res;
    }

    private static void backtrack(int len, int row, int column, int diagonal1, int diagonal2, List<List<String>> res, Deque<String> path) {
        if (row == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        int availablePositions = ((1 << len) - 1) & (~(column | diagonal1 | diagonal2));
        while (availablePositions != 0) {
            int position = availablePositions & (-availablePositions);
            int col = Integer.bitCount(position - 1);
            path.add(new StringBuilder(s).replace(col, col + 1, "Q").toString());
            availablePositions &= (availablePositions - 1);
            backtrack(len, row + 1, column | position, (diagonal1 | position) << 1, (diagonal2 | position) >> 1, res, path);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        int i = Integer.bitCount(1);
        System.out.println(solveNQueens_v2(4));
    }
}
