package com.moon.leetcode;

import java.util.HashMap;
import java.util.Map;

// 1049. 最后一块石头的重量 II
//
//有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
//
// 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
//
// 如果 x == y，那么两块石头都会被完全粉碎；
// 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
//
// 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
//
// 示例 1：
//
//输入：stones = [2,7,4,1,8,1]
//输出：1
//解释：
//组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
//组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
//组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
//组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
//
// 示例 2：
//
//输入：stones = [31,26,33,21,40]
//输出：5
//
// 示例 3：
//
//输入：stones = [1,2]
//输出：1
//
// 提示：
//
// 1 <= stones.length <= 30
// 1 <= stones[i] <= 100
//
// Related Topics 动态规划
// 👍 257 👎 0
public class No1049_lastStoneWeightII {
    int[] stones;
    int n;
    Map<String, Integer> mem;

    public static void main(String[] args) {
        System.out.println(new No1049_lastStoneWeightII().lastStoneWeightII_v1(new int[]{2, 7, 4, 1, 8, 1}));
    }

    // (sum−neg)−neg=sum−2⋅neg,枚举neg
    public int lastStoneWeightII_v1(int[] stones) {
        int n = stones.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + stones[i];
        }
        int max = sum[n] / 2;
        int[][] f = new int[n + 1][max + 1];
        for (int i = 1; i <= n; i++) {
            int x = stones[i - 1];
            for (int j = 0; j <= max; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= x) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - x] + x);
                }
            }
        }
        return Math.abs(sum[n] - 2 * f[n][max]);

    }

    public int lastStoneWeightII_v2(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int max = sum / 2;
        int[] f = new int[max + 1];
        for (int i = 1; i <= n; i++) {
            int x = stones[i - 1];
            for (int j = max; j >= x; j--) {
                f[j] = Math.max(f[j], f[j - x] + x);
            }
        }
        return Math.abs(sum - 2 * f[max]);
    }

    public int lastStoneWeightII_v3(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        boolean[][] f = new boolean[n + 1][sum / 2 + 1];
        f[0][0] = true;
        for (int i = 1; i <= n; i++) {
            int x = stones[i - 1];
            for (int j = 0; j <= sum / 2; j++) {
                if (j >= x) {
                    f[i][j] = f[i - 1][j] || f[i - 1][j - x];
                } else {
                    f[i][j] = f[i - 1][j];
                }
            }
        }
        for (int i = sum / 2; i > -1; i--) {
            if (f[n][i]) {
                return sum - 2 * i;
            }
        }
        return -1;
    }

    public int lastStoneWeightII_v4(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        boolean[] f = new boolean[sum / 2 + 1];
        f[0] = true;
        for (int i = 1; i <= n; i++) {
            int x = stones[i - 1];
            for (int j = sum / 2; j >= x; j--) {
                f[j] = f[j] || f[j - x];
            }
        }
        for (int i = sum / 2; i > -1; i--) {
            if (f[i]) {
                return sum - 2 * i;
            }
        }
        return -1;
    }

    public int lastStoneWeightII_v5(int[] stones) {
        this.n = stones.length;
        this.stones = stones;
        mem = new HashMap<>();
        return dfs(0, 0, 0);
    }

    private int dfs(int i, int big1, int big2) {
        String key1 = i + '_' + big1 + "_" + big2;
        String key2 = i + '_' + big2 + "_" + big1;
        if (mem.containsKey(key1)) {
            return mem.get(key1);
        }
        if (mem.containsKey(key2)) {
            return mem.get(key1);
        }
        if (i == n) {
            int min = Math.abs(big1 - big2);
            mem.put(key1, min);
            mem.put(key2, min);
            return min;
        }
        int b1 = dfs(i + 1, big1 + stones[i], big2);
        int b2 = dfs(i + 1, big1, big2 + stones[i]);
        int min = Math.min(b1, b2);
        mem.put(key1, min);
        mem.put(key2, min);
        return min;
    }

}
