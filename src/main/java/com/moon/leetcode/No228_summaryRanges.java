package com.moon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 228. 汇总区间
 * <p>
 * 给定一个无重复元素的有序整数数组 nums 。
 * <p>
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 num
 * s 的数字 x 。
 * <p>
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * <p>
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [0,1,2,4,5,7]
 * 输出：["0->2","4->5","7"]
 * 解释：区间范围是：
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * <p>
 * 示例 2：
 * 输入：nums = [0,2,3,4,6,8,9]
 * 输出：["0","2->4","6","8->9"]
 * 解释：区间范围是：
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = []
 * 输出：[]
 * <p>
 * 示例 4：
 * <p>
 * 输入：nums = [-1]
 * 输出：["-1"]
 * <p>
 * 示例 5：
 * <p>
 * 输入：nums = [0]
 * 输出：["0"]
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 20
 * -231 <= nums[i] <= 231 - 1
 * nums 中的所有值都 互不相同
 * nums 按升序排列
 */
public class No228_summaryRanges {
    public static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        if (nums.length == 1) {
            res.add(String.valueOf(nums[0]));
            return res;
        }
        int start = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                if (start == nums[i - 1]) {
                    res.add(String.valueOf(start));
                } else {
                    res.add(start + "->" + nums[i - 1]);
                }
                start = nums[i];
            }
        }
        if (start == nums[nums.length - 1]) {
            res.add(String.valueOf(start));
        } else {
            res.add(start + "->" + nums[nums.length - 1]);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9}));
    }

    public List<String> summaryRanges_V2(int[] nums) {
        List<String> ret = new ArrayList<>();
        int i = 0;
        int n = nums.length;
        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int high = i - 1;
            StringBuilder temp = new StringBuilder(nums[low]);
            if (low < high) {
                temp.append("->");
                temp.append(nums[high]);
            }
            ret.add(temp.toString());
        }
        return ret;
    }
}
