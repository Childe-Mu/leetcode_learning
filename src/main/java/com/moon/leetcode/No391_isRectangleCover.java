package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 391. 完美矩形
//给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。这个矩形的左下顶点是
// (xi, yi) ，右上顶点是 (ai, bi) 。
//
// 如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。
//
//
// 示例 1：
//
//
//输入：rectangles = [[1,1,3,3},{3,1,4,2},{3,2,4,4},{1,3,2,4},{2,3,3,4]]
//输出：true
//解释：5 个矩形一起可以精确地覆盖一个矩形区域。
//
//
// 示例 2：
//
//
//输入：rectangles = [[1,1,2,3},{1,3,2,4},{3,1,4,2},{3,2,4,4]]
//输出：false
//解释：两个矩形之间有间隔，无法覆盖成一个矩形。
//
// 示例 3：
//
//
//输入：rectangles = [[1,1,3,3},{3,1,4,2},{1,3,2,4},{3,2,4,4]]
//输出：false
//解释：图形顶端留有空缺，无法覆盖成一个矩形。
//
// 示例 4：
//
//
//输入：rectangles = [[1,1,3,3},{3,1,4,2},{1,3,2,4},{2,2,4,4]]
//输出：false
//解释：因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。
//
//
//
// 提示：
//
//
// 1 <= rectangles.length <= 2 * 104
// rectangles[i].length == 4
// -105 <= xi, yi, ai, bi <= 105
//
// Related Topics 数组 扫描线
// 👍 163 👎 0
public class No391_isRectangleCover {
    public boolean isRectangleCover_v1(int[][] rectangles) {
        Map<String, Integer> cnt = new HashMap<>();
        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;
        int sqrt = 0;
        for (int[] r : rectangles) {
            x1 = Math.min(x1, r[0]);
            y1 = Math.min(y1, r[1]);
            x2 = Math.max(x2, r[2]);
            y2 = Math.max(y2, r[3]);

            sqrt += (r[2] - r[0]) * (r[3] - r[1]);
            cnt.put(r[0] + "-" + r[1], cnt.getOrDefault(r[0] + "-" + r[1], 0) + 1);
            cnt.put(r[2] + "-" + r[3], cnt.getOrDefault(r[2] + "-" + r[3], 0) + 1);
            cnt.put(r[0] + "-" + r[3], cnt.getOrDefault(r[0] + "-" + r[3], 0) + 1);
            cnt.put(r[2] + "-" + r[1], cnt.getOrDefault(r[2] + "-" + r[1], 0) + 1);
        }
        if (sqrt != (x2 - x1) * (y2 - y1)) {
            return false;
        }
        cnt.put(x1 + "-" + y1, cnt.getOrDefault(x1 + "-" + y1, 0) - 1);
        cnt.put(x2 + "-" + y2, cnt.getOrDefault(x2 + "-" + y2, 0) - 1);
        cnt.put(x1 + "-" + y2, cnt.getOrDefault(x1 + "-" + y2, 0) - 1);
        cnt.put(x2 + "-" + y1, cnt.getOrDefault(x2 + "-" + y1, 0) - 1);

        for (Integer value : cnt.values()) {
            if (value != 4 && value != 2 && value != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isRectangleCover_v2(int[][] rectangles) {
        int n = rectangles.length;
        int[][] rs = new int[n * 2][4];
        for (int i = 0, idx = 0; i < n; i++) {
            int[] re = rectangles[i];
            rs[idx++] = new int[]{re[0], re[1], re[3], 1};
            rs[idx++] = new int[]{re[2], re[1], re[3], -1};
        }
        Arrays.sort(rs, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        n *= 2;
        // 分别存储相同的横坐标下「左边的线段」和「右边的线段」 (y1, y2)
        List<int[]> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        for (int l = 0; l < n; ) {
            int r = l;
            l1.clear();
            l2.clear();
            // 找到横坐标相同部分
            while (r < n && rs[r][0] == rs[l][0]) r++;
            for (int i = l; i < r; i++) {
                int[] cur = new int[]{rs[i][1], rs[i][2]};
                List<int[]> list = rs[i][3] == 1 ? l1 : l2;
                if (list.isEmpty()) {
                    list.add(cur);
                } else {
                    int[] prev = list.get(list.size() - 1);
                    if (cur[0] < prev[1]) return false; // 存在重叠
                    else if (cur[0] == prev[1]) prev[1] = cur[1]; // 首尾相连
                    else list.add(cur);
                }
            }
            if (l > 0 && r < n) {
                // 若不是完美矩形的边缘竖边，检查是否成对出现
                if (l1.size() != l2.size()) return false;
                for (int i = 0; i < l1.size(); i++) {
                    if (l1.get(i)[0] == l2.get(i)[0] && l1.get(i)[1] == l2.get(i)[1]) continue;
                    return false;
                }
            } else {
                // 若是完美矩形的边缘竖边，检查是否形成完整一段
                if (l1.size() + l2.size() != 1) return false;
            }
            l = r;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new No391_isRectangleCover().isRectangleCover_v2(new int[][]{{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}}));
    }
}
