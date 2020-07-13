package com.moon.leetcode;

/**
 * 53. 最大子序和<br/>
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。<br/>
 * <br/>
 * 示例:<br/>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],<br/>
 * 输出: 6<br/>
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。<br/>
 * <br/>
 * 进阶:<br/>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。<br/>
 */
public class No53_maxSubArray {
    // /**
    //  * 动态规划
    //  * 思路，只有加上大于零的值，和才会变大，所以过程中抛弃掉小于0的值
    //  * 加的时候，是前面的和 + 当前值。所以前面的和小于0就丢弃
    //  */
    // public static int maxSubArray(int[] nums) {
    //     int pre = 0, maxAns = nums[0];
    //     for (int num : nums) {
    //         if(pre < 0) {
    //             pre = num;
    //         } else {
    //             pre += num;
    //
    //         }
    //         maxAns = Math.max(pre, maxAns);
    //     }
    //     return maxAns;
    // }

    /**
     * 更精炼
     */
    public static int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            maxAns = Math.max(pre, maxAns);
        }
        return maxAns;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
