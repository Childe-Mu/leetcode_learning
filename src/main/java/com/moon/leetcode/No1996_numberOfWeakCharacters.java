package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 1996.游戏中弱角色的数量
 * <p>你正在参加一个多角色游戏，每个角色都有两个主要属性：<strong>攻击</strong> 和 <strong>防御</strong> 。给你一个二维整数数组 <code>properties</code> ，其中 <code>properties[i] = [attack<sub>i</sub>, defense<sub>i</sub>]</code> 表示游戏中第 <code>i</code> 个角色的属性。</p>
 *
 * <p>如果存在一个其他角色的攻击和防御等级 <strong>都严格高于</strong> 该角色的攻击和防御等级，则认为该角色为 <strong>弱角色</strong> 。更正式地，如果认为角色 <code>i</code> <strong>弱于</strong> 存在的另一个角色 <code>j</code> ，那么 <code>attack<sub>j</sub> &gt; attack<sub>i</sub></code> 且 <code>defense<sub>j</sub> &gt; defense<sub>i</sub></code> 。</p>
 *
 * <p>返回 <strong>弱角色</strong> 的数量。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>properties = [[5,5],[6,3],[3,6]]
 * <strong>输出：</strong>0
 * <strong>解释：</strong>不存在攻击和防御都严格高于其他角色的角色。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>properties = [[2,2],[3,3]]
 * <strong>输出：</strong>1
 * <strong>解释：</strong>第一个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>properties = [[1,5],[10,4],[4,3]]
 * <strong>输出：</strong>1
 * <strong>解释：</strong>第三个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>2 &lt;= properties.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>properties[i].length == 2</code></li>
 * <li><code>1 &lt;= attack<sub>i</sub>, defense<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>贪心</li><li>数组</li><li>排序</li><li>单调栈</li></div></div><br><div><li>👍 120</li><li>👎 0</li></div>
 */
public class No1996_numberOfWeakCharacters {
    public int numberOfWeakCharacters_v1(int[][] ps) {
        int n = ps.length, ans = 0;
        Arrays.sort(ps, (a, b) -> a[0] == b[0] ? b[1] - a[1] : b[0] - a[0]);
        int max = ps[0][1];
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && ps[j][0] == ps[i][0]) {
                if (i != 0 && ps[j][1] < max) {
                    ans++;
                }
                j++;
            }
            max = Math.max(max, ps[i][1]);
            i = j;
        }
        return ans;
    }

    public int numberOfWeakCharacters_v2(int[][] ps) {
        Arrays.sort(ps, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        int maxDef = 0;
        int ans = 0;
        for (int[] p : ps) {
            if (p[1] < maxDef) {
                ans++;
            } else {
                maxDef = p[1];
            }
        }
        return ans;
    }

    public int numberOfWeakCharacters_v3(int[][] ps) {
        Arrays.sort(ps, (o1, o2) -> o1[0] == o2[0] ? (o2[1] - o1[1]) : (o1[0] - o2[0]));
        int ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        for (int[] p : ps) {
            while (!st.isEmpty() && st.peek() < p[1]) {
                st.pop();
                ans++;
            }
            st.push(p[1]);
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new No1996_numberOfWeakCharacters().numberOfWeakCharacters_v3(new int[][]{{1, 5}, {10, 4}, {10, 2}, {4, 6}, {4, 3}}));
    }
}
