package com.moon.leetcode;

import java.util.Arrays;

/**
 * 1109. 航班预订统计
 * <p>
 * /**
 * <p>这里有 <code>n</code> 个航班，它们分别从 <code>1</code> 到 <code>n</code> 进行编号。</p>
 *
 * <p>有一份航班预订表 <code>bookings</code> ，表中第 <code>i</code> 条预订记录 <code>bookings[i] = [first<sub>i</sub>, last<sub>i</sub>, seats<sub>i</sub>]</code> 意味着在从 <code>first<sub>i</sub></code> 到 <code>last<sub>i</sub></code> （<strong>包含</strong> <code>first<sub>i</sub></code> 和 <code>last<sub>i</sub></code> ）的 <strong>每个航班</strong> 上预订了 <code>seats<sub>i</sub></code> 个座位。</p>
 *
 * <p>请你返回一个长度为 <code>n</code> 的数组 <code>answer</code>，其中 <code>answer[i]</code> 是航班 <code>i</code> 上预订的座位总数。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * <strong>输出：</strong>[10,55,45,25,25]
 * <strong>解释：</strong>
 * 航班编号        1   2   3   4   5
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       20  20
 * 预订记录 3 ：       25  25  25  25
 * 总座位数：      10  55  45  25  25
 * 因此，answer = [10,55,45,25,25]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>bookings = [[1,2,10],[2,2,15]], n = 2
 * <strong>输出：</strong>[10,25]
 * <strong>解释：</strong>
 * 航班编号        1   2
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       15
 * 总座位数：      10  25
 * 因此，answer = [10,25]
 * </pre>
 * <p>
 * p> </p>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= n <= 2 * 10<sup>4</sup></code></li>
 * <li><code>1 <= bookings.length <= 2 * 10<sup>4</sup></code></li>
 * <li><code>bookings[i].length == 3</code></li>
 * <li><code>1 <= first<sub>i</sub> <= last<sub>i</sub> <= n</code></li>
 * <li><code>1 <= seats<sub>i</sub> <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>前缀和</li></div></div><br><div><li>👍 218</li><li>👎 0</li></div>
 */
public class No1109_corpFlightBookings {
    int N = 20009;
    Node[] tr = new Node[N * 4];

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new No1109_corpFlightBookings().corpFlightBookings_v2(new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}}, 5)));
    }

    public int[] corpFlightBookings_v1(int[][] bookings, int n) {
        int[] f = new int[n + 1];
        for (int[] booking : bookings) {
            f[booking[0] - 1] += booking[2];
            f[booking[1]] -= booking[2];
        }
        int[] ans = new int[n];
        ans[0] = f[0];
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] + f[i];
        }
        return ans;
    }

    void pushUp(int u) {
        tr[u].v = tr[u << 1].v + tr[u << 1 | 1].v;
    }

    void pushDown(int u) {
        int add = tr[u].add;
        tr[u << 1].v += add;
        tr[u << 1].add += add;
        tr[u << 1 | 1].v += add;
        tr[u << 1 | 1].add += add;
        tr[u].add = 0;
    }

    void build(int u, int l, int r) {
        tr[u] = new Node(l, r);
        if (l != r) {
            int mid = l + r >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
        }
    }

    void update(int u, int l, int r, int v) {
        if (l <= tr[u].l && tr[u].r <= r) {
            tr[u].v += v;
            tr[u].add += v;
        } else {
            pushDown(u);
            int mid = tr[u].l + tr[u].r >> 1;
            if (l <= mid) update(u << 1, l, r, v);
            if (r > mid) update(u << 1 | 1, l, r, v);
            pushUp(u);
        }
    }

    int query(int u, int l, int r) {
        if (l <= tr[u].l && tr[u].r <= r) {
            return tr[u].v;
        } else {
            pushDown(u);
            int mid = tr[u].l + tr[u].r >> 1;
            int ans = 0;
            if (l <= mid) ans += query(u << 1, l, r);
            if (r > mid) ans += query(u << 1 | 1, l, r);
            return ans;
        }
    }

    public int[] corpFlightBookings_v2(int[][] bs, int n) {
        build(1, 1, n);
        for (int[] bo : bs) {
            update(1, bo[0], bo[1], bo[2]);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = query(1, i + 1, i + 1);
        }
        return ans;
    }

    private static class Node {
        int l, r, v, add;

        Node(int _l, int _r) {
            l = _l;
            r = _r;
        }
    }
}
