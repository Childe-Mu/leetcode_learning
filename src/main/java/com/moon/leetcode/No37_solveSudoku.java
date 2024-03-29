package com.moon.leetcode;

import java.util.Arrays;

// 37. 解数独
//编写一个程序，通过填充空格来解决数独问题。
//
// 数独的解法需 遵循如下规则：
//
//
// 数字 1-9 在每一行只能出现一次。
// 数字 1-9 在每一列只能出现一次。
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
//
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。
//
//
//
//
//
//
// 示例：
//
//
//输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5","."
//,".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".","."
//,"3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"
//],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],["
//.",".",".",".","8",".",".","7","9"]]
//输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"
//],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["
//4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","
//6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","
//5","2","8","6","1","7","9"]]
//解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
//
//
//
//
//
//
// 提示：
//
//
// board.length == 9
// board[i].length == 9
// board[i][j] 是一位数字或者 '.'
// 题目数据 保证 输入数独仅有一个解
//
//
//
//
// Related Topics 数组 回溯 矩阵
// 👍 1043 👎 0
public class No37_solveSudoku {

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        new No37_solveSudoku().solveSudoku_v1(board);
        System.out.println(Arrays.deepToString(board));
    }

    private int[] col;
    private int[] row;
    private int[] area;

    public void solveSudoku_v1(char[][] board) {
        this.col = new int[9];
        this.row = new int[9];
        this.area = new int[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int x = c - '0';
                row[i] |= 1 << x;
                col[j] |= 1 << x;
                int k = j / 3 + i / 3 * 3;
                area[k] |= 1 << x;
            }
        }
        backtrack_v1(board, 0, 0);
    }

    private boolean backtrack_v1(char[][] b, int i, int j) {
        if (j == 9) {
            i++;
            j = 0;
        }
        while (i < 9 && j < 9 && b[i][j] != '.') {
            i = i + (j + 1) / 9;
            j = (j + 1) % 9;
        }
        if (i >= 9 || j >= 9) {
            return true;
        }
        int k = j / 3 + i / 3 * 3;
        for (int x = 1; x <= 9; x++) {
            if (((row[i] >> x) & 1) == 1 || ((col[j] >> x) & 1) == 1 || ((area[k] >> x) & 1) == 1) {
                continue;
            }
            b[i][j] = (char) ('0' + x);
            row[i] |= 1 << x;
            col[j] |= 1 << x;
            area[k] |= 1 << x;
            if (backtrack_v1(b, i, j + 1)) {
                return true;
            }
            b[i][j] = '.';
            row[i] ^= 1 << x;
            col[j] ^= 1 << x;
            area[k] ^= 1 << x;
        }
        return false;
    }
}
