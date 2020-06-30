package com.moon.leetcode;

import java.util.Arrays;

/**
 * 1046. 最后一块石头的重量<br/>
 * 有一堆石头，每块石头的重量都是正整数。<br/>
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，
 * 且 x <= y。那么粉碎的可能结果如下：<br/>
 * 如果 x == y，那么两块石头都会被完全粉碎；<br/>
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。<br/>
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。<br/>
 * <br/>
 * 示例：<br/>
 * 输入：[2,7,4,1,8,1]<br/>
 * 输出：1<br/>
 * 解释：<br/>
 * 先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，<br/>
 * 再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，<br/>
 * 接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，<br/>
 * 最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。<br/>
 * <br/>
 * 提示：<br/>
 * 1 <= stones.length <= 30<br/>
 * 1 <= stones[i] <= 1000<br/>
 */
public class No1046_lastStoneWeight {
    // /**
    //  * 利用优先队列
    //  */
    // public static int lastStoneWeight(int[] stones) {
    //     PriorityQueue<Integer> heap = new PriorityQueue<>(stones.length, (o1, o2) -> o2 - o1);
    //     for (int stone : stones) {
    //         heap.offer(stone);
    //     }
    //     while (heap.size() >= 2) {
    //         heap.offer(heap.poll() - heap.poll());
    //     }
    //     if (heap.size() == 0) {
    //         return 0;
    //     } else {
    //         return heap.poll();
    //     }
    // }

    /**
     * 不断对数组进行排序，每次排完序，都是从小到大
     */
    public static int lastStoneWeight(int[] stones) {
        int index = stones.length - 1;
        //通过stones.length来判断需要操作的次数。（不用将stones.length == 1的情况单独考虑）
        for (int i = 0; i < stones.length - 1; i++) {
            //将sort放在循环体的开始。（避免在循环体外再写一次重复的sort（））
            Arrays.sort(stones);
            //两种不同情况使用同一表达式处理。（）
            stones[index] -= stones[index - 1];
            stones[index - 1] = 0;
        }
        return stones[stones.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1, 1}));
    }
}
