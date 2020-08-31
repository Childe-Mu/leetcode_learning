package com.moon.javase._stream;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * flatMap And map demo
 */
public class FlatMapAndMapDemo {

    private List<List<String>> eggs = Lists.newArrayList();

    @Before
    public void init() {
        // 第一箱鸡蛋
        eggs.add(Lists.newArrayList("鸡蛋_1", "鸡蛋_1", "鸡蛋_1", "鸡蛋_1", "鸡蛋_1"));
        // 第二箱鸡蛋
        eggs.add(Lists.newArrayList("鸡蛋_2", "鸡蛋_2", "鸡蛋_2", "鸡蛋_2", "鸡蛋_2"));
    }

    // 自增生成组编号
    private static int group = 1;
    // 自增生成学生编号
    private static int student = 1;

    /**
     * 把二箱鸡蛋分别加工成煎蛋，还是放在原来的两箱，分给2组学生
     */
    @Test
    public void map() {
        List<List<String>> temp = eggs.stream()
                .map(x -> x.stream().map(y -> y.replace("鸡", "煎")).collect(Collectors.toList()))
                .collect(Collectors.toList());
        System.out.println(temp);
        //控制台打印：
        //[[煎蛋_1, 煎蛋_1, 煎蛋_1, 煎蛋_1, 煎蛋_1], [煎蛋_2, 煎蛋_2, 煎蛋_2, 煎蛋_2, 煎蛋_2]]
    }

    /**
     * 把二箱鸡蛋分别加工成煎蛋，然后放到一起【10个煎蛋】，分给10个学生
     */
    @Test
    public void flatMap() {
        List<String> temp = eggs.stream()
                .flatMap(x -> x.stream().map(y -> y.replace("鸡", "煎")))
                .collect(Collectors.toList());
        //控制台打印：
        //[煎蛋_1, 煎蛋_1, 煎蛋_1, 煎蛋_1, 煎蛋_1, 煎蛋_2, 煎蛋_2, 煎蛋_2, 煎蛋_2, 煎蛋_2]
        System.out.println(temp);
    }

    class A {
        private String a;
        private List<B> bs;

        A(String a, List<B> bs) {
            this.a = a;
            this.bs = bs;
        }
    }

    class B {
        private String b;
        private List<C> cs;

        B(String b, List<C> cs) {
            this.b = b;
            this.cs = cs;
        }
    }

    class C {
        private String c;

        C(String c) {
            this.c = c;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }
    }
}
