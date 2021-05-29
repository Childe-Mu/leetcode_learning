package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author yaojia.mu@joymo.tech
 * @date 2021-05-29 22:33:58
 */
public class Test1 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Test1().getBiggestThree_v1(new int[][]{{3, 4, 5, 1, 3}, {3, 3, 4, 2, 3}, {20, 30, 200, 40, 10}, {1, 5, 5, 4, 1}, {4, 3, 2, 2, 5}})));
    }

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
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {

                    int s = 0;
                    int[] t = new int[]{j - i + 1, k + i - 1};
                    int[] b = new int[]{j + i - 1, k + i - 1};
                    int[] l = new int[]{j, k};
                    int[] r = new int[]{j, k + 2 * (i - 1)};
                    System.out.println(Arrays.toString(t));
                    System.out.println(Arrays.toString(b));
                    System.out.println(Arrays.toString(l));
                    System.out.println(Arrays.toString(r));

                    if (l[0] < 0 || l[1] < 0
                            || r[0] < 0 || r[1] < 0
                            || t[0] < 0 || t[1] < 0
                            || b[0] < 0 || b[1] < 0
                            || l[0] > m || l[1] > n
                            || r[0] > m || r[1] > n
                            || t[0] > m || t[1] > n
                            || b[0] > m || b[1] > n) {
                        continue;
                    }
                    for (int h = 0; h < i - 1; h++) {
                        s += grid[t[0] - h][t[1] + h] + grid[b[0] + h][b[1] - h] + grid[l[0] - h][l[1] + h] + grid[r[0] - h][r[1] - h];
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
}
