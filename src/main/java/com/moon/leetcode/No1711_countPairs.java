package com.moon.leetcode;

import java.util.HashMap;
import java.util.Map;

// 1711. 大餐计数
//
//大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
//
// 你可以搭配 任意 两道餐品做一顿大餐。
//
// 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大
//餐 的数量。结果需要对 109 + 7 取余。
//
// 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
//
//
//
// 示例 1：
//
//
//输入：deliciousness = [1,3,5,7,9]
//输出：4
//解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
//它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
//
//
// 示例 2：
//
//
//输入：deliciousness = [1,1,1,3,3,3,7]
//输出：15
//解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。
//
//
//
// 提示：
//
//
// 1 <= deliciousness.length <= 105
// 0 <= deliciousness[i] <= 220
//
// Related Topics 数组 哈希表
// 👍 57 👎 0
public class No1711_countPairs {
    public int countPairs_v1(int[] ds) {
        long ans = 0;
        int mod = (int) 1e9 + 7;
        for (int i = 0; i <= 21; i++) {
            int target = 1 << i;
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int k : ds) {
                int t = cnt.getOrDefault(k, 0);
                ans += t;
                cnt.put(target - k, cnt.getOrDefault(target - k, 0) + 1);
            }
        }
        return (int) (ans % mod);
    }

    public int countPairs_v2(int[] ds) {
        int mod = (int) 1e9 + 7;
        int max = 1 << 22;
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int x : ds) {
            for (int i = 1; i < max; i <<= 1) {
                int t = i - x;
                if (map.containsKey(t)) {
                    ans += map.get(t);
                    if (ans >= mod) ans -= mod;
                }
            }
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        return ans;
    }
}
