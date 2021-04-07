package com.moon.leetcode;

// 33. 搜索旋转排序数组
//
//整数数组 nums 按升序排列，数组中的值 互不相同 。
//
// 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[
//k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2
//,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
//
// 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
//
// 示例 1：
//
//输入：nums = [4,5,6,7,0,1,2], target = 0
//输出：4
//
// 示例 2：
//
//输入：nums = [4,5,6,7,0,1,2], target = 3
//输出：-1
//
// 示例 3：
//
//输入：nums = [1], target = 0
//输出：-1
//
// 提示：
//
// 1 <= nums.length <= 5000
// -10^4 <= nums[i] <= 10^4
// nums 中的每个值都 独一无二
// 题目数据保证 nums 在预先未知的某个下标上进行了旋转
// -10^4 <= target <= 10^4
//
// 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？
// Related Topics 数组 二分查找
// 👍 1297 👎 0
public class No33_search {
    public static void main(String[] args) {
        System.out.println(new No33_search().search_v1(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    }

    /**
     * 枚举所有的情况
     */
    public int search_v1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            int l = nums[left];
            int r = nums[right];
            int m = nums[mid];
            if (target == m) {
                return mid;
            }
            if (target >= l && target < m) {
                right = mid - 1;
            } else if (l > m && (target >= l || target < m)) {
                right = mid - 1;
            } else if (target <= r && target > m) {
                left = mid + 1;
            } else if (m > r && (target >= r || target < m)) {
                left = mid + 1;
            } else {
                return -1;
            }
        }
        return -1;
    }

    /**
     * 在常规二分查找的时候查看当前 mid 为分割位置分割出来的两个部分 [l, mid] 和 [mid + 1, r] 哪个部分是有序的，并根据有序的那个部分确定我们该如何改变二分查找的上下界，
     * 因为我们能够根据有序的那部分判断出 target 在不在这个部分：
     * <p>
     * 如果 [l, mid - 1] 是有序数组，且 target 的大小满足 textitnumsltextitnumsmid  ，则我们应该将搜索范围缩小至 [l, mid - 1]，否则在 [mid + 1, r] 中寻找。
     * 如果 [mid, r] 是有序数组，且 target 的大小满足 textitnumsmid1textitnumsr  ，则我们应该将搜索范围缩小至 [mid + 1, r]，否则在 [l, mid - 1] 中寻找。
     */
    public int search_v2(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
