package com.moon.leetcode;

import java.util.Arrays;
import java.util.Comparator;

// 1723. 完成所有工作的最短时间
//
//给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
//
// 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。请你
//设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
//
// 返回分配方案中尽可能 最小 的 最大工作时间 。
//
// 示例 1：
//
//输入：jobs = [3,2,3], k = 3
//输出：3
//解释：给每位工人分配一项工作，最大工作时间是 3 。
//
// 示例 2：
//
//输入：jobs = [1,2,4,7,8], k = 2
//输出：11
//解释：按下述方式分配工作：
//1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
//2 号工人：4、7（工作时间 = 4 + 7 = 11）
//最大工作时间是 11 。
//
// 提示：
//
// 1 <= k <= jobs.length <= 12
// 1 <= jobs[i] <= 107
//
// Related Topics 递归 回溯算法
// 👍 84 👎 0
public class No1723_minimumTimeRequired {
    int[] jobs;
    int n, k;
    int ans = 0x3f3f3f3f;

    public static void main(String[] args) {
        System.out.println(new No1723_minimumTimeRequired().minimumTimeRequired_v4(new int[]{2, 2, 4}, 2));
    }

    public int minimumTimeRequired_v1(int[] jobs, int k) {
        this.jobs = jobs;
        this.n = jobs.length;
        this.k = k;
        int[] times = new int[k];
        backtrack(0, 0, times);
        return ans;
    }

    private void backtrack(int j, int max, int[] times) {
        if (max >= ans) {
            return;
        }
        if (j == n) {
            ans = max;
            return;
        }
        for (int i = 0; i < k; i++) {
            times[i] += jobs[j];
            backtrack(j + 1, Math.max(max, times[i]), times);
            times[i] -= jobs[j];
        }
    }

    public int minimumTimeRequired_v2(int[] jobs, int k) {
        this.jobs = jobs;
        this.n = jobs.length;
        this.k = k;
        int[] times = new int[k];
        dfs(0, 0, 0, times);
        return ans;
    }

    private void dfs(int j, int max, int allot, int[] times) {
        if (max >= ans) {
            return;
        }
        if (j == n) {
            ans = max;
            return;
        }
        if (allot < k) {
            times[allot] += jobs[j];
            dfs(j + 1, Math.max(max, times[allot]), allot + 1, times);
            times[allot] -= jobs[j];
        }
        for (int i = 0; i < k; i++) {
            times[i] += jobs[j];
            dfs(j + 1, Math.max(max, times[i]), allot, times);
            times[i] -= jobs[j];
        }
    }

    public int minimumTimeRequired_v3(int[] jobs, int k) {
        int n = jobs.length;
        int m = 1 << n;
        int[] sum = new int[m];
        for (int i = 1; i < m; i++) {
            int x = Integer.numberOfTrailingZeros(i), y = i - (1 << x);
            sum[i] = sum[y] + jobs[x];
        }

        int[][] dp = new int[k][m];
        if (m >= 0) {
            System.arraycopy(sum, 0, dp[0], 0, m);
        }

        for (int i = 1; i < k; i++) {
            for (int j = 0; j < m; j++) {
                int minn = Integer.MAX_VALUE;
                for (int x = j; x != 0; x = (x - 1) & j) {
                    minn = Math.min(minn, Math.max(dp[i - 1][j - x], sum[x]));
                }
                dp[i][j] = minn;
            }
        }
        return dp[k - 1][m - 1];
    }

    public int minimumTimeRequired_v4(int[] jobs, int k) {
        jobs = Arrays.stream(jobs).boxed().sorted(Comparator.comparingInt(o -> o)).mapToInt(p -> p).toArray();
        int l = jobs[0], r = Arrays.stream(jobs).sum();
        while (l < r) {
            int mid = (l + r) / 2;
            if (check(jobs, k, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public boolean check(int[] jobs, int k, int limit) {
        int[] workloads = new int[k];
        return backtrack(jobs, workloads, 0, limit);
    }

    public boolean backtrack(int[] jobs, int[] workloads, int i, int limit) {
        if (i >= jobs.length) {
            return true;
        }
        int cur = jobs[i];
        for (int j = 0; j < workloads.length; ++j) {
            if (workloads[j] + cur <= limit) {
                workloads[j] += cur;
                if (backtrack(jobs, workloads, i + 1, limit)) {
                    return true;
                }
                workloads[j] -= cur;
            }
            // 如果当前工人未被分配工作，那么下一个工人也必然未被分配工作
            // 或者当前工作恰能使该工人的工作量达到了上限
            // 这两种情况下我们无需尝试继续分配工作
            if (workloads[j] == 0 || workloads[j] + cur == limit) {
                break;
            }
        }
        return false;
    }
}
