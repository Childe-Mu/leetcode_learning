package com.moon.leetcode;


// 413. 等差数列划分
//
//如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
//
//
// 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
//
//
//
//
// 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
//
// 子数组 是数组中的一个连续序列。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3,4]
//输出：3
//解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
//
//
// 示例 2：
//
//
//输入：nums = [1]
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 5000
// -1000 <= nums[i] <= 1000
//
//
//
// Related Topics 数组 动态规划
// 👍 287 👎 0
public class No413_numberOfArithmeticSlices {
    public static void main(String[] args) {
        System.out.println(new No413_numberOfArithmeticSlices().numberOfArithmeticSlices_v1(new int[]{1, 2, 3, 4, 5, 2, 3, 4}));
    }

    public int numberOfArithmeticSlices_v1(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        int i = 2;
        int ans = 0;
        while (i < n) {
            int count = 0;
            while (i < n && nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                i++;
                count++;
            }
            i++;
            ans += (count + 1) * count / 2;
        }
        return ans;
    }

    public int numberOfArithmeticSlices_v2(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }

        int d = nums[0] - nums[1], t = 0;
        int ans = 0;
        // 因为等差数列的长度至少为 3，所以可以从 i=2 开始枚举
        for (int i = 2; i < n; ++i) {
            if (nums[i - 1] - nums[i] == d) {
                ++t;
            } else {
                d = nums[i - 1] - nums[i];
                t = 0;
            }
            ans += t;
        }
        return ans;
    }
}
