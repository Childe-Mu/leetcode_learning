package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class Test1 {


    public int countGoodSubstrings(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        int n = chars.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            char c = chars[i];
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (i >= 2) {
                if (map.size() == 3) {
                    ans++;
                }
                Integer cnt = map.get(chars[i - 2]);
                if (cnt == 1) {
                    map.remove(chars[i - 2]);
                } else {
                    map.put(chars[i - 2], cnt - 1);
                }
            }
        }
        return ans;
    }

    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int l = 0, r = n - 1;
        while (l < r) {
            max = Math.max(nums[l] + nums[r], max);
            l++;
            r--;
        }
        return max;
    }

    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int limit = (Math.min(m, n) + 1) / 2;
        List<Integer> list = new ArrayList<>();
        for (int[] ints : grid) {
            for (int i : ints) {
                list.add(i);
            }
        }
        for (int i = 2; i <= limit; i++) {
            for (int j = i - 1; j < m - (i - 1); j++) {
                for (int k = i - 1; k < n - (i - 1); k++) {
                    int s = 0;
                    int[] l = new int[]{j, k - (i - 1)};
                    int[] r = new int[]{j, k + (i - 1)};
                    int[] t = new int[]{j - (i - 1), k};
                    int[] b = new int[]{j + (i - 1), k};
                    for (int h = 0; h < i - 1; h++) {
                        s += grid[t[0] + h][t[1] - h] + grid[b[0] - h][b[1] + h] + grid[l[0] + h][l[1] + h] + grid[r[0] - h][r[1] - h];
                    }
                    list.add(s);
                }
            }
        }
        List<Integer> collect = list.stream().distinct().sorted((o1, o2) -> o2 - o1).collect(Collectors.toList());
        int size = Math.min(collect.size(), 3);
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = collect.get(i);
        }
        return res;
    }

    public int[] getBiggestThree_v1(int[][] grid) {
        TreeSet<Integer> sum = new TreeSet<>((a, b) -> b - a);

        int m = grid.length, n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = Math.min(Math.min(j, n - 1 - j), (m - 1 - i) / 2);
                //System.out.printf("[%d, %d], t = %d\n", i, j, t);
                for (int t = 1; t <= x; t++) {
                    int s = 0;
                    for (int k = 0; k <= t; k++) s += grid[i + k][j - k];
                    for (int k = 1; k <= t; k++) s += grid[i + k][j + k];
                    for (int k = 1; k <= t; k++) s += grid[i + t + k][j - t + k];
                    for (int k = 1; k < t; k++) s += grid[i + t + k][j + t - k];
                    sum.add(s);
                    System.out.printf("[%d, %d], [%d, %d], [%d, %d], [%d, %d], t=%d, s=%d\n", i, j, i + t, j - t, i + t + t, j, i + t, j + t, t, s);
                }
                sum.add(grid[i][j]);
            }
        }
        //System.out.println(sum);
        int[] ans = new int[Math.min(sum.size(), 3)];
        for (int i = 0; i < 3 && !sum.isEmpty(); i++) {
            ans[i] = sum.first();
            sum.remove(sum.first());
        }
        return ans;
    }

    public int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = 1 << n;
        int[] f = new int[m];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    System.out.println("i=" + Integer.toBinaryString(i) + "  j=" + j + "  pre=" + (i ^ (1 << j)) + "  nums1[" + (Integer.bitCount(i) - 1) + "]^nums2[" + j + "]");
                    f[i] = Math.min(f[i], f[i ^ (1 << j)] + (nums1[Integer.bitCount(i) - 1] ^ nums2[j]));
                }
            }
        }
        return f[m - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Test1().minimumXORSum(new int[]{1, 0}, new int[]{5, 3}));
    }
}
