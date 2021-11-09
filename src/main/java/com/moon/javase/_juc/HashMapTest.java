package com.moon.javase._juc;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * hashMap容量总是2的整数倍，算法处理，只适用于2^32大小，即无符号int,
 * long型就无法处理了，但是java的hashMap容量最大值就是int，所以无影响
 */
public class HashMapTest {
    private static final int MAXIMUM_CAPACITY = Integer.MAX_VALUE - 8;

    /**
     * 原理就是复制最高位的1，用最高位的1覆盖它之后的所有位置，这样最后得到的就是11....11，这样一个2的n次幂-1的数字，
     * 而为了避免入参本身就是一个2^n，所以先减一
     */
    static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        System.out.println(tableSizeFor(7));
        HashMap<Object, Object> map = new HashMap<>();
        ConcurrentMap<Object, Object> m = new ConcurrentHashMap<>();
    }
}
