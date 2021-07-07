package com.moon.leetcode;

import java.util.Arrays;

// 645. 错误的集合
//
//集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有
//一个数字重复 。
//
// 给定一个数组 nums 代表了集合 S 发生错误后的结果。
//
// 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
//
// 示例 1：
//
//输入：nums = [1,2,2,4]
//输出：[2,3]
//
// 示例 2：
//
//输入：nums = [1,1]
//输出：[1,2]
//
// 提示：
//
// 2 <= nums.length <= 104
// 1 <= nums[i] <= 104
//
// Related Topics 位运算 数组 哈希表 排序
// 👍 217 👎 0
public class No645_findErrorNums {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new No645_findErrorNums().findErrorNums_v3(new int[]{1, 5, 3, 2, 2, 7, 6, 4, 8, 9})));
    }

    public int[] findErrorNums_v1(int[] nums) {
        int n = nums.length;
        int[] cnts = new int[n + 1];
        for (int x : nums) cnts[x]++;
        int[] ans = new int[2];
        for (int i = 1; i <= n; i++) {
            if (cnts[i] == 0) ans[1] = i;
            if (cnts[i] == 2) ans[0] = i;
        }
        return ans;
    }

    public int[] findErrorNums_v2(int[] nums) {
        int n = nums.length;
        int total = (1 + n) / 2;
        int sum = Arrays.stream(nums).sum();
        int sumSet = Arrays.stream(nums).distinct().sum();
        return new int[]{total - sumSet, sum - sumSet};
    }

    public int[] findErrorNums_v3(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        int a = -1, b = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                a = nums[i];
                b = i == 0 ? 1 : nums[i - 1] + 1;
            }
        }
        return new int[]{a, b};
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
