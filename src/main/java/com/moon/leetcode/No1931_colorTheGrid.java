package com.moon.leetcode;

// 1931. 用三种不同颜色为网格涂色
//
//给你两个整数 m 和 n 。构造一个 m x n 的网格，其中每个单元格最开始是白色。请你用 红、绿、蓝 三种颜色为每个单元格涂色。所有单元格都需要被涂色。
//
// 涂色方案需要满足：不存在相邻两个单元格颜色相同的情况 。返回网格涂色的方法数。因为答案可能非常大， 返回 对 109 + 7 取余 的结果。
//
// 示例 1：
//
//输入：m = 1, n = 1
//输出：3
//解释：如上图所示，存在三种可能的涂色方案。
//
// 示例 2：
//
//输入：m = 1, n = 2
//输出：6
//解释：如上图所示，存在六种可能的涂色方案。
//
// 示例 3：
//
//输入：m = 5, n = 5
//输出：580986
//
// 提示：
//
// 1 <= m <= 5
// 1 <= n <= 1000
//
// 👍 10 👎 0
public class No1931_colorTheGrid {
    public int colorTheGrid_v1(int m, int n) {
        int mod = (int) 1e9 + 7;
        int len = (int) Math.pow(3, m);
        long[][] dp = new long[n][len];
        boolean[] isOk = new boolean[len];
        int[][] arr = new int[len][];
        for (int i = 0; i < len; i++) {
            int num = i;
            int[] tmp = new int[m];
            int sta = 0;
            while (num != 0) {
                tmp[sta++] = num % 3;
                num /= 3;
            }
            arr[i] = tmp;
            for (int j = 1; j < m; j++)
                if (tmp[j] == tmp[j - 1]) {
                    dp[0][i]--;
                    isOk[i] = true;
                    break;
                }
            dp[0][i]++;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < len; j++) {
                if (isOk[j]) continue;
                long jia = dp[i - 1][j];
                int num = j;
                int[] temarr = arr[j];
                for (int k = 0; k < len; k++) {
                    if (isOk[k]) continue;
                    int numnum = k;
                    int[] temtemarr = arr[k];
                    for (int a = 0; a < m; a++) {
                        if (temtemarr[a] == temarr[a]) {
                            dp[i][k] -= jia % mod;
                            break;
                        }
                    }
                    dp[i][k] += jia % mod;
                }
            }
        }
        long sum = 0;
        for (int i = 0; i < len; i++)
            sum = (sum + dp[n - 1][i]) % mod;
        return (int) sum;
    }

    public int colorTheGrid_v2(int m, int n) {
        // 每一列可能的状态总数 每个单元有3可能
        int totalState = (int) Math.pow(3, m);

        // pre[k] 代表前一轮dp 状态为k 的方案总数
        int[] pre = new int[totalState];

        // 初始化合法填色 的 方案数为1
        for (int state = 0; state < totalState; ++state) {
            if (checkCol(state, m)) {
                pre[state] = 1;
            }
        }
        for (int i = 1; i < n; ++i) {
            int[] cur = new int[totalState];
            // 枚举可能的转移
            for (int preState = 0; preState < totalState; ++preState) {
                if (pre[preState] == 0) continue;
                for (int curState = 0; curState < totalState; ++curState) {
                    // 行和列的填色都满足题设
                    if (checkCol(curState, m) && checkRow(preState, curState, m)) {
                        cur[curState] = addWithMode(cur[curState], pre[preState]);
                    }
                }
            }
            pre = cur;
        }
        int planNum = 0;
        for (int state = 0; state < totalState; ++state) {
            planNum = addWithMode(planNum, pre[state]);
        }
        return planNum;
    }

    private boolean checkCol(int state, int m) {
        int prevCeilColor = -1;
        while (m-- > 0) {
            if (state % 3 == prevCeilColor) {
                return false;
            }
            prevCeilColor = state % 3;
            state /= 3;
        }
        return true;
    }

    private boolean checkRow(int pre, int cur, int m) {
        while (m-- > 0) {
            if (pre % 3 == cur % 3) return false;
            pre /= 3;
            cur /= 3;
        }
        return true;
    }

    private int addWithMode(int a, int b) {
        int mode = (int) 1E9 + 7;
        return (a + b) % mode;
    }
}
