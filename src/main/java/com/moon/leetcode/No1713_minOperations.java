package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 1713. 得到子序列的最少操作次数
//
//给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。
//
// 每一次操作中，你可以在 arr 的任意位置插入任一整数。比方说，如果 arr = [1,4,1,2] ，那么你可以在中间添加 3 得到 [1,4,3,1,
//2] 。你可以在数组最开始或最后面添加整数。
//
// 请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。
//
// 一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。比方说，[2,7,4] 是 [4,2,3,
//7,2,1,4] 的子序列（加粗元素），但 [2,4,2] 不是子序列。
//
//
//
// 示例 1：
//
// 输入：target = [5,1,3], arr = [9,4,2,3,4]
//输出：2
//解释：你可以添加 5 和 1 ，使得 arr 变为 [5,9,4,1,2,3,4] ，target 为 arr 的子序列。
//
//
// 示例 2：
//
// 输入：target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
//输出：3
//
//
//
//
// 提示：
//
//
// 1 <= target.length, arr.length <= 105
// 1 <= target[i], arr[i] <= 109
// target 不包含任何重复元素。
//
// Related Topics 贪心 数组 哈希表 二分查找
// 👍 121 👎 0
public class No1713_minOperations {
    public static void main(String[] args) {
        System.out.println(new No1713_minOperations().minOperations(new int[]{16, 7, 20, 11, 15, 13, 10, 14, 6, 8}, new int[]{11, 14, 15, 7, 5, 5, 6, 10, 11, 6}));
    }

    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> t = new HashMap<>();
        int m = target.length;
        for (int i = 0; i < m; i++) {
            t.put(target[i], i);
        }
        List<Integer> list = new ArrayList<>();
        for (int j : arr) {
            if (t.containsKey(j)) {
                list.add(t.get(j));
            }
        }
        int size = list.size();
        int[] incr = new int[size];
        Arrays.fill(incr, -1);
        incr[0] = list.get(0);
        int len = 1;
        for (int i = 1; i < size; i++) {
            if (incr[len - 1] > list.get(i)) {
                len = binarySearchSwap(incr, len, list.get(i));
            } else if (incr[len - 1] < list.get(i)) {
                incr[len++] = list.get(i);
            }
        }
        int cnt = 0;
        for (int i : incr) {
            cnt += i == -1 ? 0 : 1;
        }
        return m - cnt;
    }

    private int binarySearchSwap(int[] incr, int len, Integer t) {
        int l = 0, r = len - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (incr[m] == t) {
                return len;
            } else if (incr[m] > t) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        incr[l] = t;
        return l + 1;
    }
}



