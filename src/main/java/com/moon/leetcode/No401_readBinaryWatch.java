package com.moon.leetcode;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 401. 二进制手表
 * <p>
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。
 * <p>
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 * <p>
 * 例如，上面的二进制手表读取 “3:25”。
 * <p>
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 * <p>
 * 示例：
 * <p>
 * 输入: n = 1
 * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "
 * 0:32"]
 * <p>
 * 提示：
 * <p>
 * 输出的顺序没有要求。
 * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
 * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
 * 超过表示范围（小时 0-11，分钟 0-59）的数据将会被舍弃，也就是说不会出现 "13:00", "0:61" 等时间。
 */
@Slf4j
public class No401_readBinaryWatch {
    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new No401_readBinaryWatch().readBinaryWatch(3)));
    }

    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int hourMinute = 0;
        int index = 0;
        backtrack(num, 0, index, hourMinute, res);
        res.sort(String::compareTo);
        return res;
    }

    private void backtrack(int num, int depth, int index, int hourMinute, List<String> res) {
        if (depth == num) {
            convertTime(hourMinute, res);
            return;
        }
        for (int i = index; i < 10; i++) {
            if ((hourMinute & (1 << i)) == 1) {
                continue;
            }
            int temp = hourMinute;
            hourMinute |= (1 << i);
            backtrack(num, depth + 1, i + 1, hourMinute, res);
            hourMinute = temp;
        }
    }

    private void convertTime(int hourMinute, List<String> res) {
        int minute = hourMinute & ((1 << 6) - 1);
        int hour = hourMinute >> 6;
        if (hour > 11 || hour < 0 || minute > 59) {
            System.out.println("=====");
            return;
        }
        res.add(String.format("%d:%02d", hour, minute));
    }
}
