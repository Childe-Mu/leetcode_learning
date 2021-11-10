package com.moon.leetcode;

import static java.lang.Math.max;

/**
 * 1262. 可被三整除的最大和
 * <p>
 * /**
 * p>给你一个整数数组&nbsp;<code>nums</code>，请你找出并返回能被三整除的元素最大和。</p>
 *
 * <ol>
 * </ol>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>nums = [3,6,5,1,8]
 * <strong>输出：</strong>18
 * <strong>解释：</strong>选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>nums = [4]
 * <strong>输出：</strong>0
 * <strong>解释：</strong>4 不能被 3 整除，所以无法选出数字，返回 0。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre><strong>输入：</strong>nums = [1,2,3,4,4]
 * <strong>输出：</strong>12
 * <strong>解释：</strong>选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 4 * 10^4</code></li>
 * <li><code>1 &lt;= nums[i] &lt;= 10^4</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>贪心</li><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 150</li><li>👎 0</li></div>
 */
public class No1262_maxSumDivThree {
    public static void main(String[] args) {
        System.out.println(new No1262_maxSumDivThree().maxSumDivThree_v1(new int[]{1, 2, 3, 4, 4}));
    }

    public int maxSumDivThree_v1(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][3];
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        dp[0][2] = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            if (nums[i - 1] % 3 == 0) {
                dp[i][0] = max(dp[i - 1][0], dp[i - 1][0] + nums[i - 1]);
                dp[i][1] = max(dp[i - 1][1], dp[i - 1][1] + nums[i - 1]);
                dp[i][2] = max(dp[i - 1][2], dp[i - 1][2] + nums[i - 1]);
            } else if (nums[i - 1] % 3 == 1) {
                dp[i][0] = max(dp[i - 1][0], dp[i - 1][2] + nums[i - 1]);
                dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] + nums[i - 1]);
                dp[i][2] = max(dp[i - 1][2], dp[i - 1][1] + nums[i - 1]);
            } else if (nums[i - 1] % 3 == 2) {
                dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + nums[i - 1]);
                dp[i][1] = max(dp[i - 1][1], dp[i - 1][2] + nums[i - 1]);
                dp[i][2] = max(dp[i - 1][2], dp[i - 1][0] + nums[i - 1]);
            }
        }
        return dp[n][0];
    }

    public int maxSumDivThree_v2(int[] nums) {
        int[] remainder = new int[3];
        for (int num : nums) {
            int a, b, c;
            a = remainder[0] + num;
            b = remainder[1] + num;
            c = remainder[2] + num;
            remainder[a % 3] = max(remainder[a % 3], a);
            remainder[b % 3] = max(remainder[b % 3], b);
            remainder[c % 3] = max(remainder[c % 3], c);
        }
        return remainder[0];
    }
}
