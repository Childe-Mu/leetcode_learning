package com.moon.leetcode;

/**
 * 278. 第一个错误的版本<br/>
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。
 * 由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。<br/>
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。<br/>
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。
 * 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。<br/>
 * <br/>
 * 示例:<br/>
 * 给定 n = 5，并且 version = 4 是第一个错误的版本。<br/>
 * 调用 isBadVersion(3) -> false<br/>
 * 调用 isBadVersion(5) -> true<br/>
 * 调用 isBadVersion(4) -> true<br/>
 */
public class No278_firstBadVersion {
    public static void main(String[] args) {
        System.out.println(new No278_firstBadVersion().firstBadVersion_v2(5));
    }

    /**
     * 二分法 1
     */
    public int firstBadVersion_v1(int n) {
        int left = 1, right = n;
        int result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid) && !isBadVersion(mid - 1)) {
                result = mid;
                break;
            } else if (!isBadVersion(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    boolean isBadVersion(int mid) {
        return mid >= 4;
    }

    /**
     * 二分法 2，利用到了，如果有问题，一定是后面的版本有问题，前面的部分版本没问题，即有序
     */
    public int firstBadVersion_v2(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}