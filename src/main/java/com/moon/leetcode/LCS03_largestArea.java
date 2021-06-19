package com.moon.leetcode;

import java.util.Arrays;

//1000081. 主题空间
//「以扣会友」线下活动所在场地由若干主题空间与走廊组成，场地的地图记作由一维字符串型数组 grid，字符串中仅包含
// "0"～"5" 这 6 个字符。地图上每一个字符代表面积为 1 的区域，其中 "0" 表示走廊，其他字符表示主题空间。
// 相同且连续（连续指上、下、左、右四个方向连接）的字符组成同一个主题空间。
//
//假如整个 grid 区域的外侧均为走廊。请问，不与走廊直接相邻的主题空间的最大面积是多少？如果不存在这样的空间请返回 0。
//
//示例 1:
//
//输入：grid = ["110","231","221"]
//
//输出：1
//
//解释：4 个主题空间中，只有 1 个不与走廊相邻，面积为 1。
//image.png
//
//示例 2:
//
//输入：grid = ["11111100000","21243101111","21224101221","11111101111"]
//
//输出：3
//
//解释：8 个主题空间中，有 5 个不与走廊相邻，面积分别为 3、1、1、1、2，最大面积为 3。
//image.png
//
//提示：
//
//1 <= grid.length <= 500
//1 <= grid[i].length <= 500
//grid[i][j] 仅可能是 "0"～"5"
public class LCS03_largestArea {
    int n, m;
    char[][] chs;
    boolean flag;
    int num;
    int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        System.out.println(new LCS03_largestArea().largestArea(new String[]{"110", "231", "221"}));
    }

    public int largestArea(String[] grid) {

        n = grid.length;
        m = grid[0].length();
        chs = new char[n + 2][m + 2];
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(chs[i], '0');
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                chs[i][j] = grid[i - 1].charAt(j - 1);
            }
        }

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (chs[i][j] == '0' || chs[i][j] == '#') {
                    continue;
                }
                flag = true;
                num = 0;
                dfs(i, j);
                if (flag) {
                    ans = Math.max(ans, num);
                }
            }
        }
        return ans;

    }

    private void dfs(int i, int j) {
        int tmp = chs[i][j] - '0';
        if (tmp > 0 && tmp <= 5) {
            num++;
            chs[i][j] = '#';
            for (int k = 0; k < 4; k++) {
                int l = i + dir[k][0];
                int r = j + dir[k][1];
                int nextTmp = chs[l][r] - '0';
                if (nextTmp == 0) {
                    flag = false;
                }
                if (nextTmp == tmp) {
                    dfs(l, r);
                }
            }
        }
    }
}
