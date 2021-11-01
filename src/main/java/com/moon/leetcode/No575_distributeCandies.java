package com.moon.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 575. 分糖果
 * <p>
 * <p>给定一个<strong>偶数</strong>长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。你需要把这些糖果<strong>平均</strong>分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> candies = [1,1,2,2,3,3]
 * <strong>输出:</strong> 3
 * <strong>解析: </strong>一共有三种种类的糖果，每一种都有两个。
 * 最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
 * </pre>
 *
 * <p><strong>示例 2 :</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> candies = [1,1,2,3]
 * <strong>输出:</strong> 2
 * <strong>解析:</strong> 妹妹获得糖果[2,3],弟弟获得糖果[1,1]，妹妹有两种不同的糖果，弟弟只有一种。这样使得妹妹可以获得的糖果种类数最多。
 * </pre>
 * <p>
 * p><strong>注意:</strong></p>
 *
 * <ol>
 * <li>数组的长度为[2, 10,000]，并且确定为偶数。</li>
 * <li>数组中数字的大小在范围[-100,000, 100,000]内。
 * <ol>
 * </ol>
 * </li>
 * </ol>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li></div></div><br><div><li>👍 145</li><li>👎 0</li></div>
 */

public class No575_distributeCandies {
    public int distributeCandies(int[] candyType) {
        int total = candyType.length;
        Set<Integer> set = new HashSet<>();
        for (int candy : candyType) {
            set.add(candy);
        }
        return Math.min(total / 2, set.size());
    }
}
