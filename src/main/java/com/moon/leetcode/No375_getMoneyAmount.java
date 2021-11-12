package com.moon.leetcode;

/**
 * 375. 猜数字大小 II
 * <p>
 * <p>我们正在玩一个猜数游戏，游戏规则如下：</p>
 *
 * <ol>
 * <li>我从&nbsp;<code>1</code><strong>&nbsp;</strong>到 <code>n</code> 之间选择一个数字。</li>
 * <li>你来猜我选了哪个数字。</li>
 * <li>如果你猜到正确的数字，就会 <strong>赢得游戏</strong> 。</li>
 * <li>如果你猜错了，那么我会告诉你，我选的数字比你的 <strong>更大或者更小</strong> ，并且你需要继续猜数。</li>
 * <li>每当你猜了数字 <code>x</code> 并且猜错了的时候，你需要支付金额为 <code>x</code> 的现金。如果你花光了钱，就会<strong> 输掉游戏</strong> 。</li>
 * </ol>
 *
 * <p>给你一个特定的数字 <code>n</code> ，返回能够 <strong>确保你获胜</strong> 的最小现金数，<strong>不管我选择那个数字</strong> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/10/graph.png" style="width: 505px; height: 388px;" />
 * <pre>
 * <strong>输入：</strong>n = 10
 * <strong>输出：</strong>16
 * <strong>解释：</strong>制胜策略如下：
 * - 数字范围是 [1,10] 。你先猜测数字为 7 。
 * &nbsp;   - 如果这是我选中的数字，你的总费用为 $0 。否则，你需要支付 $7 。
 * &nbsp;   - 如果我的数字更大，则下一步需要猜测的数字范围是 [8,10] 。你可以猜测数字为 9 。
 * &nbsp;       - 如果这是我选中的数字，你的总费用为 $7 。否则，你需要支付 $9 。
 * &nbsp;       - 如果我的数字更大，那么这个数字一定是 10 。你猜测数字为 10 并赢得游戏，总费用为 $7 + $9 = $16 。
 * &nbsp;       - 如果我的数字更小，那么这个数字一定是 8 。你猜测数字为 8 并赢得游戏，总费用为 $7 + $9 = $16 。
 * &nbsp;   - 如果我的数字更小，则下一步需要猜测的数字范围是 [1,6] 。你可以猜测数字为 3 。
 * &nbsp;       - 如果这是我选中的数字，你的总费用为 $7 。否则，你需要支付 $3 。
 * &nbsp;       - 如果我的数字更大，则下一步需要猜测的数字范围是 [4,6] 。你可以猜测数字为 5 。
 * &nbsp;           - 如果这是我选中的数字，你的总费用为 $7 + $3 = $10 。否则，你需要支付 $5 。
 * &nbsp;           - 如果我的数字更大，那么这个数字一定是 6 。你猜测数字为 6 并赢得游戏，总费用为 $7 + $3 + $5 = $15 。
 * &nbsp;           - 如果我的数字更小，那么这个数字一定是 4 。你猜测数字为 4 并赢得游戏，总费用为 $7 + $3 + $5 = $15 。
 * &nbsp;       - 如果我的数字更小，则下一步需要猜测的数字范围是 [1,2] 。你可以猜测数字为 1 。
 * &nbsp;           - 如果这是我选中的数字，你的总费用为 $7 + $3 = $10 。否则，你需要支付 $1 。
 * &nbsp;           - 如果我的数字更大，那么这个数字一定是 2 。你猜测数字为 2 并赢得游戏，总费用为 $7 + $3 + $1 = $11 。
 * 在最糟糕的情况下，你需要支付 $16 。因此，你只需要 $16 就可以确保自己赢得游戏。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 1
 * <strong>输出：</strong>0
 * <strong>解释：</strong>只有一个可能的数字，所以你可以直接猜 1 并赢得游戏，无需支付任何费用。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 2
 * <strong>输出：</strong>1
 * <strong>解释：</strong>有两个可能的数字 1 和 2 。
 * - 你可以先猜 1 。
 * &nbsp;   - 如果这是我选中的数字，你的总费用为 $0 。否则，你需要支付 $1 。
 * &nbsp;   - 如果我的数字更大，那么这个数字一定是 2 。你猜测数字为 2 并赢得游戏，总费用为 $1 。
 * 最糟糕的情况下，你需要支付 $1 。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= n &lt;= 200</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数学</li><li>动态规划</li><li>博弈</li></div></div><br><div><li>👍 337</li><li>👎 0</li></div>
 */
public class No375_getMoneyAmount {
    static int m = 200;
    static int[][] dp = new int[m + 1][m + 1];

    static {
        for (int i = 2; i <= m; i++) {
            for (int j = i - 1; j >= 1; j--) {
                dp[j][i] = Integer.MAX_VALUE;
                for (int k = j; k < i; k++) {
                    dp[j][i] = Math.min(dp[j][i], k + Math.max(dp[j][k - 1], dp[k + 1][i]));
                }
            }
        }
    }

    int nn = 210;
    int[][] cache = new int[nn][nn];

    public static void main(String[] args) {
        System.out.println(new No375_getMoneyAmount().getMoneyAmount_v2(10));
    }

    public int getMoneyAmount_v1(int n) {
        return dfs(1, n);
    }

    int dfs(int l, int r) {
        if (l >= r) {
            return 0;
        }
        if (cache[l][r] != 0) {
            return cache[l][r];
        }
        int ans = Integer.MAX_VALUE;
        for (int x = l; x <= r; x++) {
            // 当选择的数位 x 时，至少需要 cur 才能猜中数字
            int cur = Math.max(dfs(l, x - 1), dfs(x + 1, r)) + x;
            // 在所有我们可以决策的数值之间取最优
            ans = Math.min(ans, cur);
        }
        cache[l][r] = ans;
        return ans;
    }

    public int getMoneyAmount_v2(int n) {
        int[][] f = new int[n + 1][n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = i - 1; j >= 1; j--) {
                f[j][i] = Integer.MAX_VALUE;
                for (int k = j; k < i; k++) {
                    f[j][i] = Math.min(f[j][i], k + Math.max(f[j][k - 1], f[k + 1][i]));
                }
            }
        }
        return f[1][n];
    }

    public int getMoneyAmount_v3(int n) {
        return dp[1][n];
    }
}
