package com.moon.leetcode;

import java.util.HashMap;
import java.util.Map;

// 1854. 人口最多的年份
//
//给你一个二维整数数组 logs ，其中每个 logs[i] = [birthi, deathi] 表示第 i 个人的出生和死亡年份。
//
// 年份 x 的 人口 定义为这一年期间活着的人的数目。第 i 个人被计入年份 x 的人口需要满足：x 在闭区间 [birthi, deathi - 1] 内
//。注意，人不应当计入他们死亡当年的人口中。
//
// 返回 人口最多 且 最早 的年份。
//
// 示例 1：
//
// 输入：logs = [[1993,1999],[2000,2010]]
//输出：1993
//解释：人口最多为 1 ，而 1993 是人口为 1 的最早年份。
//
// 示例 2：
//
// 输入：logs = [[1950,1961],[1960,1971],[1970,1981]]
//输出：1960
//解释：
//人口最多为 2 ，分别出现在 1960 和 1970 。
//其中最早年份是 1960 。
//
// 提示：
//
// 1 <= logs.length <= 100
// 1950 <= birthi < deathi <= 2050
//
// Related Topics 数组
// 👍 5 👎 0
public class No1854_maximumPopulation {
    public static void main(String[] args) {
        System.out.println(new No1854_maximumPopulation().maximumPopulation_v1(new int[][]{{2008, 2026}, {2004, 2008}, {2034, 2035}, {1999, 2050}, {2049, 2050}, {2011, 2035}, {1966, 2033}, {2044, 2049}}));
    }

    public int maximumPopulation_v1(int[][] logs) {
        Map<Integer, Integer> birth = new HashMap<>();
        Map<Integer, Integer> death = new HashMap<>();
        for (int[] log : logs) {
            birth.put(log[0], birth.getOrDefault(log[0], 0) + 1);
            death.put(log[1], death.getOrDefault(log[1], 0) + 1);
        }
        int max = 0, year = 1949, pre = 0;
        for (int i = 1950; i <= 2050; i++) {
            if (i == 2004 || i == 2011) {
                System.out.println();
            }
            int cur = pre + birth.getOrDefault(i, 0) - death.getOrDefault(i, 0);
            if (max < cur) {
                max = cur;
                year = i;
            }
            pre = cur;
        }
        return year;
    }

    public int maximumPopulation_v2(int[][] logs) {
        int[] f = new int[101];
        for (int[] log : logs) {
            f[log[0] - 1950]++;
            f[log[1] - 1950]--;
        }
        int max = 0, year = 1949, cur = 0;
        for (int i = 0; i <= 100; i++) {
            cur = cur + f[i];
            if (max < cur) {
                max = cur;
                year = i;
            }
        }
        return year + 1950;
    }
}


