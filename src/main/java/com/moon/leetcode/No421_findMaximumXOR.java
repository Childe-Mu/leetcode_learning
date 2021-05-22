package com.moon.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 421. 数组中两个数的最大异或值
 * <p>
 * 给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。
 * <p>
 * 找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,  j < n 。
 * <p>
 * 你能在O(n)的时间解决这个问题吗？
 * <p>
 * 示例:
 * <p>
 * 输入: [3, 10, 5, 25, 2, 8]
 * <p>
 * 输出: 28
 * <p>
 * 解释: 最大的结果是 5 ^ 25 = 28.
 */
public class No421_findMaximumXOR {
    public static int findMaximumXOR_v1(int[] nums) {
        int maxNum = nums[0];
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        // length of max number in a binary representation
        int L = (Integer.toBinaryString(maxNum)).length();

        int maxXor = 0, currXor;
        Set<Integer> prefixes = new HashSet<>();
        for (int i = L - 1; i > -1; --i) {
            // go to the next bit by the left shift
            maxXor <<= 1;
            // set 1 in the smallest bit
            currXor = maxXor | 1;
            System.out.println(currXor);
            prefixes.clear();
            // compute all possible prefixes
            // of length (L - i) in binary representation
            for (int num : nums) {
                prefixes.add(num >> i);
            }
            // Update maxXor, if two of these prefixes could result in currXor.
            // Check if p1^p2 == currXor, i.e. p1 == currXor^p2.
            System.out.println(prefixes);
            for (int p : prefixes) {
                if (prefixes.contains(currXor ^ p)) {
                    maxXor = currXor;
                    break;
                }
            }
        }
        return maxXor;
    }

    public static int findMaximumXOR_v2(int[] nums) {
        int maxNum = Arrays.stream(nums).max().orElse(0);
        int len = Integer.toBinaryString(maxNum).length();
        int maxXOR = 0;
        TrieNode root = new TrieNode();
        for (int num : nums) {
            TrieNode prevNode = root;
            TrieNode xorNode = root;
            int temp = 0;
            for (int i = len - 1; i >= 0; i--) {
                int key = (num >>> i) & 1;
                if (!prevNode.childNode.containsKey(key)) {
                    prevNode.childNode.put(key, new TrieNode());
                }
                prevNode = prevNode.childNode.get(key);
                if (xorNode.childNode.containsKey(key ^ 1)) {
                    temp = temp << 1 | 1;
                    xorNode = xorNode.childNode.get(key ^ 1);
                } else {
                    temp <<= 1;
                    xorNode = xorNode.childNode.get(key);
                }
            }
            maxXOR = Math.max(maxXOR, temp);
        }
        return maxXOR;
    }

    public static int findMaximumXOR_v3(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        int len = Integer.toBinaryString(max).length();
        int ans = 0;
        TrieNode root = new TrieNode();
        for (int num : nums) {
            TrieNode pre = root;
            TrieNode xor = root;
            int x = 0;
            for (int i = len - 1; i >= 0; i--) {
                int key = num >>> i & 1;
                if (!pre.childNode.containsKey(key)) {
                    pre.childNode.put(key, new TrieNode());
                }
                pre = pre.childNode.get(key);
                if (xor.childNode.containsKey(key ^ 1)) {
                    x = x << 1 | 1;
                    xor = xor.childNode.get(key ^ 1);
                } else {
                    x = x << 1;
                    xor = xor.childNode.get(key);
                }
            }
            ans = Math.max(x, ans);
        }
        return ans;
    }

    public static int findMaximumXOR_v4(int[] nums) {
        int x = 0;
        for (int i = 30; i >= 0; i--) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num >> i);
            }
            int next = x << 1 | 1;
            boolean found = false;
            for (Integer pre : set) {
                if (set.contains(next ^ pre)) {
                    found = true;
                    break;
                }
            }
            x = found ? next : x << 1;
        }
        return x;
    }

    public static void main(String[] args) {
        System.out.println(findMaximumXOR_v3(new int[]{3, 10, 5, 25, 2, 8}));
//        int maxNum = Arrays.stream(new int[]{3, 10, 5, 25, 2, 8}).max().orElse(0);
//        System.out.println(maxNum);
    }

    private static class TrieNode {
        Map<Integer, TrieNode> childNode = new HashMap<>();
    }
}
