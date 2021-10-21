package com.moon.leetcode;

/**
 * 453. 最小操作次数使数组元素相等
 * <p>
 * <p>给你一个长度为 <code>n</code> 的整数数组，每次操作将会使 <code>n - 1</code> 个元素增加 <code>1</code> 。返回让数组所有元素相等的最小操作次数。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3]
 * <strong>输出：</strong>3
 * <strong>解释：</strong>
 * 只需要3次操作（注意每次操作会增加两个元素的值）：
 * [1,2,3]  =&gt;  [2,3,3]  =&gt;  [3,4,3]  =&gt;  [4,4,4]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <p>
 * pre>
 * <strong>输入：</strong>nums = [1,1,1]
 * <strong>输出：</strong>0
 * </pre>
 * <p>
 * p>&nbsp;</p>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>n == nums.length</code></li>
 * <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
 * <li>答案保证符合 <strong>32-bit</strong> 整数</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>数学</li></div></div><br><div><li>👍 346</li><li>👎 0</li></div>
 */
public class No453_minMoves {
    public int minMoves_v1(int[] nums) {
        int n = nums.length;
        int min = nums[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, nums[i]);
        }
        int ans = 0;
        for (int num : nums) {
            ans += num - min;
        }
        return ans;
    }

    public int minMoves_v2(int[] nums) {
        int n = nums.length;
        long min = nums[0], sum = 0;
        for (int i : nums) {
            min = Math.min(min, i);
            sum += i;
        }
        return (int) (sum - min * n);
    }
}
