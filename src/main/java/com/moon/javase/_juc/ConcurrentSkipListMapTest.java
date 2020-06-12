package com.moon.javase._juc;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 跳表
 */
public class ConcurrentSkipListMapTest {
    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, Integer> map = new ConcurrentSkipListMap<>();
        map.putIfAbsent(1, 1);
        map.putIfAbsent(1, 2);
        map.put(3, 3);
        map.put(3, 4);
        Integer v = map.computeIfPresent(3, (i1, i2) -> i1 * i2);
        map.forEach((k, vv) -> System.out.println(k + ": " + v));
        // System.out.println(map + " " + v);

        Function<Integer, Integer> fun1 = arg -> arg * arg;
        // Function<Integer, Integer> fun1 =  new Function<Integer, Integer>() {
        //     @Override
        //     public Integer apply(Integer i) {
        //         return i * i;
        //     }
        // };
        Integer apply = fun1.apply(10);
        // 100
        System.out.println(apply);

        // 求输入两个的和
        BiFunction<Integer, Integer, Integer> fun2 = Integer::sum;
        // BiFunction<Integer, Integer, Integer> fun2 = new BiFunction<Integer, Integer, Integer>() {
        //     @Override
        //     public Integer apply(Integer o1, Integer o2) {
        //         return o1 + o2;
        //     }
        // };
        Integer sum = fun2.apply(10, 20);
        // 30
        System.out.println(sum);
    }
}
