package com.moon.leetcode;

/**
 * 441. 排列硬币
 * <p>
 * <p>你总共有&nbsp;<code>n</code><em>&nbsp;</em>枚硬币，并计划将它们按阶梯状排列。对于一个由 <code>k</code> 行组成的阶梯，其第 <code>i</code><em> </em>行必须正好有 <code>i</code><em> </em>枚硬币。阶梯的最后一行 <strong>可能</strong> 是不完整的。</p>
 *
 * <p>给你一个数字&nbsp;<code>n</code><em> </em>，计算并返回可形成 <strong>完整阶梯行</strong> 的总行数。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/04/09/arrangecoins1-grid.jpg" style="width: 253px; height: 253px;" />
 * <pre>
 * <strong>输入：</strong>n = 5
 * <strong>输出：</strong>2
 * <strong>解释：</strong>因为第三行不完整，所以返回 2 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/04/09/arrangecoins2-grid.jpg" style="width: 333px; height: 333px;" />
 * <pre>
 * <strong>输入：</strong>n = 8
 * <strong>输出：</strong>3
 * <strong>解释：</strong>因为第四行不完整，所以返回 3 。
 * </pre>
 * <p>
 * p>&nbsp;</p>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数学</li><li>二分查找</li></div></div><br><div><li>👍 173</li><li>👎 0</li></div>
 */
public class No441_arrangeCoins {
    // x^2+x-2*n = 0;
    public int arrangeCoins_v1(int n) {
        return (int) ((-1 + Math.sqrt(1 + (long) 8 * n)) / 2.0);
    }

    public int arrangeCoins_v2(int n) {
        int l = 1, r = n;
        while (l < r) {
            int m = (r - l + 1) / 2 + l;
            if ((long) m * (m + 1) <= (long) 2 * n) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }
}
