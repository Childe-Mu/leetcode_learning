package com.moon.leetcode;

import java.util.Arrays;

/**
 * 26. 删除排序数组中的重复项<br/>
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。<br/>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。<br/>
 * <br/>
 * 示例 1:<br/>
 * 给定数组 nums = [1,1,2], <br/>
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。 <br/>
 * 你不需要考虑数组中超出新长度后面的元素。<br/>
 * <br/>
 * 示例 2:<br/>
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],<br/>
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。<br/>
 * 你不需要考虑数组中超出新长度后面的元素。<br/>
 * <br/>
 * 说明:<br/>
 * 为什么返回数值是整数，但输出的答案是数组呢?<br/>
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。<br/>
 * 你可以想象内部操作如下:<br/>
 * <code><br/>
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝<br/>
 * int len = removeDuplicates(nums);<br/>
 * // 在函数里修改输入数组对于调用者是可见的。<br/>
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。<br/>
 * for (int i = 0; i < len; i++) {<br/>
 * print(nums[i]);<br/>
 * }<br/></code>
 */
public class No26_removeDuplicates {
    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return 1;
        }
        int prev = nums[0];
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (prev != nums[i]) {
                nums[++index] = nums[i];
                prev = nums[i];
            }
        }
        return index + 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int len = removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < len; i++) {
            System.out.println(nums[i]);
        }
    }
}
