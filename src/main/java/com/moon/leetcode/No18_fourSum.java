package com.moon.leetcode;

// 18. 四数之和
//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b
//], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
//
//
// 0 <= a, b, c, d < n
// a、b、c 和 d 互不相同
// nums[a] + nums[b] + nums[c] + nums[d] == target
//
//
// 你可以按 任意顺序 返回答案 。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//
//
// 示例 2：
//
//
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 200
// -109 <= nums[i] <= 109
// -109 <= target <= 109
//
// Related Topics 数组 双指针 排序
// 👍 1028 👎 0

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class No18_fourSum {
    public List<List<Integer>> fourSum_v1(int[] nums, int t) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (i > 0 && i < n && nums[i] == nums[i - 1]) {
                i++;
            }
            for (int j = i + 1; j < n; j++) {
                while (i + 1 < j && j < n && nums[j] == nums[j - 1]) {
                    j++;
                }
                int k = j + 1;
                int l = n - 1;
                while (k < l) {
                    while (j + 1 < k && k < l && nums[k] == nums[k - 1]) {
                        k++;
                    }
                    while (k < l && l < n - 1 && nums[l] == nums[l + 1]) {
                        l--;
                    }
                    if (k >= l) {
                        break;
                    }
                    int s = nums[i] + nums[j] + nums[k] + nums[l];
                    if (s == t) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        list.add(nums[l]);
                        res.add(list);
                        k++;
                        l--;
                    } else if (s < t) {
                        k++;
                    } else {
                        l--;
                    }
                }
            }
        }
        return res;
    }


    public static List<List<Integer>> fourSum_v2(int[] nums, int target) {
        Map<String, DyeNum> indexStrKeyMap = new HashMap<>();
        Map<Integer, List<String>> sumKeyMap = new HashMap<>();
        Set<Integer> sumKeys = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                DyeNum dyeNum = new DyeNum();
                dyeNum.setNumSum(nums[i] + nums[j]);
                List<Integer> ids = new ArrayList<>();
                ids.add(i);
                ids.add(j);
                dyeNum.setIndex(ids);
                dyeNum.setIndexStr(i + "-" + j);
                sumKeys.add(dyeNum.getNumSum());
                indexStrKeyMap.put(dyeNum.getIndexStr(), dyeNum);
                if (sumKeyMap.containsKey(dyeNum.getNumSum())) {
                    sumKeyMap.get(dyeNum.getNumSum()).add(dyeNum.getIndexStr());
                } else {
                    List<String> tmp = new ArrayList<>();
                    tmp.add(dyeNum.getIndexStr());
                    sumKeyMap.put(dyeNum.getNumSum(), tmp);
                }
            }
        }
        Set<String> keys = new HashSet<>();
        List<List<Integer>> results = new ArrayList<>();
        for (Integer sumKey : sumKeys) {
            Integer differKey = target - sumKey;
            if (sumKeyMap.containsKey(sumKey) && sumKeyMap.containsKey(differKey)) {
                List<String> indexStrKey1 = sumKeyMap.get(sumKey);
                List<String> indexStrKey2 = sumKeyMap.get(differKey);
                for (String s1 : indexStrKey1) {
                    for (String s2 : indexStrKey2) {
                        DyeNum dyeNum1 = indexStrKeyMap.get(s1);
                        DyeNum dyeNum2 = indexStrKeyMap.get(s2);
                        if (dyeNum1.intersects(dyeNum2.getIndex())) {
                            List<Integer> result = new ArrayList<>(dyeNum1.getIndex());
                            result.addAll(dyeNum2.getIndex());
                            results.add(result);
                        }
                    }
                }
            }
        }
        List<List<Integer>> results1 = new ArrayList<>();
        for (List<Integer> result : results) {
            List<Integer> re = new ArrayList<>();
            for (Integer integer : result) {
                re.add(nums[integer]);
            }
            re.sort(Comparator.comparingInt(o -> o));
            if (!keys.contains(re.toString())) {
                keys.add(re.toString());
                results1.add(re);
            }
        }
        return results1;
    }

    @Setter
    @Getter
    private static class DyeNum {
        private int numSum;
        private List<Integer> index;
        private String indexStr;

        private boolean intersects(List<Integer> index) {
            List<Integer> temp = new ArrayList<>(this.index);
            temp.removeAll(index);
            return temp.size() == this.index.size();
        }

        @Override
        public String toString() {
            return this.indexStr;
        }
    }
}
