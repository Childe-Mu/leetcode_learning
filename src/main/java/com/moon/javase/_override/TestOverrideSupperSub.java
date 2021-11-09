package com.moon.javase._override;

/**
 * @author moon
 */
public class TestOverrideSupperSub extends TestOverrideSupper {
// 重写的方法为 final
//    static final Integer testStaticFinal() {
//        return 1;
//    }

    static Integer testStatic() {
        return 1;
    }

    // 重写的方法为 final
//    final Integer testFinal() {
//        return 1;
//    }

    // 不能返回int
    Integer test() {
        return 1;
    }

    TestOverrideSupperSub testReturn() {
        return null;
    }
}
