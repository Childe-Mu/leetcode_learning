package com.moon.leetcode;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * 638. 大礼包
 * <p>
 * /**
 * <p>在 LeetCode 商店中， 有 <code>n</code> 件在售的物品。每件物品都有对应的价格。然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。</p>
 *
 * <p>给你一个整数数组 <code>price</code> 表示物品价格，其中 <code>price[i]</code> 是第 <code>i</code> 件物品的价格。另有一个整数数组 <code>needs</code> 表示购物清单，其中 <code>needs[i]</code> 是需要购买第 <code>i</code> 件物品的数量。</p>
 *
 * <p>还有一个数组 <code>special</code> 表示大礼包，<code>special[i]</code> 的长度为 <code>n + 1</code> ，其中 <code>special[i][j]</code> 表示第 <code>i</code> 个大礼包中内含第 <code>j</code> 件物品的数量，且 <code>special[i][n]</code> （也就是数组中的最后一个整数）为第 <code>i</code> 个大礼包的价格。</p>
 *
 * <p>返回<strong> 确切 </strong>满足购物清单所需花费的最低价格，你可以充分利用大礼包的优惠活动。你不能购买超出购物清单指定数量的物品，即使那样会降低整体价格。任意大礼包可无限次购买。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
 * <strong>输出：</strong>14
 * <strong>解释：</strong>有 A 和 B 两种物品，价格分别为 ¥2 和 ¥5 。
 * 大礼包 1 ，你可以以 ¥5 的价格购买 3A 和 0B 。
 * 大礼包 2 ，你可以以 ¥10 的价格购买 1A 和 2B 。
 * 需要购买 3 个 A 和 2 个 B ， 所以付 ¥10 购买 1A 和 2B（大礼包 2），以及 ¥4 购买 2A 。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <p>
 * pre>
 * <strong>输入：</strong>price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1]
 * <strong>输出：</strong>11
 * <strong>解释：</strong>A ，B ，C 的价格分别为 ¥2 ，¥3 ，¥4 。
 * 可以用 ¥4 购买 1A 和 1B ，也可以用 ¥9 购买 2A ，2B 和 1C 。
 * 需要买 1A ，2B 和 1C ，所以付 ¥4 买 1A 和 1B（大礼包 1），以及 ¥3 购买 1B ， ¥4 购买 1C 。
 * 不可以购买超出待购清单的物品，尽管购买大礼包 2 更加便宜。</pre>
 * <p>
 * p> </p>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>n == price.length</code></li>
 * <li><code>n == needs.length</code></li>
 * <li><code>1 <= n <= 6</code></li>
 * <li><code>0 <= price[i] <= 10</code></li>
 * <li><code>0 <= needs[i] <= 10</code></li>
 * <li><code>1 <= special.length <= 100</code></li>
 * <li><code>special[i].length == n + 1</code></li>
 * <li><code>0 <= special[i][j] <= 50</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>位运算</li><li>记忆化搜索</li><li>数组</li><li>动态规划</li><li>回溯</li><li>状态压缩</li></div></div><br><div><li>👍 288</li><li>👎 0</li></div>
 */
public class No638_shoppingOffers {
    public static void main(String[] args) {
        List<Integer> price = Lists.newArrayList(2, 3, 4);
        List<List<Integer>> special = Lists.newArrayList(Lists.newArrayList(1, 1, 0, 4), Lists.newArrayList(2, 2, 1, 9));
        List<Integer> needs = Lists.newArrayList(1, 2, 1);
        System.out.println(new No638_shoppingOffers().shoppingOffers(price, special, needs));
    }

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int n = price.size();
        int[] g = new int[n + 1];
        g[0] = 1;
        for (int i = 1; i <= n; i++) {
            g[i] = g[i - 1] * (needs.get(i - 1) + 1);
        }
        int mask = g[n];
        int[] f = new int[mask];
        int[] cnt = new int[n];
        for (int state = 1; state < mask; state++) {
            f[state] = 0x3f3f3f3f;
            Arrays.fill(cnt, 0);
            for (int i = 0; i < n; i++) {
                cnt[i] = state % g[i + 1] / g[i];
            }
            for (int i = 0; i < n; i++) {
                if (cnt[i] > 0) f[state] = Math.min(f[state], f[state - g[i]] + price.get(i));
            }
            out:
            for (List<Integer> x : special) {
                int cur = state;
                for (int i = 0; i < n; i++) {
                    if (cnt[i] < x.get(i)) {
                        continue out;
                    }
                    cur -= x.get(i) * g[i];
                }
                f[state] = Math.min(f[state], f[cur] + x.get(n));
            }
        }
        return f[mask - 1];
    }
}
