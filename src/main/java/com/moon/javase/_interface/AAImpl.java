package com.moon.javase._interface;

class AAImpl extends AA_ {

    @Override
    public Integer b() {
        return getB();
    }

    // 接口中可以有默认方法实现（jdk1.8），默认为pubic修饰，
    public Long c() {
        return 1L;
    }


    // 接口中可以有默认方法实现（jdk1.8），默认为pubic修饰，
    public Long d() {
        return 1L;
    }

    @Override
    public String toString() {
        return "AAImpl{}";
    }
}
