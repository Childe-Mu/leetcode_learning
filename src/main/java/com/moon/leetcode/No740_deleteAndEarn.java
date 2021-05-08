package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 740. 删除并获得点数
//
//给你一个整数数组 nums ，你可以对它进行一些操作。
//
// 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i]
// + 1 的元素。
//
// 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
//
// 示例 1：
//
//
//输入：nums = [3,4,2]
//输出：6
//解释：
//删除 4 获得 4 个点数，因此 3 也被删除。
//之后，删除 2 获得 2 个点数。总共获得 6 个点数。
//
// 示例 2：
//
//输入：nums = [2,2,3,3,3,4]
//输出：9
//解释：
//删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
//之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
//总共获得 9 个点数。
//
// 提示：
//
// 1 <= nums.length <= 2 * 104
// 1 <= nums[i] <= 104
//
// Related Topics 动态规划
// 👍 336 👎 0
public class No740_deleteAndEarn {
    public static void main(String[] args) {
        System.out.println(new No740_deleteAndEarn().deleteAndEarn_v2(new int[]{2, 2, 3, 3, 3, 4, 6}));
    }

    public int deleteAndEarn_v1(int[] nums) {
        int maxVal = 0;
        for (int val : nums) {
            maxVal = Math.max(maxVal, val);
        }
        int[] sums = new int[maxVal + 1];
        for (int num : nums) {
            sums[num] += num;
        }
        return rob(sums);
    }

    private int rob(int[] sums) {
        int n = sums.length;
        int[] f = new int[n];
        f[0] = sums[0];
        f[1] = Math.max(sums[0], sums[1]);
        for (int i = 2; i < n; i++) {
            f[i] = Math.max(f[i - 1], f[i - 2] + sums[i]);
        }
        return f[n - 1];
    }

    public int deleteAndEarn_v2(int[] nums) {
        int n = nums.length;
        int max = 0;
        Arrays.sort(nums);
        List<Integer> sums = new ArrayList<>();
        sums.add(0);
        sums.add(nums[0]);
        for (int i = 1; i < n; i++) {
            int pre = nums[i - 1];
            int cur = nums[i];
            int size = sums.size();
            if (pre == cur) {
                sums.set(size - 1, sums.get(size - 1) + cur);
            } else if (cur - pre == 1) {
                sums.add(cur);
            } else {
                max += rob(sums);
                sums.clear();
                sums.add(0);
                sums.add(cur);
            }
        }
        return max + rob(sums);
    }

    private int rob(List<Integer> sums) {
        int n = sums.size();
        int pre = sums.get(0);
        int cur = Math.max(sums.get(0), sums.get(1));
        for (int i = 2; i < n; i++) {
            int tmp = Math.max(pre + sums.get(i), cur);
            pre = cur;
            cur = tmp;
        }
        return cur;
    }
}
