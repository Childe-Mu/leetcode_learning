package com.moon.leetcode;

import java.util.Arrays;

// 1833. 雪糕的最大数量
//
//夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
//
// 商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有 coin
//s 现金可以用于消费，他想要买尽可能多的雪糕。
//
// 给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
//
// 注意：Tony 可以按任意顺序购买雪糕。
//
// 示例 1：
//
// 输入：costs = [1,3,2,4,1], coins = 7
//输出：4
//解释：Tony 可以买下标为 0、1、2、4 的雪糕，总价为 1 + 3 + 2 + 1 = 7
//
// 示例 2：
//
// 输入：costs = [10,6,8,7,7,8], coins = 5
//输出：0
//解释：Tony 没有足够的钱买任何一支雪糕。
//
// 示例 3：
//
// 输入：costs = [1,6,3,1,2,5], coins = 20
//输出：6
//解释：Tony 可以买下所有的雪糕，总价为 1 + 6 + 3 + 1 + 2 + 5 = 18 。
//
// 提示：
//
// costs.length == n
// 1 <= n <= 105
// 1 <= costs[i] <= 105
// 1 <= coins <= 108
//
// Related Topics 贪心 数组 排序
// 👍 64 👎 0
public class No1833_maxIceCream {
    public static void main(String[] args) {
        System.out.println(new No1833_maxIceCream().maxIceCream_v2(new int[]{1, 3, 2, 4, 1}, 7));
    }

    public int maxIceCream_v1(int[] costs, int coins) {
        Arrays.sort(costs);
        int cnt = 0;
        for (int cost : costs) {
            if (coins - cost < 0) {
                break;
            }
            coins -= cost;
            cnt++;
        }
        return cnt;
    }

    public int maxIceCream_v2(int[] costs, int coins) {
        int[] cnt = new int[(int) 1e5 + 10];
        for (int cost : costs) {
            cnt[cost]++;
        }
        int ans = 0;
        for (int i = 1; i < (int) 1e5 + 10; i++) {
            int cost = cnt[i] * i;
            if (coins - cost < 0) {
                for (int j = 0; j < cnt[i]; j++) {
                    if (coins - i < 0) {
                        break;
                    }
                    coins -= i;
                    ans++;
                }
                break;
            }
            coins -= cost;
            ans += cnt[i];
        }
        return ans;
    }

    public int maxIceCream_v3(int[] costs, int coins) {
        int[] freq = new int[100001];
        for (int cost : costs) {
            freq[cost]++;
        }
        int count = 0;
        for (int i = 1; i <= 100000; i++) {
            if (coins >= i) {
                int curCount = Math.min(freq[i], coins / i);
                count += curCount;
                coins -= i * curCount;
            } else {
                break;
            }
        }
        return count;
    }
}
