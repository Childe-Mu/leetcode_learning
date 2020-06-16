package com.moon.leetcode;

import java.util.Arrays;

/**
 * 1356. 根据数字二进制下 1 的数目排序<br/>
 * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
 * 请你返回排序后的数组。
 * <p>
 * 示例 1：
 * 输入：arr = [0,1,2,3,4,5,6,7,8]
 * 输出：[0,1,2,4,8,3,5,6,7]
 * 解释：[0] 是唯一一个有 0 个 1 的数。
 * [1,2,4,8] 都有 1 个 1 。
 * [3,5,6] 有 2 个 1 。
 * [7] 有 3 个 1 。
 * 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
 * <p>
 * 示例 2：
 * 输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
 * 输出：[1,2,4,8,16,32,64,128,256,512,1024]
 * 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
 * <p>
 * 示例 3：
 * 输入：arr = [10000,10000]
 * 输出：[10000,10000]
 * <p>
 * 示例 4：
 * 输入：arr = [2,3,5,7,11,13,17,19]
 * 输出：[2,3,5,17,7,11,13,19]
 * <p>
 * 示例 5：
 * 输入：arr = [10,100,1000,10000]
 * 输出：[10,100,10000,1000]
 * <p>
 * <p>
 * 提示：
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 10^4
 */
public class No1356_sortByBits {
    // public static int[] sortByBits(int[] arr) {
    //     int count = 0;
    //     int[] result = new int[arr.length];
    //     Map<String, List<Integer>> group = new HashMap<>();
    //     for (int i : arr) {
    //         String key = Integer.toBinaryString(i).replaceAll("0", "");
    //         if (group.containsKey(key)) {
    //             group.get(key).add(i);
    //         } else {
    //             List<Integer> sameKey = new ArrayList<>();
    //             sameKey.add(i);
    //             group.put(key, sameKey);
    //         }
    //     }
    //     List<String> keys = new ArrayList<>(group.keySet());
    //     keys.sort(Comparator.comparingInt(String::length));
    //     for (String key : keys) {
    //         List<Integer> temp = group.get(key);
    //         if (temp.size() == 1) {
    //             result[count++] = temp.get(0);
    //         } else {
    //             temp.sort(Comparator.comparingInt(i -> i));
    //             for (Integer integer : temp) {
    //                 result[count++] = integer;
    //             }
    //         }
    //
    //     }
    //     return result;
    // }

    public static int[] sortByBits(int[] arr) {
        return Arrays.stream(arr)
                .boxed()
                .sorted((a, b) -> countBits(a) == countBits(b) ? a - b : countBits(a) - countBits(b))
                .mapToInt(Integer::intValue)
                .toArray();
        // return Arrays.stream(arr)
        //         .boxed()
        //         .sorted((i1, i2) -> {
        //             if (countBits(i1) == countBits(i2)) {
        //                 return i1 - i2;
        //             } else {
        //                 return countBits(i1) - countBits(i2);
        //             }
        //         })
        //         .mapToInt(Integer::intValue)
        //         .toArray();
    }

    private static int countBits(int num) {
        // 位运算hack
        int res = 0;
        while (num != 0) {
            // System.out.println(Integer.toBinaryString(num) + " -- " + num);
            // System.out.println(Integer.toBinaryString(num - 1) + " -- " + (num - 1));
            num = num & (num - 1);
            res++;
            // System.out.println("----------------");
        }
        return res;
    }

    /**
     * 非常慢，不推荐
     */
    // public static int[] sortByBits(int[] arr) {
    //     return Arrays.stream(arr)
    //             .boxed()
    //             .sorted((i1, i2) -> {
    //                 int l1 = Integer.toBinaryString(i1).replaceAll("0", "").length()
    //                 int l2 = Integer.toBinaryString(i2).replaceAll("0", "").length()
    //                 if (l1 == l2) {
    //                     return i1 - i2;
    //                 } else {
    //                     return l1 - l2;
    //                 }
    //             })
    //             .mapToInt(Integer::intValue)
    //             .toArray();
    // }
    public static void main(String[] args) {
        // int[] params = {8848, 4205, 113, 3764, 2376, 6352, 6372, 9927, 2990, 4286, 7783, 2121, 3749, 7800, 7479, 2723, 8305, 8276, 3598, 5776, 8016, 5053, 3113, 4395, 3595, 5079, 3065, 3158, 6141, 4187, 1328, 4900, 2120, 3239, 6474, 2218, 4892, 7993};
        // for (int i : sortByBits(params)) {
        //     System.out.print(i + " ");
        // }
        //
        int[] params = {16};
        for (int i : params) {
            System.out.print(countBits(i));
        }


    }
}
