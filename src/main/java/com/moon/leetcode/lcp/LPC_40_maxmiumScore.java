package com.moon.leetcode.lcp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * LCP 40. 心算挑战
 * <p>
 * /**
 * 「力扣挑战赛」心算项目的挑战比赛中，要求选手从 `N` 张卡牌中选出 `cnt` 张卡牌，若这 `cnt` 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 `cnt` 张卡牌数字总和。
 * 给定数组 `cards` 和 `cnt`，其中 `cards[i]` 表示第 `i` 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。
 * <p>
 * *示例 1：**
 * >输入：`cards = [1,2,8,9], cnt = 3`
 * >
 * >输出：`18`
 * >
 * >解释：选择数字为 1、8、9 的这三张卡牌，此时可获得最大的有效得分 1+8+9=18。
 * <p>
 * *示例 2：**
 * >输入：`cards = [3,3,1], cnt = 1`
 * >
 * >输出：`0`
 * >
 * >解释：不存在获取有效得分的卡牌方案。
 * <p>
 * *提示：**
 * - `1 <= cnt <= cards.length <= 10^5`
 * - `1 <= cards[i] <= 1000`
 */
public class LPC_40_maxmiumScore {
    public static void main(String[] args) {
        System.out.println(new LPC_40_maxmiumScore().maxmiumScore(new int[]{1, 2, 8, 9}, 3));
    }

    public int maxmiumScore(int[] cards, int cnt) {
        Map<Integer, List<Integer>> map = Arrays.stream(cards).boxed().sorted((a, b) -> b - a).collect(Collectors.groupingBy(p -> p & 1));
        int oddSize = map.getOrDefault(1, new ArrayList<>()).size();
        int evenSize = map.getOrDefault(0, new ArrayList<>()).size();
        int[] oddSum = new int[oddSize + 1];
        int[] evenSum = new int[evenSize + 1];
        for (int i = 0; i < oddSize; i++) {
            oddSum[i + 1] = oddSum[i] + map.get(1).get(i);
        }
        for (int i = 0; i < evenSize; i++) {
            evenSum[i + 1] = evenSum[i] + map.get(0).get(i);
        }
        int ans = 0;
        for (int i = 0; i <= oddSize; i += 2) {
            int j = cnt - i;
            if (0 <= j && j <= evenSize) {
                ans = Math.max(oddSum[i] + evenSum[j], ans);
            }
        }
        return ans;
    }
}
