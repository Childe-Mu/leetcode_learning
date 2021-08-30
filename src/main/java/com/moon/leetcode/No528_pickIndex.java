package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * 528. 按权重随机选择
 * <p>
 * /**
 * p>给定一个正整数数组&nbsp;<code>w</code> ，其中&nbsp;<code>w[i]</code>&nbsp;代表下标 <code>i</code>&nbsp;的权重（下标从 <code>0</code> 开始），请写一个函数&nbsp;<code>pickIndex</code>&nbsp;，它可以随机地获取下标 <code>i</code>，选取下标 <code>i</code>&nbsp;的概率与&nbsp;<code>w[i]</code>&nbsp;成正比。</p>
 *
 * <ol>
 * </ol>
 *
 * <p>例如，对于 <code>w = [1, 3]</code>，挑选下标 <code>0</code> 的概率为 <code>1 / (1 + 3)&nbsp;= 0.25</code> （即，25%），而选取下标 <code>1</code> 的概率为 <code>3 / (1 + 3)&nbsp;= 0.75</code>（即，75%）。</p>
 *
 * <p>也就是说，选取下标 <code>i</code> 的概率为 <code>w[i] / sum(w)</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>
 * [&quot;Solution&quot;,&quot;pickIndex&quot;]
 * [[[1]],[]]
 * <strong>输出：</strong>
 * [null,0]
 * <strong>解释：</strong>
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>
 * [&quot;Solution&quot;,&quot;pickIndex&quot;,&quot;pickIndex&quot;,&quot;pickIndex&quot;,&quot;pickIndex&quot;,&quot;pickIndex&quot;]
 * [[[1,3]],[],[],[],[],[]]
 * <strong>输出：</strong>
 * [null,1,1,1,1,0]
 * <strong>解释：</strong>
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。
 *
 * 由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * 诸若此类。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= w.length &lt;= 10000</code></li>
 * <li><code>1 &lt;= w[i] &lt;= 10^5</code></li>
 * <li><code>pickIndex</code>&nbsp;将被调用不超过&nbsp;<code>10000</code>&nbsp;次</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数学</li><li>二分查找</li><li>前缀和</li><li>随机化</li></div></div><br><div><li>👍 164</li><li>👎 0</li></div>
 */
public class No528_pickIndex {
    public static void main(String[] args) {
        Solution solution = new Solution(new int[]{1, 3});
        int[] cnt = new int[2];
        for (int i = 0; i < 1000; i++) {
            cnt[solution.pickIndex()]++;
        }
        System.out.println(Arrays.toString(cnt));
    }

    static class Solution {
        int sum = 0;
        Random random;
        TreeMap<Integer, Integer> map;

        public Solution(int[] w) {
            random = new Random();
            map = new TreeMap<>();
            for (int i = 0; i < w.length; i++) {
                sum += w[i];
                map.put(sum, i);
            }
        }

        public int pickIndex() {
            int r = random.nextInt(sum) + 1;
            Map.Entry<Integer, Integer> entry = map.ceilingEntry(r);
            return entry.getValue();
        }
    }

    class Solution_v2 {
        int[] sum;
        Random random;

        public Solution_v2(int[] w) {
            random = new Random();
            int n = w.length;
            sum = new int[n + 1];
            for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + w[i - 1];
        }

        public int pickIndex() {
            int n = sum.length;
            int t = random.nextInt(sum[n - 1]) + 1;
            int l = 1, r = n - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (sum[mid] >= t) r = mid;
                else l = mid + 1;
            }
            return r - 1;
        }
    }

    class Solution_v3 {
        // 桶编号 / 桶内编号 / 总数
        int bid, iid, tot;
        List<int[]> list = new ArrayList<>();

        public Solution_v3(int[] w) {
            int n = w.length;
            double sum = 0, min = 1e9;
            for (int i : w) {
                sum += i;
                min = Math.min(min, i);
            }
            double minv = min / sum;
            int k = 1;
            while (minv * k < 1) k *= 10;
            for (int i = 0; i < n; i++) {
                int cnt = (int) (w[i] / sum * k);
                list.add(new int[]{i, cnt});
                tot += cnt;
            }
        }

        public int pickIndex() {
            if (bid >= list.size()) {
                bid = 0;
                iid = 0;
            }
            int[] info = list.get(bid);
            int id = info[0], cnt = info[1];
            if (iid >= cnt) {
                bid++;
                iid = 0;
                return pickIndex();
            }
            iid++;
            return id;
        }
    }
}
