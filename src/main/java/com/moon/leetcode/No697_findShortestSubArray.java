package com.moon.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 697. 数组的度
 * <p>
 * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 * <p>
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1, 2, 2, 3, 1]
 * 输出：2
 * 解释：
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 * <p>
 * 示例 2：
 * <p>
 * 输入：[1,2,2,3,1,4,2]
 * 输出：6
 * <p>
 * 提示：
 * <p>
 * nums.length 在1到 50,000 区间范围内。
 * nums[i] 是一个在 0 到 49,999 范围内的整数。
 */
public class No697_findShortestSubArray {
    /**
     * 辅助类
     */
    public static int findShortestSubArray(int[] nums) {
        Map<Integer, Helper> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                Helper helper = map.get(nums[i]);
                helper.cal(i);
            } else {
                Helper helper = new Helper(i);
                map.put(nums[i], helper);
            }
        }
        Helper h = map.values().stream().sorted((o1, o2) -> o1.count == o2.count ? ((o1.rightIndex - o1.leftIndex) - (o2.rightIndex - o2.leftIndex)) : o2.count - o1.count).collect(Collectors.toList()).get(0);
        return h.rightIndex - h.leftIndex + 1;
    }

    static class Helper {
        int leftIndex;
        int rightIndex;
        int count = 0;

        public Helper(int index) {
            this.leftIndex = index;
            this.rightIndex = index;
            this.count++;
        }

        public void cal(int index) {
            rightIndex = index;
            count++;
        }
    }

    public static void main(String[] args) {
        System.out.println(findShortestSubArray(new int[]{1,2,2,3,1,4,2}));
    }
}
