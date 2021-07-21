package com.moon.leetcode;

import java.util.Arrays;

// 1838. 最高频元素的频数
//
//元素的 频数 是该元素在一个数组中出现的次数。
//
// 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
//
// 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
//
// 示例 1：
//
//输入：nums = [1,2,4], k = 5
//输出：3
//解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
//4 是数组中最高频元素，频数是 3 。
//
// 示例 2：
//
//输入：nums = [1,4,8,13], k = 5
//输出：2
//解释：存在多种最优解决方案：
//- 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
//- 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
//- 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
//
// 示例 3：
//
//输入：nums = [3,9,6], k = 2
//输出：1
//
// 提示：
//
// 1 <= nums.length <= 105
// 1 <= nums[i] <= 105
// 1 <= k <= 105
//
// Related Topics 贪心算法
// 👍 24 👎 0
public class No1838_maxFrequency {
    public static void main(String[] args) {
        System.out.println(new No1838_maxFrequency().maxFrequency_v2(new int[]{1, 2, 4}, 5));
    }

    /**
     * 超时
     */
    public int maxFrequency_v1(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int kk = k;
            int count = 0;
            int target = nums[i];
            for (int j = i; j >= 0; j--) {
                if (kk - (target - nums[j]) < 0) {
                    break;
                }
                count++;
                kk = kk - (target - nums[j]);
            }
            max = Math.max(max, count);
        }
        return max;
    }

    public int maxFrequency_v2(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long total = 0;
        int l = 0, max = 1;
        for (int r = 1; r < n; r++) {
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= nums[r] - nums[l];
                ++l;
            }
            max = Math.max(max, r - l + 1);
        }
        return max;
    }

    public int maxFrequency_v3(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int i = 0;
        int j = 0;
        while (i < n) {
            while (i < n && nums[i] * (i + 1 - j) - (sum[i + 1] - sum[j]) <= k) {
                ans = Math.max(i - j + 1, ans);
                i++;
            }
            while (i < n && j <= i && nums[i] * (i + 1 - j) - (sum[i + 1] - sum[j]) > k) {
                j++;
            }
        }
        return ans;
    }

    public int maxFrequency_v4(int[] nums, int k) {
        int[] count = new int[100001];
        for (int num : nums) {
            count[num]++;
        }
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                nums[index++] = i;
                count[i]--;
            }
        }
        int n = nums.length;
        long total = 0;
        int l = 0, res = 1;
        for (int r = 1; r < n; ++r) {
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= nums[r] - nums[l];
                ++l;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }


}
