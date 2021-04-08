package com.moon.leetcode;

import java.util.HashMap;
import java.util.Map;

// 1814. 统计一个数组中好对子的数目
//
//给你一个数组 nums ，数组中只包含非负整数。定义 rev(x) 的值为将整数 x 各个数字位反转得到的结果。比方说 rev(123) = 321 ， r
//ev(120) = 21 。我们称满足下面条件的下标对 (i, j) 是 好的 ：
//
// 0 <= i < j < nums.length
// nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
//
// 请你返回好下标对的数目。由于结果可能会很大，请将结果对 109 + 7 取余 后返回。
//
// 示例 1：
//
// 输入：nums = [42,11,1,97]
//输出：2
//解释：两个坐标对为：
// - (0,3)：42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121 。
// - (1,2)：11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12 。
//
// 示例 2：
//
// 输入：nums = [13,10,35,24,76]
//输出：4
//
// 提示：
//
// 1 <= nums.length <= 105
// 0 <= nums[i] <= 109
//
// Related Topics 数组 哈希表
// 👍 10 👎 0
public class No1814_countNicePairs {
    public static void main(String[] args) {
    }

    public int countNicePairs(int[] nums) {
        int maxn = (int) 1e9 + 7;
        Map<Long, Integer> map = new HashMap<>();
        long ans = 0;
        for (int num : nums) {
            long x = rev(num);
            int n = map.getOrDefault(x - num, 0);
            ans = n + ans % maxn;
//            ans += n;
            map.put(x - num, n + 1);
        }
        return (int) ans % maxn;
    }

    long rev(int n) {
        long re = 0;
        while (n > 0) {
            re = re * 10 + n % 10;
            n /= 10;
        }
        return re;

    }
}
