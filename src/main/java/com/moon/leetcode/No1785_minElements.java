package com.moon.leetcode;

/**
 * 1785. 构成特定和需要添加的最少元素
 * <p>
 * 给你一个整数数组 nums ，和两个整数 limit 与 goal 。数组 nums 有一条重要属性：abs(nums[i]) <= limit 。
 * <p>
 * 返回使数组元素总和等于 goal 所需要向数组中添加的 最少元素数量 ，添加元素 不应改变 数组中 abs(nums[i]) <= limit 这一属性。
 * <p>
 * 注意，如果 x >= 0 ，那么 abs(x) 等于 x ；否则，等于 -x 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,-1,1], limit = 3, goal = -4
 * 输出：2
 * 解释：可以将 -2 和 -3 添加到数组中，数组的元素总和变为 1 - 1 + 1 - 2 - 3 = -4 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1,-10,9,1], limit = 100, goal = 0
 * 输出：1
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= limit <= 106
 * -limit <= nums[i] <= limit
 * -109 <= goal <= 109
 */
public class No1785_minElements {
    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        long diff = Math.abs((long)goal - sum);
        return (int)(diff % limit == 0 ? diff / limit : diff / limit + 1);
    }

    public int minElements_v2(int[] nums, int limit, int goal) {
        int no = 0;
        int sl = 0;
        for (int num : nums) {
            no += num / limit;
            sl += num % limit;
        }
        no += sl / limit;
        sl = sl % limit;
        int gl = goal % limit;
        int gno = goal / limit;
        int a = 0;
        if (gno - no == 0) {
            if (gl - sl != 0) {
                a = 1;
            }
        } else if (gno - no > 0) {
            if (gl > sl) {
                a = 1;
            }
        } else if (gno - no < 0) {
            if (gl < sl) {
                a = 1;
            }
        }
        return Math.abs(gno - no) + a;
    }

    public static void main(String[] args) {
        System.out.println(-12 % 5);
    }
}
