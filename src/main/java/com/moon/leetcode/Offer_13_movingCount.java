package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

// 剑指 Offer 13. 机器人的运动范围
//
//地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一
//格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但
//它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
//
// 示例 1：
//
// 输入：m = 2, n = 3, k = 1
//输出：3
//
// 示例 2：
//
// 输入：m = 3, n = 1, k = 0
//输出：1
//
// 提示：
//
// 1 <= n,m <= 100
// 0 <= k <= 20
//
// 👍 273 👎 0
public class Offer_13_movingCount {
    public static void main(String[] args) {
        System.out.println(new Offer_13_movingCount().movingCount_v1(15, 15, 10));
    }

    public int movingCount_v1(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return traverse(visited, m, n, k, 0, 0);
    }

    private int traverse(boolean[][] visited, int m, int n, int k, int i, int j) {
        if (i >= m || i < 0 || j >= n || j < 0 || visited[i][j] || cal(i, j) > k) {
            return 0;
        }
        visited[i][j] = true;
        return traverse(visited, m, n, k, i + 1, j) + traverse(visited, m, n, k, i, j + 1) + traverse(visited, m, n, k, i - 1, j) + traverse(visited, m, n, k, i, j - 1) + 1;
    }

    private int cal(int i, int j) {
        int sum = 0;
        int digit = 10;
        while (i > 0) {
            sum = sum + i % digit;
            i = i / digit;
            digit = digit * 10;
        }
        digit = 10;
        while (j > 0) {
            sum = sum + j % digit;
            j = j / digit;
            digit = digit * 10;
        }
        return sum;
    }

    public int movingCount_v2(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        Queue<int[]> queue = new ArrayDeque<>();
        // 向右和向下的方向数组
        int[] dx = {0, 1};
        int[] dy = {1, 0};
        boolean[][] vis = new boolean[m][n];
        queue.offer(new int[]{0, 0});
        vis[0][0] = true;
        int ans = 1;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 2; ++i) {
                int tx = dx[i] + x;
                int ty = dy[i] + y;
                if (tx < 0 || tx >= m || ty < 0 || ty >= n || vis[tx][ty] || get(tx) + get(ty) > k) {
                    continue;
                }
                queue.offer(new int[]{tx, ty});
                vis[tx][ty] = true;
                ans++;
            }
        }
        return ans;
    }

    public int movingCount_v3(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        boolean[][] vis = new boolean[m][n];
        int ans = 1;
        vis[0][0] = true;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i == 0 && j == 0) || get(i) + get(j) > k) {
                    continue;
                }
                // 边界判断
                if (i - 1 >= 0) {
                    vis[i][j] |= vis[i - 1][j];
                }
                if (j - 1 >= 0) {
                    vis[i][j] |= vis[i][j - 1];
                }
                ans += vis[i][j] ? 1 : 0;
            }
        }
        return ans;
    }

    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }

}
