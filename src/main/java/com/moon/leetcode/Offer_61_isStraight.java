package com.moon.leetcode;

// 剑指 Offer 61. 扑克牌中的顺子
//
//从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任
//意数字。A 不能视为 14。
//
// 示例 1:
//
// 输入: [1,2,3,4,5]
//输出: True
//
// 示例 2:
//
// 输入: [0,0,1,2,4]
//输出: True
//
// 限制：
//
// 数组长度为 5
//
// 数组的数取值为 [0, 13] .
// 👍 120 👎 0
public class Offer_61_isStraight {
    public static void main(String[] args) {
        System.out.println(new Offer_61_isStraight().isStraight(new int[]{1, 2, 3, 4, 5}));
    }

    public boolean isStraight(int[] nums) {
        int min = 14, max = -1;
        int[] count = new int[14];
        for (int num : nums) {
            if (num == 0) {
                count[num]++;
            } else {
                if (++count[num] > 1) {
                    return false;
                }
                min = Math.min(num, min);
                max = Math.max(num, max);
            }
        }
        // 右边表示，最大值和最小值之间差了多少个0来填补空缺,
        // return count[0] >= (max - min - 1) - (4 - count[0] - 1);
        // 简化以后为
        return max - min <= 4;
    }
}
