package com.moon.leetcode;

/**
 * 860. 柠檬水找零<br/>
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。<br/>
 * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。<br/>
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。
 * 你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。<br/>
 * 注意，一开始你手头没有任何零钱。<br/>
 * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。<br/>
 * <br/>
 * 示例 1：<br/>
 * 输入：[5,5,5,10,20]<br/>
 * 输出：true<br/>
 * 解释：<br/>
 * 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。<br/>
 * 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。<br/>
 * 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。<br/>
 * 由于所有客户都得到了正确的找零，所以我们输出 true。<br/>
 * <br/>
 * 示例 2：<br/>
 * 输入：[5,5,10]<br/>
 * 输出：true<br/>
 * <br/>
 * 示例 3：<br/>
 * 输入：[10,10]<br/>
 * 输出：false<br/>
 * <br/>
 * 示例 4：<br/>
 * 输入：[5,5,10,10,20]<br/>
 * 输出：false<br/>
 * 解释：<br/>
 * 前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。<br/>
 * 对于接下来的 2 位顾客，我们收取一张 10 美元的钞票，然后返还 5 美元。<br/>
 * 对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。<br/>
 * 由于不是每位顾客都得到了正确的找零，所以答案是 false。<br/>
 * <br/>
 * 提示：<br/>
 * 0 <= bills.length <= 10000<br/>
 * bills[i] 不是 5 就是 10 或是 20 <br/>
 */
public class No860_lemonadeChange {
    // /**
    //  * 用hashmap
    //  */
    // public static boolean lemonadeChange(int[] bills) {
    //     if (bills == null || bills.length == 0) {
    //         return true;
    //     }
    //     Map<Integer, Integer> map = new HashMap<>();
    //     for (int bill : bills) {
    //         if (bill == 5) {
    //             map.put(5, map.getOrDefault(5, 0) + 1);
    //         }
    //         if (bill == 10) {
    //             map.put(10, map.getOrDefault(10, 0) + 1);
    //             int fiveNum = map.getOrDefault(5, 0);
    //             if (fiveNum == 0) {
    //                 return false;
    //             } else {
    //                 map.put(5, fiveNum - 1);
    //             }
    //         }
    //         if (bill == 20) {
    //             map.put(20, map.getOrDefault(20, 0) + 1);
    //             int fiveNum = map.getOrDefault(5, 0);
    //             int tenNum = map.getOrDefault(10, 0);
    //             if (tenNum - 1 >= 0 && fiveNum - 1 >= 0) {
    //                 map.put(5, fiveNum - 1);
    //                 map.put(10, tenNum - 1);
    //             } else if (fiveNum - 3 >= 0) {
    //                 map.put(5, fiveNum - 3);
    //             } else {
    //                 return false;
    //             }
    //         }
    //     }
    //     return true;
    // }

    // /**
    //  * 用数组
    //  */
    // public static boolean lemonadeChange(int[] bills) {
    //     if (bills == null || bills.length == 0) {
    //         return true;
    //     }
    //     int[] count = new int[3];
    //     for (int bill : bills) {
    //         if (bill == 5) {
    //             count[0] = ++count[0];
    //         }
    //         if (bill == 10) {
    //             count[1] = ++count[1];
    //             if (count[0] == 0) {
    //                 return false;
    //             } else {
    //                 count[0] = --count[0];
    //             }
    //         }
    //         if (bill == 20) {
    //             count[2] = ++count[2];
    //             if (count[1] - 1 >= 0 && count[0] - 1 >= 0) {
    //                 count[0] = --count[0];
    //                 count[1] = --count[1];
    //             } else if (count[0] - 3 >= 0) {
    //                 count[0] = count[0] - 3;
    //             } else {
    //                 return false;
    //             }
    //         }
    //     }
    //     return true;
    // }

    /**
     * 前面两种陷入误区，没必要存储20的数量<br/>
     * 整体思路不变<br/>
     * 让我们尝试模拟给每个购买柠檬水的顾客进行找零的过程。最初，我们既没有 5 美元钞票也没有 10 美元钞票。<br/>
     * <br/>
     * 如果顾客支付了 5 美元钞票，那么我们就得到 5 美元的钞票。<br/>
     * <br/>
     * 如果顾客支付了 10 美元钞票，我们必须找回一张 5 美元钞票。如果我们没有 5 美元的钞票，答案就是 False，
     * 因为我们无法正确找零。<br/>
     * <br/>
     * 如果顾客支付了 20 美元钞票，我们必须找回 15 美元。<br/>
     * 如果我们有一张 10 美元和一张 5 美元，那么我们总会更愿意这样找零，这比用三张 5 美元进行找零更有利。<br/>
     * 否则，如果我们有三张 5 美元的钞票，那么我们将这样找零。<br/>
     * 否则，我们将无法给出总面值为 15 美元的零钱，答案是 False 。<br/>
     */
    public static boolean lemonadeChange(int[] bills) {
        if (bills == null || bills.length == 0) {
            return true;
        }
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5)
                five++;
            else if (bill == 10) {
                if (five == 0)
                    return false;
                five--;
                ten++;
            } else {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(lemonadeChange(new int[]{5, 5, 5, 10, 20}));
    }
}
