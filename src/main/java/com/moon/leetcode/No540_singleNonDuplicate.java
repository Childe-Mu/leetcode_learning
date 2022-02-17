package com.moon.leetcode;

/**
 * 540.有序数组中的单一元素
 * <p>给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。</p>
 *
 * <p>请你找出并返回只出现一次的那个数。</p>
 *
 * <p>你设计的解决方案必须满足 <code>O(log n)</code> 时间复杂度和 <code>O(1)</code> 空间复杂度。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums = [1,1,2,3,3,4,4,8,8]
 * <strong>输出:</strong> 2
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums =  [3,3,7,7,10,11,11]
 * <strong>输出:</strong> 10
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><meta charset="UTF-8" /></p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>0 &lt;= nums[i]&nbsp;&lt;= 10<sup>5</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 464</li><li>👎 0</li></div>
 */
public class No540_singleNonDuplicate {

    public int singleNonDuplicate_v1(int[] nums) {
        int ans = 0;
        for(int n : nums) {
            ans ^= n;
        }
        return ans;
    }
    public int singleNonDuplicate_v2(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int m = (r - l) / 2 + l;
            if ((m & 1) == 0) {
                if (m < r && nums[m] == nums[m + 1]) {
                    l = m + 1;
                } else {
                    r = m;
                }
            } else {
                if (m > 0 && nums[m] == nums[m - 1]) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
        }
        return nums[r];
    }

    public int singleNonDuplicate_v3(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] == nums[mid ^ 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[r];
    }
}
