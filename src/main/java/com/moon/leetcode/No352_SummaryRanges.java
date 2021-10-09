package com.moon.leetcode;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 352. 将数据流变为多个不相交区间
 * <p>
 * /**
 * <p>&nbsp;给你一个由非负整数&nbsp;<code>a<sub>1</sub>, a<sub>2</sub>, ..., a<sub>n</sub></code> 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。</p>
 * <p>
 * p>实现 <code>SummaryRanges</code> 类：</p>
 *
 * <div class="original__bRMd">
 * <div>
 * <ul>
 * <li><code>SummaryRanges()</code> 使用一个空数据流初始化对象。</li>
 * <li><code>void addNum(int val)</code> 向数据流中加入整数 <code>val</code> 。</li>
 * <li><code>int[][] getIntervals()</code> 以不相交区间&nbsp;<code>[start<sub>i</sub>, end<sub>i</sub>]</code> 的列表形式返回对数据流中整数的总结。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>
 * ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
 * [[], [1], [], [3], [], [7], [], [2], [], [6], []]
 * <strong>输出：</strong>
 * [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
 *
 * <strong>解释：</strong>
 * SummaryRanges summaryRanges = new SummaryRanges();
 * summaryRanges.addNum(1);      // arr = [1]
 * summaryRanges.getIntervals(); // 返回 [[1, 1]]
 * summaryRanges.addNum(3);      // arr = [1, 3]
 * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
 * summaryRanges.addNum(7);      // arr = [1, 3, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
 * summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
 * summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= val &lt;= 10<sup>4</sup></code></li>
 * <li>最多调用&nbsp;<code>addNum</code> 和 <code>getIntervals</code> 方法 <code>3 * 10<sup>4</sup></code> 次</li>
 * </ul>
 * </div>
 * </div>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?</p>
 * <div><div>Related Topics</div><div><li>设计</li><li>二分查找</li><li>有序集合</li></div></div><br><div><li>👍 107</li><li>👎 0</li></div>
 */
public class No352_SummaryRanges {
    int n = (int) 1e4;
    boolean[] table;
    Map<Integer, int[]> map;

