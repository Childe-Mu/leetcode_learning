package com.moon.leetcode;

import java.util.Arrays;
import java.util.stream.Collectors;

// 179. 最大数
//
//给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
//
// 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
//
// 示例 1：
//
//输入：nums = [10,2]
//输出："210"
//
// 示例 2：
//
//输入：nums = [3,30,34,5,9]
//输出："9534330"
//
// 示例 3：
//
//输入：nums = [1]
//输出："1"
//
// 示例 4：
//
//输入：nums = [10]
//输出："10"
//
// 提示：
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 109
//
// Related Topics 排序
// 👍 547 👎 0
public class No179_largestNumber {
    public static void main(String[] args) {
        System.out.println(new No179_largestNumber().largestNumber_v2(new int[]{0, 9, 8, 7, 6, 5, 4, 3, 2, 1}));
    }

    public String largestNumber_v1(int[] nums) {
        boolean f = true;
        for (int num : nums) {
            if (num != 0) {
                f = false;
                break;
            }
        }
        if (f) {
            return "0";
        }
        return Arrays.stream(nums).boxed().sorted((o1, o2) -> {
            Long t1 = Long.parseLong(o1 + "" + o2);
            Long t2 = Long.parseLong(o2 + "" + o1);
            return (int) (t2 - t1);
        }).map(String::valueOf).collect(Collectors.joining(""));
    }

    public String largestNumber_v2(int[] nums) {
        boolean f = true;
        for (int num : nums) {
            if (num != 0) {
                f = false;
                break;
            }
        }
        if (f) {
            return "0";
        }
        return Arrays.stream(nums).boxed().sorted((o1, o2) -> {
            long t1 = 10, t2 = 10;
            while (t1 <= o1) {
                t1 *= 10;
            }
            while (t2 <= o2) {
                t2 *= 10;
            }
            long c1 = o1 * t2 + o2;
            long c2 = o2 * t1 + o1;
            return Long.compare(c2, c1);
        }).map(String::valueOf).collect(Collectors.joining(""));
    }
}
