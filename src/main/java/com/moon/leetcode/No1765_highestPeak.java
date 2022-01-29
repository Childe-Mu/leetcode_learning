package com.moon.leetcode;

import java.util.*;

/**
 * 1765.地图中的最高点
 * <p>给你一个大小为 <code>m x n</code> 的整数矩阵 <code>isWater</code> ，它代表了一个由 <strong>陆地</strong> 和 <strong>水域</strong> 单元格组成的地图。</p>
 *
 * <ul>
 * <li>如果 <code>isWater[i][j] == 0</code> ，格子 <code>(i, j)</code> 是一个 <strong>陆地</strong> 格子。</li>
 * <li>如果 <code>isWater[i][j] == 1</code> ，格子 <code>(i, j)</code> 是一个 <strong>水域</strong> 格子。</li>
 * </ul>
 *
 * <p>你需要按照如下规则给每个单元格安排高度：</p>
 *
 * <ul>
 * <li>每个格子的高度都必须是非负的。</li>
 * <li>如果一个格子是是 <strong>水域</strong> ，那么它的高度必须为 <code>0</code> 。</li>
 * <li>任意相邻的格子高度差 <strong>至多</strong> 为 <code>1</code> 。当两个格子在正东、南、西、北方向上相互紧挨着，就称它们为相邻的格子。（也就是说它们有一条公共边）</li>
 * </ul>
 *
 * <p>找到一种安排高度的方案，使得矩阵中的最高高度值 <strong>最大</strong> 。</p>
 *
 * <p>请你返回一个大小为 <code>m x n</code> 的整数矩阵 <code>height</code> ，其中 <code>height[i][j]</code> 是格子 <code>(i, j)</code> 的高度。如果有多种解法，请返回 <strong>任意一个</strong> 。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><strong><img alt="" src="https://assets.leetcode.com/uploads/2021/01/10/screenshot-2021-01-11-at-82045-am.png" style="width: 220px; height: 219px;" /></strong></p>
 *
 * <pre>
 * <b>输入：</b>isWater = [[0,1],[0,0]]
 * <b>输出：</b>[[1,0],[2,1]]
 * <b>解释：</b>上图展示了给各个格子安排的高度。
 * 蓝色格子是水域格，绿色格子是陆地格。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <p><strong><img alt="" src="https://assets.leetcode.com/uploads/2021/01/10/screenshot-2021-01-11-at-82050-am.png" style="width: 300px; height: 296px;" /></strong></p>
 *
 * <pre>
 * <b>输入：</b>isWater = [[0,0,1],[1,0,0],[0,0,0]]
 * <b>输出：</b>[[1,1,0],[0,1,1],[1,2,2]]
 * <b>解释：</b>所有安排方案中，最高可行高度为 2 。
 * 任意安排方案中，只要最高高度为 2 且符合上述规则的，都为可行方案。
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == isWater.length</code></li>
 * <li><code>n == isWater[i].length</code></li>
 * <li><code>1 <= m, n <= 1000</code></li>
 * <li><code>isWater[i][j]</code> 要么是 <code>0</code> ，要么是 <code>1</code> 。</li>
 * <li>至少有 <strong>1</strong> 个水域格子。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>广度优先搜索</li><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 80</li><li>👎 0</li></div>
 */
public class No1765_highestPeak {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[][] highestPeak_v1(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;
        int[][] res = new int[m][n];
        Deque<int[]> queue = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    set.add(i << 10 | j);
                }
            }
        }
        int h = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] p = queue.poll();
                res[p[0]][p[1]] = h;
                for (int[] d : dirs) {
                    int i = p[0] + d[0];
                    int j = p[1] + d[1];
                    if (0 <= i && i < m && 0 <= j && j < n && !set.contains(i << 10 | j)) {
                        queue.offer(new int[]{i, j});
                        set.add(i << 10 | j);
                    }
                }
            }
            h++;
        }
        return res;
    }

    public int[][] highestPeak_v2(int[][] isWater) {
        int b = 0x3FF;
        int m = isWater.length;
        int n = isWater[0].length;
        int[][] res = new int[m][n];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            Arrays.fill(res[i], -1);
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    queue.offer(i << 10 | j);
                    res[i][j] = 0;
                }
            }
        }
        int h = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int p = queue.poll();
                for (int[] d : dirs) {
                    int i = (p >> 10) + d[0];
                    int j = (p & b) + d[1];
                    if (0 <= i && i < m && 0 <= j && j < n && res[i][j] == -1) {
                        queue.offer(i << 10 | j);
                        res[i][j] = h;
                    }
                }
            }
            h++;
        }
        return res;
    }

    public int[][] highestPeak_v3(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(ans[i], -1); // -1 表示该格子尚未被访问过
        }
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (isWater[i][j] == 1) {
                    ans[i][j] = 0;
                    queue.offer(new int[]{i, j}); // 将所有水域入队
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int[] dir : dirs) {
                int x = p[0] + dir[0], y = p[1] + dir[1];
                if (0 <= x && x < m && 0 <= y && y < n && ans[x][y] == -1) {
                    ans[x][y] = ans[p[0]][p[1]] + 1;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new No1765_highestPeak().highestPeak_v1(new int[][]{{0, 0, 1}, {1, 0, 0}, {0, 0, 0}})));
    }
}
