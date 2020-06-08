package com.moon.javase._juc;

/**
 * hashMap容量总是2的整数倍，算法处理，只适用于2^32大小，即无符号int,
 * long型就无法处理了，但是java的hashMap容量最大值就是int，所以无影响
 */
public class HashMaPTest {
    private static final int MAXIMUM_CAPACITY = Integer.MAX_VALUE - 8;

    static final int tableSizeFor(int cap) {
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
    }
}
