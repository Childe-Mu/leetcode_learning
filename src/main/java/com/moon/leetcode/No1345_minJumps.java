package com.moon.leetcode;

import java.util.*;

/**
 * 1345.跳跃游戏 IV
 * <p>给你一个整数数组&nbsp;<code>arr</code>&nbsp;，你一开始在数组的第一个元素处（下标为 0）。</p>
 *
 * <p>每一步，你可以从下标&nbsp;<code>i</code>&nbsp;跳到下标：</p>
 *
 * <ul>
 * <li><code>i + 1</code>&nbsp;满足：<code>i + 1 &lt; arr.length</code></li>
 * <li><code>i - 1</code>&nbsp;满足：<code>i - 1 &gt;= 0</code></li>
 * <li><code>j</code>&nbsp;满足：<code>arr[i] == arr[j]</code>&nbsp;且&nbsp;<code>i != j</code></li>
 * </ul>
 *
 * <p>请你返回到达数组最后一个元素的下标处所需的&nbsp;<strong>最少操作次数</strong>&nbsp;。</p>
 *
 * <p>注意：任何时候你都不能跳到数组外面。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>arr = [100,-23,-23,404,100,23,23,23,3,404]
 * <strong>输出：</strong>3
 * <strong>解释：</strong>那你需要跳跃 3 次，下标依次为 0 --&gt; 4 --&gt; 3 --&gt; 9 。下标 9 为数组的最后一个元素的下标。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>arr = [7]
 * <strong>输出：</strong>0
 * <strong>解释：</strong>一开始就在最后一个元素处，所以你不需要跳跃。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre><strong>输入：</strong>arr = [7,6,9,6,9,6,9,7]
 * <strong>输出：</strong>1
 * <strong>解释：</strong>你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre><strong>输入：</strong>arr = [6,1,9]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 5：</strong></p>
 *
 * <pre><strong>输入：</strong>arr = [11,22,7,7,7,7,7,7,7,22,13]
 * <strong>输出：</strong>3
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= arr.length &lt;= 5 * 10^4</code></li>
 * <li><code>-10^8 &lt;= arr[i] &lt;= 10^8</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>广度优先搜索</li><li>数组</li><li>哈希表</li></div></div><br><div><li>👍 170</li><li>👎 0</li></div>
 */
public class No1345_minJumps {
    public int minJumps_v1(int[] arr) {
        int n = arr.length;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], o -> new ArrayList<>()).add(i);
        }
        Deque<Integer> queue = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        queue.offer(0);
        set.add(0);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int p = queue.poll();
                if (p == n - 1) {
                    return ans;
                }
                if (!set.contains(p + 1)) {
                    queue.offer(p + 1);
                    set.add(p + 1);
                }
                if (p - 1 > 0 && !set.contains(p - 1)) {
                    queue.offer(p - 1);
                    set.add(p - 1);
                }
                if (map.containsKey(arr[p])) {
                    for (Integer j : map.get(arr[p])) {
                        if (!set.contains(j)) {
                            queue.offer(j);
                            set.add(j);
                        }
                    }
                }
                map.remove(arr[p]);
            }
            ans++;
        }
        return ans;
    }

    public int minJumps_v2(int[] arr) {
        int n = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], o -> new ArrayList<>()).add(i);
        }
        Set<Integer> set = new HashSet<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        set.add(0);
        while (!queue.isEmpty()) {
            int[] idxStep = queue.poll();
            int idx = idxStep[0], step = idxStep[1];
            if (idx == n - 1) {
                return step;
            }
            int v = arr[idx];
            step++;
            if (map.containsKey(v)) {
                for (int i : map.get(v)) {
                    if (set.add(i)) {
                        queue.offer(new int[]{i, step});
                    }
                }
                map.remove(v);
            }
            if (idx + 1 < n && set.add(idx + 1)) {
                queue.offer(new int[]{idx + 1, step});
            }
            if (idx - 1 >= 0 && set.add(idx - 1)) {
                queue.offer(new int[]{idx - 1, step});
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new No1345_minJumps().minJumps_v1(new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404}));
    }
}
