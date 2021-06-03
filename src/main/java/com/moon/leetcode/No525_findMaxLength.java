package com.moon.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 525. 连续数组
//
//给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
//
// 示例 1:
//
//输入: nums = [0,1]
//输出: 2
//说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
//
// 示例 2:
//
//输入: nums = [0,1,0]
//输出: 2
//说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
//
// 提示：
//
// 1 <= nums.length <= 105
// nums[i] 不是 0 就是 1
//
// Related Topics 哈希表
// 👍 354 👎 0
public class No525_findMaxLength {
    public static void main(String[] args) {
        System.out.println(new No525_findMaxLength().findMaxLength_v2(new int[]{0, 0, 0, 1, 1, 1, 0}));
    }

    public int findMaxLength_v1(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            int dif = i - 2 * sum[i];
            if (!map.containsKey(dif)) {
                map.put(dif, i);
            }
            if (dif == 0) {
                ans = i;
            } else if (map.containsKey(dif)) {
                ans = Math.max(ans, i - map.get(dif));
            }
        }
        return ans;
    }

    public int findMaxLength_v2(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            sum += nums[i - 1];
            int dif = i - 2 * sum;
            map.putIfAbsent(dif, i);
            if (dif == 0) {
                ans = i;
            } else if (map.containsKey(dif)) {
                ans = Math.max(ans, i - map.get(dif));
            }
        }
        return ans;
    }

    /**
     * 用数字代替hash，卡常数
     */
    public int findMaxLength_v3(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + (nums[i - 1] == 1 ? 1 : -1);
        int ans = 0;
        int[] hash = new int[2 * n + 1];
        Arrays.fill(hash, -1);
        hash[n] = 0;
        for (int i = 2; i <= n; i++) {
            int index = sum[i - 2] + n;
            if (hash[index] == -1) {
                hash[index] = i - 2;
            }
            if (hash[sum[i] + n] != -1) {
                ans = Math.max(ans, i - hash[sum[i] + n]);
            }
        }
        return ans;
    }

    /**
     * 有问题，不能滑窗   例如 new int[]{0, 0, 0, 1, 1, 1, 0}
     */
    public int findMaxLength_v4(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int l = 0;
        int r = 2;
        while (r <= n) {
            int one = sum[r];
            int zero = r - l - one;
            if (one == zero) {
                ans = Math.max(ans, r - l);
                r += 2;
            } else {
                r++;
                l++;
            }
        }
        return ans;
    }
}