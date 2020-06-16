package com.moon.leetcode;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 349. 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * <p>
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * <p>
 * 说明:
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */
public class No349_intersection {
    // private static int[] intersection(int[] nums1, int[] nums2) {
    //     List<Integer> r = new ArrayList<>();
    //     Set<Integer> s = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
    //     Arrays.stream(nums2).boxed().distinct().forEach(p -> {
    //         if (s.contains(p)) {
    //             r.add(p);
    //         }
    //     });
    //     return r.stream().mapToInt(p->p).toArray();
    // }

    private static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        set1.retainAll(set2);
        return set1.stream().mapToInt(p->p).toArray();
    }

    public static void main(String[] args) {
        int[] n = new int[]{1, 2, 2, 1};
        int[] nn = new int[]{2, 2};
        System.out.println(Arrays.toString(intersection(n, nn)));
    }
}
