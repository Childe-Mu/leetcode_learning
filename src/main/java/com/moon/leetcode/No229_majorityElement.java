package com.moon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 229. 求众数 II
 * <p>
 * /**
 * <p>给定一个大小为&nbsp;<em>n&nbsp;</em>的整数数组，找出其中所有出现超过&nbsp;<code>⌊ n/3 ⌋</code>&nbsp;次的元素。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>[3,2,3]
 * <strong>输出：</strong>[3]</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1]
 * <strong>输出：</strong>[1]
 * </pre>
 * <p>
 * p><strong>示例 3：</strong></p>
 * <p>
 * pre>
 * <strong>输入：</strong>[1,1,1,3,3,2,2,2]
 * <strong>输出：</strong>[1,2]</pre>
 * <p>
 * p>&nbsp;</p>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
 * <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>计数</li><li>排序</li></div></div><br><div><li>👍 494</li><li>👎 0</li></div>
 */
public class No229_majorityElement {

    public List<Integer> majorityElement_v2(int[] nums) {
        int element1 = 0;
        int element2 = 0;
        int vote1 = 0;
        int vote2 = 0;

        for (int num : nums) {
            if (vote1 > 0 && num == element1) { //如果该元素为第一个元素，则计数加1
                vote1++;
            } else if (vote2 > 0 && num == element2) { //如果该元素为第二个元素，则计数加1
                vote2++;
            } else if (vote1 == 0) { // 选择第一个元素
                element1 = num;
                vote1++;
            } else if (vote2 == 0) { // 选择第二个元素
                element2 = num;
                vote2++;
            } else { //如果三个元素均不相同，则相互抵消1次
                vote1--;
                vote2--;
            }
        }

        int cnt1 = 0;
        int cnt2 = 0;
        for (int num : nums) {
            if (vote1 > 0 && num == element1) {
                cnt1++;
            }
            if (vote2 > 0 && num == element2) {
                cnt2++;
            }
        }
        // 检测元素出现的次数是否满足要求
        List<Integer> ans = new ArrayList<>();
        if (vote1 > 0 && cnt1 > nums.length / 3) {
            ans.add(element1);
        }
        if (vote2 > 0 && cnt2 > nums.length / 3) {
            ans.add(element2);
        }

        return ans;
    }
}