    public No352_SummaryRanges() {
        table = new boolean[n + 5];
        map = new HashMap<>();
    }

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(0, 1, 5, 7, 8, 9, 10, 11, 11, 11, 12, 12, 12, 13, 15, 17, 17, 18, 18, 23, 24, 26, 26, 27, 28, 30, 30, 31, 32, 33, 33, 36, 36, 37, 37, 38, 38, 39, 40, 40, 40, 41, 42, 42, 42, 45, 45, 49, 49, 51, 51, 53, 55, 56, 56, 57, 60, 61, 61, 62, 62, 63, 64, 65, 65, 66, 68, 69, 69, 70, 70, 70, 70, 70, 71, 72, 73, 74, 75, 76, 77, 77, 78, 78, 79, 80, 81, 85, 87, 90, 90, 90, 90, 92, 93, 93, 96, 97, 100, 100);
        list.sort(Comparator.comparingInt(a -> a));
        System.out.println(list);
        No352_SummaryRanges ranges = new No352_SummaryRanges();
        SummaryRanges summaryRanges = new SummaryRanges();
        for (Integer i : list) {
            ranges.addNum(i);
            summaryRanges.addNum(i);
        }
        int[][] a = ranges.getIntervals();
        int[][] b = summaryRanges.getIntervals();
        Arrays.sort(a, (c, d) -> c[0] == d[0] ? c[1] - d[1] : c[0] - d[0]);
        Arrays.sort(b, (c, d) -> c[0] == d[0] ? c[1] - d[1] : c[0] - d[0]);
        System.out.println(Arrays.deepToString(a));
        System.out.println(Arrays.deepToString(b));
        System.out.println();
    }

    public void addNum(int val) {
        if (table[val]) {
            return;
        }
        int pre = val - 1;
        int next = val + 1;
        table[val] = true;
        if (pre >= 0 && next <= n && table[pre] && table[next]) {
            int[] t1 = map.get(pre);
            int[] t2 = map.get(next);
            if (t1[0] == t1[1] && t2[0] == t2[1]) {
                t1[1] = t2[1];
                map.remove(t2[0]);
                map.put(t1[1], t1);
            } else if (t1[0] == t1[1]) {
                t1[1] = t2[1];
                map.remove(t2[0]);
                map.remove(t2[1]);
                map.put(t1[1], t1);
            } else if (t2[0] == t2[1]) {
                map.remove(t1[1]);
                map.remove(t2[1]);
                t1[1] = t2[1];
                map.put(t1[1], t1);
            } else {
                t1[1] = t2[1];
                map.remove(pre);
                map.remove(next);
                map.remove(t2[1]);
                map.put(t1[1], t1);
            }
        } else if (pre >= 0 && table[pre]) {
            int[] t = map.get(pre);
            if (t[0] == t[1]) {
                t[1] = val;
                map.put(val, t);
            } else {
                map.remove(t[1]);
                t[1] = val;
                map.put(val, t);
            }
        } else if (next <= n && table[next]) {
            int[] t = map.get(next);
            if (t[0] == t[1]) {
                t[0] = val;
                map.put(val, t);
            } else {
                map.remove(t[0]);
                t[0] = val;
                map.put(val, t);
            }
        } else {
            int[] t = new int[]{val, val};
            map.put(val, t);
        }
    }

    public int[][] getIntervals() {
        Set<int[]> ints = new HashSet<>(map.values());
        int[][] res = ints.toArray(new int[0][0]);
        Arrays.sort(res, (c, d) -> c[0] == d[0] ? c[1] - d[1] : c[0] - d[0]);
        return res;
    }

    private static class SummaryRanges {
        TreeMap<Integer, Integer> intervals;

        public SummaryRanges() {
            intervals = new TreeMap<>();
        }

        public void addNum(int val) {
            // 找到 l1 最小的且满足 l1 > val 的区间 interval1 = [l1, r1]
            // 如果不存在这样的区间，interval1 为尾迭代器
            Map.Entry<Integer, Integer> interval1 = intervals.ceilingEntry(val + 1);
            // 找到 l0 最大的且满足 l0 <= val 的区间 interval0 = [l0, r0]
            // 在有序集合中，interval0 就是 interval1 的前一个区间
            // 如果不存在这样的区间，interval0 为尾迭代器
            Map.Entry<Integer, Integer> interval0 = intervals.floorEntry(val);

            if (interval0 != null && interval0.getKey() <= val && val <= interval0.getValue()) {
                // 情况一
                return;
            } else {
                boolean leftAside = interval0 != null && interval0.getValue() + 1 == val;
                boolean rightAside = interval1 != null && interval1.getKey() - 1 == val;
                if (leftAside && rightAside) {
                    // 情况四
                    int left = interval0.getKey(), right = interval1.getValue();
                    intervals.remove(interval0.getKey());
                    intervals.remove(interval1.getKey());
                    intervals.put(left, right);
                } else if (leftAside) {
                    // 情况二
                    intervals.put(interval0.getKey(), interval0.getValue() + 1);
                } else if (rightAside) {
                    // 情况三
                    int right = interval1.getValue();
                    intervals.remove(interval1.getKey());
                    intervals.put(val, right);
                } else {
                    // 情况五
                    intervals.put(val, val);
                }
            }
        }

        public int[][] getIntervals() {
            int size = intervals.size();
            int[][] ans = new int[size][2];
            int index = 0;
            for (Map.Entry<Integer, Integer> entry : intervals.entrySet()) {
                int left = entry.getKey(), right = entry.getValue();
                ans[index][0] = left;
                ans[index][1] = right;
                ++index;
            }
            return ans;
        }
    }
}