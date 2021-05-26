package com.moon.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 1787. 使所有区间的异或结果为零
//
//给你一个整数数组 nums 和一个整数 k 。区间 [left, right]（left <= right）的 异或结果 是对下标位于 left 和 rig
//ht（包括 left 和 right ）之间所有元素进行 XOR 运算的结果：nums[left] XOR nums[left+1] XOR ... XOR nums[right] 。
//
// 返回数组中 要更改的最小元素数 ，以使所有长度为 k 的区间异或结果等于零。
//
// 示例 1：
//
//输入：nums = [1,2,0,3,0], k = 1
//输出：3
//解释：将数组 [1,2,0,3,0] 修改为 [0,0,0,0,0]
//
// 示例 2：
//
//输入：nums = [3,4,5,2,1,7,3,4,7], k = 3
//输出：3
//解释：将数组 [3,4,5,2,1,7,3,4,7] 修改为 [3,4,7,3,4,7,3,4,7]
//
// 示例 3：
//
//输入：nums = [1,2,4,1,2,5,1,2,6], k = 3
//输出：3
//解释：将数组[1,2,4,1,2,5,1,2,6] 修改为 [1,2,3,1,2,3,1,2,3]
//
// 提示：
//
// 1 <= k <= nums.length <= 2000
// 0 <= nums[i] < 210
//
// Related Topics 动态规划
// 👍 95 👎 0
public class No1787_minChanges {

    // x 的范围为 [0, 2^10)
    static final int MAXX = 1 << 10;
    // 极大值，为了防止整数溢出选择 INT_MAX / 2
    static final int INFTY = Integer.MAX_VALUE / 2;

    public static void main(String[] args) {
        System.out.println(new No1787_minChanges().minChanges_v3(new int[]{1, 2, 4, 1, 2, 5, 1, 2, 6}, 3));
    }

    public int minChanges_v2(int[] nums, int k) {
        int n = nums.length;
        int max = 1024;
        int[][] f = new int[k][max];
        int[] g = new int[k];
        for (int i = 0; i < k; i++) {
            Arrays.fill(f[i], 0x3f3f3f3f);
            g[i] = 0x3f3f3f3f;
        }
        for (int i = 0, cnt = 0; i < k; i++, cnt = 0) {
            // 使用 map 和 cnt 分别统计当前列的「每个数的出现次数」和「有多少个数」
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i; j < n; j += k) {
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                cnt++;
            }
            // 第 0 列：只需要考虑如何将该列变为 xor 即可
            if (i == 0) {
                for (int xor = 0; xor < max; xor++) {
                    f[0][xor] = Math.min(f[0][xor], cnt - map.getOrDefault(xor, 0));
                    g[0] = Math.min(g[0], f[0][xor]);
                }
            } else {
                // 其他列：考虑与前面列的关系
                for (int xor = 0; xor < max; xor++) {
                    // 整列替换
                    f[i][xor] = g[0] + cnt;
                    // 部分替换
                    for (int cur : map.keySet()) {
                        f[i][xor] = Math.min(f[i][xor], f[i - 1][xor ^ cur] + cnt - map.get(cur));
                    }
                    g[i] = Math.min(g[i], f[i][xor]);
                }
            }
        }
        return f[k - 1][0];
    }

    public int minChanges_v3(int[] nums, int k) {
        int n = nums.length;
        int M = Integer.MAX_VALUE / 2;
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        for (int i = 0; i <= 10; i++) {
            if (max < 1 << i) {
                max = 1 << i;
                break;
            }
        }

        int[] f = new int[max];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            int minTmp = Integer.MAX_VALUE;
            Map<Integer, Integer> map = new HashMap<>();
            int count = 0;
            for (int j = i; j < n; j += k) {
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                count++;
            }
            if (i == 0) {
                for (int j = 0; j < max; j++) {
                    f[j] = count - map.getOrDefault(j, 0);
                    minTmp = Math.min(minTmp, f[j]);
                }
            } else {
                int[] g = new int[max];
                for (int j = 0; j < max; j++) {
                    g[j] = min + count;
                    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                        Integer key = entry.getKey();
                        Integer value = entry.getValue();
                        int xor = j ^ key;
                        g[j] = Math.min(g[j], f[xor] + count - value);
                    }
                    minTmp = Math.min(minTmp, g[j]);
                }
                f = g;
            }
            min = minTmp;
        }
        return f[0];
    }

    public int minChanges_v4(int[] nums, int k) {
        int n = nums.length;
        int[] f = new int[MAXX];
        Arrays.fill(f, INFTY);
        // 边界条件 f(-1,0)=0
        f[0] = 0;

        for (int i = 0; i < k; ++i) {
            // 第 i 个组的哈希映射
            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
            int size = 0;
            for (int j = i; j < n; j += k) {
                cnt.put(nums[j], cnt.getOrDefault(nums[j], 0) + 1);
                ++size;
            }

            // 求出 t2
            int t2min = Arrays.stream(f).min().getAsInt();

            int[] g = new int[MAXX];
            Arrays.fill(g, t2min);
            for (int mask = 0; mask < MAXX; ++mask) {
                // t1 则需要枚举 x 才能求出
                for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                    int x = entry.getKey(), countx = entry.getValue();
                    g[mask] = Math.min(g[mask], f[mask ^ x] - countx);
                }
            }

            // 别忘了加上 size
            for (int j = 0; j < MAXX; ++j) {
                g[j] += size;
            }
            f = g;
        }

        return f[0];
    }
}
