package com.moon.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// LCP 45. 自行车炫技赛场
//「力扣挑战赛」中 `N*M` 大小的自行车炫技赛场的场地由一片连绵起伏的上下坡组成，场地的高度值记录于二维数组 `terrain` 中，场地的减速值记录于二
//维数组 `obstacle` 中。
//- 若选手骑着自行车从高度为 `h1` 且减速值为 `o1` 的位置到高度为 `h2` 且减速值为 `o2` 的相邻位置，速度变化值为 `h1-h2-o2`
//（负值减速，正值增速）。
//
//选手初始位于坐标 `position` 处且初始速度为 1，请问选手可以刚好到其他哪些位置时速度依旧为 1。请以二维数组形式返回这些位置。若有多个位置则按行
//坐标升序排列，若有多个位置行坐标相同则按列坐标升序排列。
//
//**注意：** 骑行过程中速度不能为零或负值
//
//**示例 1：**
//> 输入：`position = [0,0], terrain = [[0,0],[0,0]], obstacle = [[0,0],[0,0]]`
//>
//> 输出：`[[0,1],[1,0],[1,1]]`
//>
//> 解释：
//> 由于当前场地属于平地，根据上面的规则，选手从`[0,0]`的位置出发都能刚好在其他处的位置速度为 1。
//
//**示例 2：**
//> 输入：`position = [1,1], terrain = [[5,0],[0,6]], obstacle = [[0,6],[7,0]]`
//>
//> 输出：`[[0,1]]`
//>
//> 解释：
//> 选手从 `[1,1]` 处的位置出发，到 `[0,1]` 处的位置时恰好速度为 1。
//
//
//**提示：**
//- `n == terrain.length == obstacle.length`
//- `m == terrain[i].length == obstacle[i].length`
//- `1 <= n <= 100`
//- `1 <= m <= 100`
//- `0 <= terrain[i][j], obstacle[i][j] <= 100`
//- `position.length == 2`
//- `0 <= position[0] < n`
//- `0 <= position[1] < m` 👍 1 👎 0
public class LCP_45_bicycleYard {
    public int[][] bicycleYard(int[] position, int[][] terrain, int[][] obstacle) {
        int m = terrain.length;
        int n = terrain[0].length;
        List<int[]> res = new ArrayList<>();
        Map<String, Set<Integer>> used = new HashMap<>();
        used.computeIfAbsent(position[0] + "_" + position[1], o -> new HashSet<>()).add(1);
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{position[0], position[1], 1});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int s = poll[2];
            int h1 = terrain[x][y];
            for (int[] d : dir) {
                int a = d[0] + x;
                int b = d[1] + y;
                if (0 <= a && a < m && 0 <= b && b < n) {
                    int h2 = terrain[a][b];
                    int o = obstacle[a][b];
                    int ss = h1 - h2 - o + s;
                    String key = a + "_" + b;
                    if (ss < 1) {
                        continue;
                    }
                    if (used.getOrDefault(key, new HashSet<>()).contains(ss)) {
                        continue;
                    }
                    if (ss == 1) {
                        res.add(new int[]{a, b});
                    }
                    used.computeIfAbsent(key, p -> new HashSet<>()).add(ss);
                    queue.offer(new int[]{a, b, ss});
                }
            }
        }
        res.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        return res.toArray(new int[0][0]);
    }
}
