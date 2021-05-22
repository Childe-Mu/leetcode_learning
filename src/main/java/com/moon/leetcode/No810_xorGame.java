package com.moon.leetcode;

// 810. 黑板异或游戏
//
//黑板上写着一个非负整数数组 nums[i] 。Alice 和 Bob 轮流从黑板上擦掉一个数字，Alice 先手。如果擦除一个数字后，剩余的所有数字按位异或
//运算得出的结果等于 0 的话，当前玩家游戏失败。 (另外，如果只剩一个数字，按位异或运算得到它本身；如果无数字剩余，按位异或运算结果为 0。）
//
// 换种说法就是，轮到某个玩家时，如果当前黑板上所有数字按位异或运算结果等于 0，这个玩家获胜。
//
// 假设两个玩家每步都使用最优解，当且仅当 Alice 获胜时返回 true。
//
// 示例：
//
//输入: nums = [1, 1, 2]
//输出: false
//解释:
//Alice 有两个选择: 擦掉数字 1 或 2。
//如果擦掉 1, 数组变成 [1, 2]。剩余数字按位异或得到 1 XOR 2 = 3。那么 Bob 可以擦掉任意数字，因为 Alice 会成为擦掉最后一个数
//字的人，她总是会输。
//如果 Alice 擦掉 2，那么数组变成[1, 1]。剩余数字按位异或得到 1 XOR 1 = 0。Alice 仍然会输掉游戏。
//
// 提示：
//
// 1 <= N <= 1000
// 0 <= nums[i] <= 2^16
//
// Related Topics 数学
// 👍 83 👎 0
public class No810_xorGame {

    public static void main(String[] args) {
        System.out.println(new No810_xorGame().xorGame(new int[]{1, 0}));
    }

    public boolean xorGame(int[] nums) {
        int xor = 0;
        int n = nums.length;
        boolean ans = (n & 1) == 0;
        for (int num : nums) {
            xor ^= num;
        }
        if (xor == 0) {
            return true;
        }
        return ans;
    }
}
