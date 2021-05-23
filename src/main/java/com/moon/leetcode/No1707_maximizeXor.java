package com.moon.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// 1707. 与数组中元素的最大异或值
//给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
//
// 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR
// xi) ，其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
//
// 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
//
// 示例 1：
//
// 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
//输出：[3,3,7]
//解释：
//1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
//2) 1 XOR 2 = 3.
//3) 5 XOR 2 = 7.
//
// 示例 2：
//
// 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
//输出：[15,-1,5]
//
// 提示：
//
// 1 <= nums.length, queries.length <= 105
// queries[i].length == 2
// 0 <= nums[j], xi, mi <= 109
//
// Related Topics 位运算 字典树
// 👍 100 👎 0
public class No1707_maximizeXor {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        int[] res = new int[m];
        Arrays.sort(nums);
        int min = nums[0];
        Map<int[], Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            map.put(queries[i], i);
        }
        Arrays.sort(queries, Comparator.comparingInt(o -> o[1]));
        TrieNode root = new TrieNode();
        int start = 0;
        for (int[] query : queries) {
            int xi = query[0];
            int mi = query[1];
            if (mi < min) {
                res[map.get(query)] = -1;
                continue;
            }
            for (; start < n && nums[start] <= mi; start++) {
                TrieNode pre = root;
                int num = nums[start];
                for (int k = 31; k >= 0; k--) {
                    int l = num >> k & 1;
                    if (pre.child[l] == null) {
                        pre.child[l] = new TrieNode();
                    }
                    pre = pre.child[l];
                }
            }
            int x = 0;
            TrieNode xor = root;
            for (int k = 31; k >= 0; k--) {
                int l = xi >> k & 1;
                if (xor.child[l ^ 1] != null) {
                    x = x << 1 | 1;
                    xor = xor.child[l ^ 1];
                } else {
                    x = x << 1;
                    xor = xor.child[l];
                }
            }
            res[map.get(query)] = x;
        }
        return res;
    }

    private static class TrieNode {
        TrieNode[] child = new TrieNode[2];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new No1707_maximizeXor().maximizeXor(
                new int[]{537623, 4096, 4096, 7351703, 4096, 2790666, 3810100, 3596649, 2771788, 1751995, 1414302, 22158312, 15941567, 32835966, 4096, 26516532, 3772313, 687681, 4096, 2902600},
                new int[][]{{17484496, 1000000000}, {391935295, 158969}, {389948163, 1000000000}, {530828903, 207846853}, {1000000000, 1000000000}, {1000000000, 1000000000}, {276739277, 1000000000}, {350541283, 1000000000}, {343329183, 1000000000}, {382830042, 225644}, {1000000000, 2302405}, {3708384, 1000000000}, {330902154, 1000000000}, {1000000000, 4024838}, {527385402, 142642}, {312288482, 1000000000}, {1000000000, 1000000000}, {19929306, 3787993}, {15768874, 1000000000}, {27944430, 172704181}}
        )));
    }

}
