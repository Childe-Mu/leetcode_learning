package com.moon.leetcode;

import java.util.HashSet;

/**
 * 874. 模拟行走机器人<br/>
 * 机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令：<br/>
 * -2：向左转 90 度<br/>
 * -1：向右转 90 度<br/>
 * 1 <= x <= 9：向前移动 x 个单位长度<br/>
 * 在网格上有一些格子被视为障碍物。<br/>
 * 第 i 个障碍物位于网格点  (obstacles[i][0], obstacles[i][1])<br/>
 * 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。<br/>
 * 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。<br/>
 * <br/>
 * 示例 1：<br/>
 * 输入: commands = [4,-1,3], obstacles = []<br/>
 * 输出: 25<br/>
 * 解释: 机器人将会到达 (3, 4)<br/>
 * <br/>
 * 示例 2：<br/>
 * 输入: commands = [4,-1,4,-2,4], obstacles = [[2,4]]<br/>
 * 输出: 65<br/>
 * 解释: 机器人在左转走到 (1, 8) 之前将被困在 (1, 4) 处<br/>
 * <br/>
 * 提示：<br/>
 * <code>
 * 0 <= commands.length <= 10000<br/>
 * 0 <= obstacles.length <= 10000<br/>
 * -30000 <= obstacle[i][0] <= 30000<br/>
 * -30000 <= obstacle[i][1] <= 30000<br/>
 * 答案保证小于 2 ^ 31</code>
 */
public class No874_robotSim {
    /**
     * 相较于下面的解法，此解法慢在changeVector方法，但旋转角度如果不是90°，则一定需要通过三角函数计算旋转后的方向向量，
     */
    public static int robotSim(int[] commands, int[][] obstacles) {
        //将所有障碍物坐标组合成字符串存入set中方便查询
        HashSet<String> set = new HashSet<>();
        for (int[] arr : obstacles) {
            set.add(arr[0] + "-" + arr[1]);
        }
        // 方向向量
        int[] vector = new int[]{0, 1};
        // 保存路径终点
        int[] path = new int[]{0, 0};
        // 最大欧式距离的平方
        int result = 0;
        for (int command : commands) {
            if (command < 0) {
                changeVector(command, vector);
            } else {
                for (int i = 0; i < command; i++) {
                    // 每循环一次，就朝着方向向量前进一步
                    int x = vector[0] + path[0];
                    int y = vector[1] + path[1];
                    // 若下一步有障碍物，结束当前命令，跳至下一命令
                    if (set.contains(x + "-" + y)) {
                        break;
                    }
                    // 否则更新坐标与最远距离
                    path[0] = x;
                    path[1] = y;
                    result = Math.max(result, x * x + y * y);
                }
            }
        }
        return result;
    }

    /**
     * 计算方向向量
     * 将一个向量旋转 Θ°，可根据三角函数计算
     */
    private static void changeVector(int command, int[] vector) {
        if (command == -1) {
            int temp = vector[0];
            vector[0] = vector[1];
            vector[1] = -temp;
        } else {
            int temp = vector[0];
            vector[0] = -vector[1];
            vector[1] = temp;
        }
    }

    // public static int robotSim(int[] commands, int[][] obstacles) {
    //     //direction表当前朝向，0123 表 北东南西
    //     int ans = 0, direction = 0, x = 0, y = 0;
    //     //每个朝向上的数据变化，比如朝北时取Direction[0]  ->   {0,1}
    //     //那么x轴的变化为x+0，y轴变化为y+1;
    //     int[][] Direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    //
    //     HashSet<String> set = new HashSet<>();
    //     //将所有障碍物坐标组合成字符串存入set中方便查询
    //     for (int[] arr : obstacles) {
    //         set.add(arr[0] + "，" + arr[1]);
    //     }
    //
    //     for (int com : commands) {
    //         //定义下一步的坐标
    //         int next_x = 0, next_y = 0;
    //         //当命令为前进，开始移动
    //         if (com >= 0) {
    //             for (int i = 0; i < com; i++) {
    //                 //取得下一步的坐标
    //                 next_x = x + Direction[direction][0];
    //                 next_y = y + Direction[direction][1];
    //                 //若下一步有障碍物，结束当前命令，跳至下一命令
    //                 if (set.contains(next_x + "，" + next_y))
    //                     break;
    //                 //否则更新坐标与最远距离
    //                 x = next_x;
    //                 y = next_y;
    //                 ans = Math.max(ans, x * x + y * y);
    //             }
    //         } else {
    //             //改变朝向
    //             direction = com == -1 ? (direction + 1) % 4 : (direction + 3) % 4;
    //         }
    //     }
    //     return ans;
    // }

    public static void main(String[] args) {
        System.out.println(robotSim(new int[]{4,-1,4,-2,4}, new int[][]{{2,4}}));
    }
}
