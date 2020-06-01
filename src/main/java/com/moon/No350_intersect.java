package com.moon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 350. 两个数组的交集 II<br/>
 * 给定两个数组，编写一个函数来计算它们的交集。<br/>
 * <br/>
 * 示例 1:<br/>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]<br/>
 * 输出: [2,2]<br/>
 * <br/>
 * 示例 2:<br/>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]<br/>
 * 输出: [4,9]<br/>
 * <br/>
 * 说明：<br/>
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。<br/>
 * 我们可以不考虑输出结果的顺序。<br/>
 */
public class No350_intersect {
    // /**
    //  * 哈希映射
    //  */
    // public static int[] intersect(int[] nums1, int[] nums2) {
    //     if (nums1.length > nums2.length) {
    //         return intersect(nums2, nums1);
    //     }
    //     Map<Integer, Integer> map = new HashMap<>();
    //     for (int key : nums1) {
    //         map.put(key, map.getOrDefault(key, 0) + 1);
    //     }
    //     int k = 0;
    //     for (int n : nums2) {
    //         int cnt = map.getOrDefault(n, 0);
    //         if (cnt > 0) {
    //             // 可以新生成一个临时数组用于存放交集，但是num1经过第一个循环，
    //             // 生成map以后，就没有用了，此时重复使用，可以减少程序内存
    //             nums1[k++] = n;
    //             map.put(n, cnt - 1);
    //         }
    //     }
    //     return Arrays.copyOfRange(nums1, 0, k);
    // }

    /**
     * 排序
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        if (nums1.length == 0 || nums2.length == 0 || nums1[0] > nums2[nums2.length - 1] || nums2[0] > nums1[nums1.length - 1]) {
            return new int[0];
        }
        // List<Integer> res = new ArrayList<>();
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                nums1[k++] = nums1[i];
                // res.add(nums1[i]);
                i++;
                j++;
            }
        }
        // return res.stream().mapToInt(Integer::intValue).toArray();
        return Arrays.copyOfRange(nums1, 0, k);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2})));
    }
}
