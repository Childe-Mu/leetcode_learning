package com.moon.leetcode;

// 1894. 找到需要补充粉笔的学生编号
//
//一个班级里有 n 个学生，编号为 0 到 n - 1 。每个学生会依次回答问题，编号为 0 的学生先回答，然后是编号为 1 的学生，以此类推，直到编号为 n
// - 1 的学生，然后老师会重复这个过程，重新从编号为 0 的学生开始回答问题。
//
// 给你一个长度为 n 且下标从 0 开始的整数数组 chalk 和一个整数 k 。一开始粉笔盒里总共有 k 支粉笔。当编号为 i 的学生回答问题时，他会消耗
// chalk[i] 支粉笔。如果剩余粉笔数量 严格小于 chalk[i] ，那么学生 i 需要 补充 粉笔。
//
// 请你返回需要 补充 粉笔的学生 编号 。
//
//
//
// 示例 1：
//
// 输入：chalk = [5,1,5], k = 22
//输出：0
//解释：学生消耗粉笔情况如下：
//- 编号为 0 的学生使用 5 支粉笔，然后 k = 17 。
//- 编号为 1 的学生使用 1 支粉笔，然后 k = 16 。
//- 编号为 2 的学生使用 5 支粉笔，然后 k = 11 。
//- 编号为 0 的学生使用 5 支粉笔，然后 k = 6 。
//- 编号为 1 的学生使用 1 支粉笔，然后 k = 5 。
//- 编号为 2 的学生使用 5 支粉笔，然后 k = 0 。
//编号为 0 的学生没有足够的粉笔，所以他需要补充粉笔。
//
// 示例 2：
//
// 输入：chalk = [3,4,1,2], k = 25
//输出：1
//解释：学生消耗粉笔情况如下：
//- 编号为 0 的学生使用 3 支粉笔，然后 k = 22 。
//- 编号为 1 的学生使用 4 支粉笔，然后 k = 18 。
//- 编号为 2 的学生使用 1 支粉笔，然后 k = 17 。
//- 编号为 3 的学生使用 2 支粉笔，然后 k = 15 。
//- 编号为 0 的学生使用 3 支粉笔，然后 k = 12 。
//- 编号为 1 的学生使用 4 支粉笔，然后 k = 8 。
//- 编号为 2 的学生使用 1 支粉笔，然后 k = 7 。
//- 编号为 3 的学生使用 2 支粉笔，然后 k = 5 。
//- 编号为 0 的学生使用 3 支粉笔，然后 k = 2 。
//编号为 1 的学生没有足够的粉笔，所以他需要补充粉笔。
//
//
//
//
// 提示：
//
//
// chalk.length == n
// 1 <= n <= 105
// 1 <= chalk[i] <= 105
// 1 <= k <= 109
//
// Related Topics 贪心算法 数组
// 👍 3 👎 0
public class No1894_chalkReplacer {
    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        long sum = 0;
        for (int i : chalk) {
            sum += i;
        }
        long mod = k % sum;
        int i = 0;
        while (mod > 0) {
            mod -= chalk[i];
            if (mod < 0) {
                return i;
            }
            i = (i + 1) % n;
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println(new No1894_chalkReplacer().chalkReplacer_v2(new int[]{5, 1, 5}, 22));
    }

    public int chalkReplacer_v2(int[] chalk, int k) {
        int n = chalk.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + chalk[i];
            if (k <= sum[i + 1]) {
                return k == sum[i + 1] ? (i + 1) % n : i;
            }
        }
        int mod = k % sum[n];
        int l = 1, r = n;
        while (l < r) {
            int m = (r - l) / 2 + l;
            if (sum[m] < mod) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return mod == sum[r] ? r % n : r - 1;
    }
}
