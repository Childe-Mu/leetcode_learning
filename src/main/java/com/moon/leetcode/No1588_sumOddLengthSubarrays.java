package com.moon.leetcode;

/**
 * 1588. 所有奇数长度子数组的和
 * <p>
 * /**
 * <p>给你一个正整数数组&nbsp;<code>arr</code>&nbsp;，请你计算所有可能的奇数长度子数组的和。</p>
 *
 * <p><strong>子数组</strong> 定义为原数组中的一个连续子序列。</p>
 *
 * <p>请你返回 <code>arr</code>&nbsp;中 <strong>所有奇数长度子数组的和</strong> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>arr = [1,4,2,5,3]
 * <strong>输出：</strong>58
 * <strong>解释：</strong>所有奇数长度子数组和它们的和为：
 * [1] = 1
 * [4] = 4
 * [2] = 2
 * [5] = 5
 * [3] = 3
 * [1,4,2] = 7
 * [4,2,5] = 11
 * [2,5,3] = 10
 * [1,4,2,5,3] = 15
 * 我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>arr = [1,2]
 * <strong>输出：</strong>3
 * <strong>解释：</strong>总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。</pre>
 * <p>
 * p><strong>示例 3：</strong></p>
 * <p>
 * pre><strong>输入：</strong>arr = [10,11,12]
 * <strong>输出：</strong>66
 * </pre>
 * <p>
 * p>&nbsp;</p>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= arr.length &lt;= 100</code></li>
 * <li><code>1 &lt;= arr[i] &lt;= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>前缀和</li></div></div><br><div><li>👍 126</li><li>👎 0</li></div>
 */
public class No1588_sumOddLengthSubarrays {
    public static void main(String[] args) {
        System.out.println(new No1588_sumOddLengthSubarrays().sumOddLengthSubarrays(new int[]{1, 4, 2, 5, 3}));
    }

    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        int ans = 0;
        for (int i = 1; i <= n; i += 2) {
            for (int j = 0; j <= n - i; j++) {
                ans += sum[j + i] - sum[j];
            }
        }
        return ans;
    }

}
