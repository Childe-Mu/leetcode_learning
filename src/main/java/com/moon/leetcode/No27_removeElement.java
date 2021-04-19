package com.moon.leetcode;

import java.util.Arrays;

/**
 * 27. 移除元素<br/>
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。<br/>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。<br/>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。<br/>
 * <br/>
 * 示例 1:<br/>
 * 给定 nums = [3,2,2,3], val = 3,<br/>
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。<br/>
 * 你不需要考虑数组中超出新长度后面的元素。<br/>
 * <br/>
 * 示例 2:<br/>
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,<br/>
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。<br/>
 * 注意这五个元素可为任意顺序。<br/>
 * 你不需要考虑数组中超出新长度后面的元素。<br/>
 * <br/>
 * 说明:<br/>
 * 为什么返回数值是整数，但输出的答案是数组呢?<br/>
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。<br/>
 * 你可以想象内部操作如下:<br/><code>
 * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝<br/>
 * int len = removeElement(nums, val);<br/>
 * <br/>
 * // 在函数里修改输入数组对于调用者是可见的。<br/>
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。<br/>
 * for (int i = 0; i < len; i++) {<br/>
 * print(nums[i]);<br/>
 * }<br/></code>
 */
public class No27_removeElement {
    // /**
    //  * 方法一：双指针<br/>
    //  * 思路<br/>
    //  * 既然问题要求我们就地删除给定值的所有元素，我们就必须用 O(1) 的额外空间来处理它。如何解决？我们可以保留两个指针 i 和 j，
    //  * 其中 i 是慢指针，j 是快指针。<br/>
    //  * <br/>
    //  * 算法<br/>
    //  * 当 nums[j] 与给定的值相等时，递增 j 以跳过该元素。只要 nums[j] ≠ val，
    //  * 我们就复制 nums[j] 到 nums[i] 并同时递增两个索引。重复这一过程，直到 j 到达数组的末尾，该数组的新长度为 i。<br/>
    //  * 该解法与 删除排序数组中的重复项 的解法十分相似。<br/>
    //  */
    public static int removeElement_v1(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    // /**
    //  * 方法二：双指针 —— 当要删除的元素很少时<br/>
    //  * 思路<br/>
    //  * 现在考虑数组包含很少的要删除的元素的情况。例如，num=[1，2，3，5，4]，Val=4num=[1，2，3，5，4]，Val=4。
    //  * 之前的算法会对前四个元素做不必要的复制操作。另一个例子是 num=[4，1，2，3，5]，Val=4 num=[4，1，2，3，5]，Val=4。
    //  * 似乎没有必要将 [1，2，3，5][1，2，3，5] 这几个元素左移一步，因为问题描述中提到元素的顺序可以更改。<br/>
    //  *<br/>
    //  * 算法<br/>
    //  * 当我们遇到 nums[i] = valnums[i]=val 时，我们可以将当前元素与最后一个元素进行交换，并释放最后一个元素。
    //  * 这实际上使数组的大小减少了 1。<br/>
    //  * 请注意，被交换的最后一个元素可能是您想要移除的值。但是不要担心，在下一次迭代中，我们仍然会检查这个元素。<br/>
    //  */
    public static int removeElement_v2(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                // reduce array size by one
                n--;
            } else {
                i++;
            }
        }
        return n;
    }

    /**
     * 方法三：双指针<br/>
     * 和方法二类似，但是没有方法二巧妙，但是逻辑更清晰，更容易想到，没有那么脑筋急转弯。
     * 主要思路就是把数组前面值为val的都交换到最后，结果及时有个分界线，前面的全是不为val的，后面的都是为val的。
     */
    public static int removeElement_v3(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] == val && nums[right] != val) {
                nums[left] = nums[right];
                nums[right] = val;
                left++;
                right--;
            } else if (nums[left] == val && nums[right] == val) {
                right--;
            } else if (nums[left] != val && nums[right] != val) {
                left++;
            } else if (nums[left] != val && nums[right] == val) {
                left++;
                right--;
            }
        }
        for (left = 0; left < nums.length; left++) {
            if (nums[left] == val) {
                break;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 2, 3};
        int len = removeElement_v3(nums, 3);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(Arrays.copyOfRange(nums, 0, len)));
    }
}
