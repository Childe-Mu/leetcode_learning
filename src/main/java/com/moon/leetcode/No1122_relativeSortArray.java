package com.moon.leetcode;

import java.util.Arrays;

/**
 * 1122. 数组的相对排序<br/>
 * <br/>
 * 给你两个数组，arr1 和 arr2，<br/>
 * arr2 中的元素各不相同<br/>
 * arr2 中的每个元素都出现在 arr1 中<br/>
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。<br/>
 * <br/>
 * 示例：<br/>
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]<br/>
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]<br/>
 * <br/>
 * 提示：<br/>
 * arr1.length, arr2.length <= 1000<br/>
 * 0 <= arr1[i], arr2[i] <= 1000<br/>
 * arr2 中的元素 arr2[i] 各不相同<br/>
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中<br/>
 */
public class No1122_relativeSortArray {
    // /**
    //  * 解法思路：
    //  * 1.通过循环将不在arr2的元素挪到arr1的最后面，结果得到arr1前面0~j全为包含于arr2的元素，j+1 ~ arr1.length,全为不包含于arr2的元素
    //  * 2.对arr1 的（j+1 ~ arr1.length）进行升序排序
    //  * 3.对arr1的（0~j）进行arr2顺序排序
    //  */
    // public static int[] relativeSortArray(int[] arr1, int[] arr2) {
    //     int j = arr1.length - 1;
    //     Map<Integer, Integer> sortOrder = new HashMap<>();
    //     for (int i = 0; i < arr2.length; i++) {
    //         sortOrder.put(arr2[i], i);
    //     }
    //     for (int i = 0; i < arr1.length && i < j; i++) {
    //         if (!sortOrder.containsKey(arr1[i])) {
    //             while (!sortOrder.containsKey(arr1[j])) {
    //                 j--;
    //             }
    //             if (i >= j) {
    //                 break;
    //             }
    //             int temp = arr1[i];
    //             arr1[i] = arr1[j];
    //             arr1[j] = temp;
    //         }
    //     }
    //     Arrays.sort(arr1, j + 1, arr1.length);
    //     return Arrays.stream(arr1).boxed().sorted((o1, o2) -> {
    //         if (sortOrder.containsKey(o1) && sortOrder.containsKey(o2)) {
    //             return sortOrder.get(o1) - sortOrder.get(o2);
    //         }
    //         return 0;
    //     }).mapToInt(Integer::intValue).toArray();
    // }

    // /**
    //  * 和上一个大同小异
    //  */
    // public static int[] relativeSortArray(int[] arr1, int[] arr2) {
    //     int j = arr1.length - 1;
    //     Set<Integer> set = new HashSet<>();
    //     for (int i : arr2) {
    //         set.add(i);
    //     }
    //     for (int i = 0; i < arr1.length && i < j; i++) {
    //         if (!set.contains(arr1[i])) {
    //             while (!set.contains(arr1[j])) {
    //                 j--;
    //             }
    //             if (i >= j) {
    //                 break;
    //             }
    //             int temp = arr1[i];
    //             arr1[i] = arr1[j];
    //             arr1[j] = temp;
    //         }
    //     }
    //     Arrays.sort(arr1, j + 1, arr1.length);
    //     int k = 0;
    //     int[] temp = Arrays.copyOfRange(arr1, 0, j + 1);
    //     for (int i : arr2) {
    //         for (int i1 : temp) {
    //             if (i == i1) {
    //                 arr1[k++] = i;
    //             }
    //         }
    //     }
    //     return arr1;
    // }

    /**
     * 桶排序，计数排序
     */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] nums = new int[1001];
        int[] res = new int[arr1.length];
        //遍历arr1,统计每个元素的数量
        for (int i : arr1) {
            nums[i]++;
        }
        //遍历arr2,处理arr2中出现的元素
        int index = 0;
        for (int i : arr2) {
            while (nums[i] > 0) {
                res[index++] = i;
                nums[i]--;
            }
        }
        //遍历nums,处理剩下arr2中未出现的元素
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0) {
                res[index++] = i;
                nums[i]--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                relativeSortArray(
                        new int[]{28, 6, 22, 8, 44, 17},
                        new int[]{22, 28, 8, 6}
                )));
        //         [28,6,22,8,44,17]
        // [22,28,8,6]
    }
}