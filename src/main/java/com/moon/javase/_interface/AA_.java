package com.moon.javase._interface;

abstract class AA_ implements AA {
    private Integer b;

    public Integer getB() {
        return this.b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    // 接口中可以有静态方法实现（jdk1.8），默认为pubic修饰，
    public static Integer a() {
        return a;
    }

    // 接口中可以有默认方法实现（jdk1.8），默认为pubic修饰，
    public Long c() {
        return 1L;
    }
}
