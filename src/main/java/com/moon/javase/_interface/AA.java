package com.moon.javase._interface;

interface AA {
    // 接口中的常量，默认是public final static，什么也不用写
    public final static Integer a = 0;

    // 接口中可以有静态方法实现（jdk1.8），默认为pubic修饰，
    public static Integer a() {
        return a;
    }

    Integer b();

    // 接口中可以有默认方法实现（jdk1.8），默认为pubic修饰，
    public default Long c() {
        return 1L;
    }
}
