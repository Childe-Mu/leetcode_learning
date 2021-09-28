package com.moon.leetcode.offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 剑指 Offer 57 - II. 和为s的连续正数序列
//
//输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
//
// 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
//
// 示例 1：
//
// 输入：target = 9
//输出：[[2,3,4],[4,5]]
//
//
// 示例 2：
//
// 输入：target = 15
//输出：[[1,2,3,4,5],[4,5,6],[7,8]]
//
// 限制：
//
// 1 <= target <= 10^5
//
// 👍 253 👎 0
public class Offer_57_2_findContinuousSequence {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Offer_57_2_findContinuousSequence().findContinuousSequence(6)));
    }

    public int[][] findContinuousSequence_v1(int target) {
        List<int[]> res = new ArrayList<>();
        for (int l = 1, r = 2; l < r; ) {
            int sum = (l + r) * (r - l + 1) / 2;
            if (sum == target) {
                int[] temp = new int[r - l + 1];
                for (int i = l; i <= r; ++i) {
                    temp[i - l] = i;
                }
                res.add(temp);
                l++;
            } else if (sum > target) {
                l++;
            } else {
                r++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    /**
     * y^2 + y − (x^2 + x − 2×target) = 0
     */
    public int[][] findContinuousSequence(int target) {
        List<int[]> res = new ArrayList<>();
        // (target - 1) / 2 等效于 target / 2 下取整
        int limit = (target - 1) / 2;
        for (int x = 1; x <= limit; ++x) {
            long delta = 1 - 4 * (x - (long) x * x - 2L * target);
            if (delta < 0) {
                continue;
            }
            int delta_sqrt = (int) Math.sqrt(delta);
            if ((long) delta_sqrt * delta_sqrt == delta && (delta_sqrt - 1) % 2 == 0) {
                // 另一个解(-1-delta_sqrt)/2必然小于0，不用考虑
                int y = (-1 + delta_sqrt) / 2;
                if (x < y) {
                    int[] tmp = new int[y - x + 1];
                    for (int i = x; i <= y; ++i) {
                        tmp[i - x] = i;
                    }
                    res.add(tmp);
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
