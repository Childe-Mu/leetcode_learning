package com.moon.leetcode;

/**
 * 605. 种花问题
 * <p>
 * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * <p>
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
 * <p>
 * 示例 1:
 * <p>
 * 输入: flowerbed = [1,0,0,0,1], n = 1
 * 输出: True
 * 示例 2:
 * <p>
 * 输入: flowerbed = [1,0,0,0,1], n = 2
 * 输出: False
 * <p>
 * 注意:
 * 数组内已种好的花不会违反种植规则。
 * 输入的数组长度范围为 [1, 20000]。
 * n 是非负整数，且不会超过输入数组的大小。
 */
public class No605_canPlaceFlowers {
    // /**
    //  * 为了避免考虑边界问题，在原来的数组开头和结尾，加上0，就可以通过循环直接判断了
    //  */
    // public static boolean canPlaceFlowers(int[] flowerbed, int n) {
    //     int[] temp = new int[flowerbed.length + 2];
    //     temp[0] = 0;
    //     temp[flowerbed.length + 1] = 0;
    //     for (int i = 1; i < temp.length - 1; i++) {
    //         temp[i] = flowerbed[i - 1];
    //         if (i < flowerbed.length) {
    //             temp[i + 1] = flowerbed[i];
    //         }
    //         if (temp[i] == 0 && temp[i - 1] == 0 && temp[i + 1] == 0) {
    //             temp[i] = 1;
    //             n--;
    //         }
    //         if (n <= 0) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    /**
     * 我们从左到右扫描数组 flowerbed，如果数组中有一个 0，并且这个 0 的左右两侧都是 0，那么我们就可以在这个位置种花，
     * 即将这个位置的 0 修改成 1，并将计数器 count 增加 1。对于数组的第一个和最后一个位置，我们只需要考虑一侧是否为 0。
     * <p>
     * 在扫描结束之后，我们将 count 与 n 进行比较。如果 count >= n，那么返回 True，否则返回 False。
     */
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            // 三个条件并联，第一个判断当前值是否为0，第二个判断是否第一个元素或者左边为0，第三个判断右边是否最后一个或者是否为0
            // 注意，一定要先判断是否为最后一个或者第一个，不然会导致数组下标越界
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                n--;
            }
            if (n <= 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 0, 0, 0, 0, 1};
        System.out.println(canPlaceFlowers(a, 2));
    }
}
