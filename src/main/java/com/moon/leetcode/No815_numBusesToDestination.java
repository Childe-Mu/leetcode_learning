package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// 815. 公交路线
//
//给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
//
// 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1
//-> ... 这样的车站路线行驶。
//
// 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
//
// 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
//
// 示例 1：
//
//输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
//输出：2
//解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
//
// 示例 2：
//
//输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
//输出：-1
//
// 提示：
//
// 1 <= routes.length <= 500.
// 1 <= routes[i].length <= 105
// routes[i] 中的所有值 互不相同
// sum(routes[i].length) <= 105
// 0 <= routes[i][j] < 106
// 0 <= source, target < 106
//
// Related Topics 广度优先搜索 数组 哈希表
// 👍 161 👎 0
public class No815_numBusesToDestination {
    public static void main(String[] args) {
        System.out.println(new No815_numBusesToDestination().numBusesToDestination(new int[][]{{1, 2, 7}, {3, 6, 7}}, 1, 6));
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int n = routes.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        boolean[][] path = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            int[] route = routes[i];
            for (int site : route) {
                List<Integer> list = map.computeIfAbsent(site, o -> new ArrayList<>());
                for (Integer j : list) {
                    path[i][j] = path[j][i] = true;
                }
                list.add(i);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        for (Integer route : map.getOrDefault(source, new ArrayList<>())) {
            queue.offer(route);
            dis[route] = 1;
        }
        while (!queue.isEmpty()) {
            Integer x = queue.poll();
            for (int y = 0; y < n; y++) {
                if (path[x][y] && dis[y] == -1) {
                    dis[y] = dis[x] + 1;
                    queue.offer(y);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (Integer route : map.getOrDefault(target, new ArrayList<>())) {
            if (dis[route] != -1) {
                ans = Math.min(ans, dis[route]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
