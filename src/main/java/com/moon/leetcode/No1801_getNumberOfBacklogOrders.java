package com.moon.leetcode;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * 1801. 积压订单中的订单总数
 * <p>
 * 给你一个二维整数数组 orders ，其中每个 orders[i] = [pricei, amounti, orderTypei] 表示有 amounti 笔类型为 orderTypei 、价格为 pricei 的订单。
 * <p>
 * 订单类型 orderTypei 可以分为两种：
 * <p>
 * 0 表示这是一批采购订单 buy
 * 1 表示这是一批销售订单 sell
 * 注意，orders[i] 表示一批共计 amounti 笔的独立订单，这些订单的价格和类型相同。对于所有有效的 i ，由 orders[i] 表示的所有订单提交时间均早于 orders[i+1] 表示的所有订单。
 * <p>
 * 存在由未执行订单组成的 积压订单 。积压订单最初是空的。提交订单时，会发生以下情况：
 * <p>
 * 如果该订单是一笔采购订单 buy ，则可以查看积压订单中价格 最低 的销售订单 sell 。如果该销售订单 sell 的价格 低于或等于 当前采购订单 buy 的价格，则匹配并执行这两笔订单，并将销售订单 sell 从积压订单中删除。否则，采购订单 buy 将会添加到积压订单中。
 * 反之亦然，如果该订单是一笔销售订单 sell ，则可以查看积压订单中价格 最高 的采购订单 buy 。如果该采购订单 buy 的价格 高于或等于 当前销售订单 sell 的价格，则匹配并执行这两笔订单，并将采购订单 buy 从积压订单中删除。否则，销售订单 sell 将会添加到积压订单中。
 * 输入所有订单后，返回积压订单中的 订单总数 。由于数字可能很大，所以需要返回对 109 + 7 取余的结果。
 * <p>
 * 示例 1：
 * <p>
 * 输入：orders = [[10,5,0],[15,2,1],[25,1,1],[30,4,0]]
 * 输出：6
 * 解释：输入订单后会发生下述情况：
 * - 提交 5 笔采购订单，价格为 10 。没有销售订单，所以这 5 笔订单添加到积压订单中。
 * - 提交 2 笔销售订单，价格为 15 。没有采购订单的价格大于或等于 15 ，所以这 2 笔订单添加到积压订单中。
 * - 提交 1 笔销售订单，价格为 25 。没有采购订单的价格大于或等于 25 ，所以这 1 笔订单添加到积压订单中。
 * - 提交 4 笔采购订单，价格为 30 。前 2 笔采购订单与价格最低（价格为 15）的 2 笔销售订单匹配，从积压订单中删除这 2 笔销售订单。第 3 笔采购订单与价格最低的 1 笔销售订单匹配，销售订单价格为 25 ，从积压订单中删除这 1 笔销售订单。积压订单中不存在更多销售订单，所以第 4 笔采购订单需要添加到积压订单中。
 * 最终，积压订单中有 5 笔价格为 10 的采购订单，和 1 笔价格为 30 的采购订单。所以积压订单中的订单总数为 6 。
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：orders = [[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]
 * 输出：999999984
 * 解释：输入订单后会发生下述情况：
 * - 提交 109 笔销售订单，价格为 7 。没有采购订单，所以这 109 笔订单添加到积压订单中。
 * - 提交 3 笔采购订单，价格为 15 。这些采购订单与价格最低（价格为 7 ）的 3 笔销售订单匹配，从积压订单中删除这 3 笔销售订单。
 * - 提交 999999995 笔采购订单，价格为 5 。销售订单的最低价为 7 ，所以这 999999995 笔订单添加到积压订单中。
 * - 提交 1 笔销售订单，价格为 5 。这笔销售订单与价格最高（价格为 5 ）的 1 笔采购订单匹配，从积压订单中删除这 1 笔采购订单。
 * 最终，积压订单中有 (1000000000-3) 笔价格为 7 的销售订单，和 (999999995-1) 笔价格为 5 的采购订单。所以积压订单中的订单总数为 1999999991 ，等于 999999984 % (109 + 7) 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= orders.length <= 105
 * orders[i].length == 3
 * 1 <= pricei, amounti <= 109
 * orderTypei 为 0 或 1
 */
public class No1801_getNumberOfBacklogOrders {
    public static int getNumberOfBacklogOrders_v1(int[][] orders) {
        TreeMap<Integer, Integer> sell = new TreeMap<>();
        TreeMap<Integer, Integer> buy = new TreeMap<>();
        for (int[] order : orders) {
            // buy
            if (order[2] == 0) {
                while (!sell.isEmpty() && order[1] != 0) {
                    Map.Entry<Integer, Integer> entry = sell.firstEntry();
                    Integer key = entry.getKey();
                    if (key > order[0]) {
                        break;
                    }
                    int temp = entry.getValue();
                    if (temp - order[1] <= 0) {
                        sell.remove(entry.getKey());
                        order[1] -= temp;
                    } else {
                        sell.put(entry.getKey(), temp - order[1]);
                        order[1] = 0;
                    }
                }
                if (order[1] > 0) {
                    buy.put(order[0], buy.getOrDefault(order[0], 0) + order[1]);
                }
            } else {
                while (!buy.isEmpty() && order[1] != 0) {
                    Map.Entry<Integer, Integer> entry = buy.lastEntry();
                    Integer key = entry.getKey();
                    if (key < order[0]) {
                        break;
                    }
                    int temp = entry.getValue();
                    if (temp - order[1] <= 0) {
                        buy.remove(entry.getKey());
                        order[1] -= temp;
                    } else {
                        buy.put(entry.getKey(), temp - order[1]);
                        order[1] = 0;
                    }
                }
                if (order[1] > 0) {
                    sell.put(order[0], sell.getOrDefault(order[0], 0) + order[1]);
                }
            }
        }
        int sum = (int) (sell.values().stream().mapToLong(p -> p).sum() % 1000000007);
        sum = (int) ((buy.values().stream().mapToLong(p -> p).sum() + sum) % 1000000007);
        return sum;
    }

    public static int getNumberOfBacklogOrders_v2(int[][] orders) {
        Queue<int[]> sell = new PriorityQueue<>();
        Queue<int[]> buy = new PriorityQueue<>();
        for (int[] order : orders) {
            // buy
            if (order[2] == 0) {
                while (!sell.isEmpty() && order[1] != 0) {
                    int[] entry = sell.poll();
                    int key = entry[0];
                    if (key > order[0]) {
                        break;
                    }
                    int value = entry[1];
                    if (value - order[1] <= 0) {
                        order[1] -= value;
                    } else {
                        entry[1] -= order[1];
                        sell.add(entry);
                        order[1] = 0;
                    }
                }
                if (order[1] > 0) {
                    buy.add(order);
                }
            } else {
                while (!buy.isEmpty() && order[1] != 0) {
                    int[] entry = buy.poll();
                    int key = entry[0];
                    if (key < order[0]) {
                        break;
                    }
                    int value = entry[1];
                    if (value - order[1] <= 0) {
                        order[1] -= value;
                    } else {
                        entry[1] -= order[1];
                        buy.add(entry);
                        order[1] = 0;
                    }
                }
                if (order[1] > 0) {
                    sell.add(order);
                }
            }
        }
        int sum = (int) (sell.stream().mapToLong(p -> p[1]).sum() % 1000000007);
        sum = (int) ((buy.stream().mapToLong(p -> p[1]).sum() + sum) % 1000000007);
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(getNumberOfBacklogOrders_v1(new int[][]{{27, 30, 0}, {10, 10, 1}, {28, 17, 1}, {19, 28, 0}, {16, 8, 1}, {14, 22, 0}, {12, 18, 1}, {3, 15, 0}, {25, 6, 1}}));

    }

    private static class Order {
        private int price;
        private int amount;
        private int orderType;
    }
}
