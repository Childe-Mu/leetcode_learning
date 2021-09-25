package com.moon.leetcode;

/**
 * 326. 3的幂
 * <p>
 * /**
 * <p>给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
 *
 * <p>整数 <code>n</code> 是 3 的幂次方需满足：存在整数 <code>x</code> 使得 <code>n == 3<sup>x</sup></code></p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 27
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 0
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 9
 * <strong>输出：</strong>true
 * </pre>
 * <p>
 * p><strong>示例 4：</strong></p>
 * <p>
 * pre>
 * <strong>输入：</strong>n = 45
 * <strong>输出：</strong>false
 * </pre>
 * <p>
 * p> </p>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>-2<sup>31</sup> <= n <= 2<sup>31</sup> - 1</code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong></p>
 *
 * <ul>
 * <li>你能不使用循环或者递归来完成本题吗？</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>递归</li><li>数学</li></div></div><br><div><li>👍 196</li><li>👎 0</li></div>
 */
public class No326_isPowerOfThree {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
