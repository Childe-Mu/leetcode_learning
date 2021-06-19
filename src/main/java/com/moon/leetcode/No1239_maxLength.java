package com.moon.leetcode;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 1239. 串联字符串的最大长度
//
//给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
//
// 请返回所有可行解 s 中最长长度。
//
// 示例 1：
//
// 输入：arr = ["un","iq","ue"]
//输出：4
//解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
//
// 示例 2：
//
// 输入：arr = ["cha","r","act","ers"]
//输出：6
//解释：可能的解答有 "chaers" 和 "acters"。
//
// 示例 3：
//
// 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
//输出：26
//
// 提示：
//
// 1 <= arr.length <= 16
// 1 <= arr[i].length <= 26
// arr[i] 中只含有小写英文字母
//
// Related Topics 位运算 回溯算法
// 👍 113 👎 0
public class No1239_maxLength {

    List<Integer> arr;
    List<String> array;
    int n;
    int ans = 0;
    Map<Integer, Integer>[] maps;

    public static void main(String[] args) {
        System.out.println(new No1239_maxLength().maxLength_v1(Lists.newArrayList("yy", "bkhwmpbiisbldzknpm")));
    }

    public int maxLength_v1(List<String> arr) {
        List<Integer> tmp = new ArrayList<>();
        for (String s : arr) {
            int t = 0;
            char[] ss = s.toCharArray();
            for (char c : ss) {
                int i = c - 'a';
                if ((t & (1 << i)) != 0) {
                    t = -1;
                    break;
                }
                t |= 1 << (c - 'a');
            }
            if (t > 0) {
                tmp.add(t);
            }
        }
        this.arr = tmp;
        this.n = tmp.size();
        dfs(0, 0);
        return ans;
    }

    private void dfs(int depth, int path) {
        if (depth == n) {
            ans = Math.max(Integer.bitCount(path), ans);
            return;
        }
        int cur = arr.get(depth);
        if ((path & cur) == 0) {
            dfs(depth + 1, path | cur);
        }
        dfs(depth + 1, path);
    }

    public int maxLength_v2(List<String> arr) {
        maps = new HashMap[arr.size()];
        for (int i = 0; i < maps.length; i++) {
            maps[i] = new HashMap<>();
        }
        return dfs(arr, 0, 0);
    }

    int dfs(List<String> arr, int i, int state) {
        if (i >= arr.size()) {
            return 0;
        }
        Integer len = maps[i].get(state);
        if (len != null) {
            return len;
        }
        int old = state;
        len = dfs(arr, i + 1, state);
        char[] chars = arr.get(i).toCharArray();
        for (char c : chars) {
            int idx = 1 << (c - 'a');
            if ((state & idx) > 0) {
                maps[i].put(old, len);
                return len;
            }
            state |= idx;
        }
        len = Math.max(dfs(arr, i + 1, state) + chars.length, len);
        maps[i].put(old, len);
        return len;
    }
}
