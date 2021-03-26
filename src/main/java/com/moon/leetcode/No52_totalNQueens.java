package com.moon.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 52. N皇后 II
 * <p>
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：1
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 9
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 */
public class No52_totalNQueens {
    private int total = 0;

    public static void main(String[] args) {
        System.out.println(new No52_totalNQueens().totalNQueens(4));
    }

    public int totalNQueens(int n) {
        Set<Integer> column = new HashSet<>();
        Set<Integer> diagonal1 = new HashSet<>();
        Set<Integer> diagonal2 = new HashSet<>();
        backtrack(n, 0, column, diagonal1, diagonal2);
        return total;
    }

    private void backtrack(int n, int row, Set<Integer> column, Set<Integer> diagonal1, Set<Integer> diagonal2) {
        if (row == n) {
            total++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (column.contains(i) || diagonal1.contains(row + i) || diagonal2.contains(row - i)) {
                continue;
            }
            column.add(i);
            diagonal1.add(row + i);
            diagonal2.add(row - i);
            backtrack(n, row + 1, column, diagonal1, diagonal2);
            diagonal2.remove(row - i);
            diagonal1.remove(row + i);
            column.remove(i);
        }
    }
}
