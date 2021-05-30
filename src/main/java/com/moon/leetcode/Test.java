package com.moon.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Test {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeMap<Long, Integer> set = new TreeMap<>();
        int m = nums.length;
        for (int i = 0; i < m; i++) {
            long min = (long) nums[i] - t;
            long max = (long) nums[i] + t;
            Map.Entry<Long, Integer> entry = set.ceilingEntry(min);
            if (entry != null && entry.getKey() <= max && entry.getValue() > 0) {
                return true;
            }
            set.put((long) nums[i], set.getOrDefault((long) nums[i], 0) + 1);
            if (i >= k) {
                if (set.get((long) nums[i - k]) > 2) {
                    set.put((long) nums[i - k], set.get((long) nums[i - k]) - 1);
                } else {
                    set.remove((long) nums[i - k]);
                }
            }
        }
        return false;
    }

    public boolean checkZeroOnes(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        if (n == 1) {
            return chars[0] == '1';
        }
        int oneMax = 0;
        int zeroMax = 0;
        int i = 0;
        while (i < n) {
            int t1 = 0;
            if (chars[i] == '1') {
                i++;
                while (i < n && chars[i] == chars[i - 1]) {
                    i++;
                    t1++;
                }
                oneMax = Math.max(oneMax, t1 + 1);
            }
            i++;
        }
        i = 0;
        while (i < n) {
            int t1 = 0;
            if (chars[i] == '0') {
                i++;
                while (i < n && chars[i] == chars[i - 1]) {
                    i++;
                    t1++;
                }
                zeroMax = Math.max(zeroMax, t1 + 1);
            }
            i++;
        }
        return oneMax > zeroMax;
    }

    public int minSpeedOnTime(int[] dist, double hour) {
        int n = dist.length;
        if (hour < n - 1) {
            return -1;
        }
        int l = 1, r = 100000000;
        while (l < r) {
            int m = l + (r - l) / 2;
            int check = check(m, dist, hour);
            if (check == 0) {
                return m;
            } else if (check < 0) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return r;
    }

    private int check(int m, int[] dist, double hour) {
        double tmp = 0;
        for (int i = 0; i < dist.length - 1; i++) {
            tmp += Math.ceil((double) dist[i] / m);
        }
        tmp += (double) dist[dist.length - 1] / m;
        return Double.compare(hour, tmp);
    }

    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        char[] chars = s.toCharArray();
        if (chars[n - 1] == '1') {
            return false;
        }
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int k = 0;
        for (int i = 0; i < n; i++) {
            map.put(i, k);
            if (chars[i] == '0') {
                list.add(i);
                k++;
            }
        }
        boolean[] f = new boolean[n];
        f[0] = true;
        for (Integer j : list) {
            if (!f[j]) {
                for (int i = map.getOrDefault(j - maxJump, 0); i <= map.getOrDefault(j - minJump, 0); i++) {
                    if (f[i]) {
                        f[j] = true;
                        break;
                    }
                }
            }
        }
        for (int j = 0; j < n; j++) {
            if (chars[j] == '1') {
                continue;
            }
            for (int i = Math.max(j - maxJump, 0); i <= j - minJump; i++) {
                if (f[i]) {
                    f[j] = true;
                    break;
                }
            }
        }
        return f[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Test().canReach("01101110", 2, 3));
    }
}