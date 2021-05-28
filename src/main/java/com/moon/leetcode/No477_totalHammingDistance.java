package com.moon.leetcode;

// 477. 汉明距离总和
//
//两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
//
// 计算一个数组中，任意两个数之间汉明距离的总和。
//
// 示例:
//
//输入: 4, 14, 2
//
//输出: 6
//
//解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系）
//所以答案为：
//HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 +
//2 + 2 = 6.
//
// 注意:
//
// 数组中元素的范围为从 0到 10^9。
// 数组的长度不超过 10^4。
//
// Related Topics 位运算
// 👍 170 👎 0
public class No477_totalHammingDistance {
    public static void main(String[] args) {
        System.out.println(new No477_totalHammingDistance().totalHammingDistance_v2(new int[]{4, 14, 2}));
    }

    public int totalHammingDistance_v1(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = nums[i] ^ nums[j];
                while (tmp > 0) {
                    tmp = tmp & (tmp - 1);
                    sum++;
                }
            }
        }
        return sum;
    }

    public int totalHammingDistance_v2(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 31; i >= 0; i--) {
            int cnt = 0;
            for (int num : nums) {
                cnt += (num >> i) & 1;
            }
            sum += cnt * (n - cnt);
        }
        return sum;
    }
}

