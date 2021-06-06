package com.moon.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Test2 {

    public int minWastedSpace(int[] packages, int[][] boxes) {
        int m = packages.length;
        int n = boxes.length;
        Arrays.sort(packages);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        long[] sum = new long[m + 1];
        for (int i = 0; i < m; i++) {
            int p = packages[i];
            sum[i + 1] = sum[i] + packages[i];
            map.put(p, i);
        }
        long ans = Long.MAX_VALUE;
        boolean f = true;
        for (int i = 0; i < n; i++) {
            int[] box = boxes[i];
            int o = box.length;
            Arrays.sort(box);
            if (box[o - 1] < packages[m - 1]) {
                continue;
            }
            f = false;
            long tmp = 0;
            int preIndex = -1;
            for (int j = 0; j < o; j++) {
                int b = box[j];
                Map.Entry<Integer, Integer> entry = map.floorEntry(b);
                if (entry == null) {
                    continue;
                }
                int curIndex = entry.getValue();
                tmp += (((long) b * (curIndex - preIndex) - (sum[curIndex + 1] - sum[preIndex + 1])));
                preIndex = curIndex;
            }
            ans = Math.min(tmp, ans);
        }
        return f ? -1 : (int) (ans % 1_000_000_007);

    }


    public int minWastedSpace_v1(int[] packages, int[][] boxes) {
        int maxn = (int) (1e9 + 7);
        int max = 0;
        for (int i = 0; i < packages.length; i++) {
            max = Math.max(packages[i], max);
        }
        long ans = -1;
        long[] diffs = new long[max + 1];
        long[] diffc = new long[max + 1];
        long[] sum = new long[max + 1];
        long[] count = new long[max + 1];
        for (int i = 0; i < packages.length; i++) {
            diffs[packages[i]] += packages[i];
            diffc[packages[i]]++;
        }
        for (int i = 1; i <= max; i++) {
            sum[i] = sum[i - 1] + diffs[i];
            count[i] = count[i - 1] + diffc[i];
        }
        for (int i = 0; i < boxes.length; i++) {
            Arrays.sort(boxes[i]);
            if (boxes[i][boxes[i].length - 1] < max) {
                if (count[max] - count[boxes[i][boxes[i].length - 1]] > 0) continue;
            }
            long s = 0;
            for (int j = 0; j < boxes[i].length; j++) {
                int ql = j > 0 ? boxes[i][j - 1] + 1 : 0;
                int qr = Math.min(boxes[i][j], max);
                long s1 = sum[qr] - sum[ql];
                long c1 = count[qr] - count[ql];
                s += c1 * boxes[i][j] - s1;
                if (boxes[i][j] >= max) break;
            }
            if (ans == -1) ans = s;
            else ans = Math.min(ans, s);
        }
        return (int) (ans % maxn);
    }

    public int reductionOperations(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] a = Arrays.stream(nums).boxed().distinct().sorted(Comparator.comparingInt(o -> o)).mapToInt(p -> p).toArray();
        int n = a.length;
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            ans += map.get(a[i]) * i;
        }
        return ans;
    }

    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        boolean ans = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[i][j]) {
                    ans = false;
                    break;
                }
            }
        }
        if (ans) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[n - j - 1][i] != target[i][j]) {
                    ans = true;
                    break;
                }
            }
        }
        if (!ans) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[n - 1 - i][n - 1 - j] != target[i][j]) {
                    ans = false;
                    break;
                }
            }
        }
        if (ans) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[j][n - 1 - i] != target[i][j]) {
                    ans = true;
                    break;
                }
            }
        }
        return !ans;
    }

    public static void main(String[] args) {
        System.out.println(new Test2().minWastedSpace(new int[]{4, 4, 9, 10, 1}, new int[][]{{9}, {4, 8}, {1, 3}}));
    }
}
