package com.moon.javase._keyword;

/**
 * 非静态代码块与构造函数的区别是：
 * 非静态代码块是给所有对象进行统一初始化，而构造函数是给对应的对象初始化，
 * 因为构造函数是可以多个的，运行哪个构造函数就会建立什么样的对象，
 * 但无论建立哪个对象，都会先执行相同的构造代码块。
 * 也就是说，构造代码块中定义的是不同对象共性的初始化内容。
 */
public class StaticTest {
    static {
        i = 1;
        // System.out.println("静态代码块对于定义在它之后的静态变量，可以赋值，但是不能访问." + i);
    }

    private static int i;

    static {
        System.out.println("静态代码块");
    }

    {
        System.out.println("非静态代码块");
    }

    public StaticTest() {
        System.out.println("构造方法");
    }

    public void doSomething() {
        System.out.println("非静态方法");
        {
            System.out.println("非静态方法中的代码块！");
        }
    }

    private static void test() {
        System.out.println("静态方法中的内容! ");
        {
            System.out.println("静态方法中的代码块！");
        }
    }

    public static void main(String[] args) {
        StaticTest test1 = new StaticTest();
        StaticTest test2 = new StaticTest();
        test1.doSomething();
        StaticTest.test();
        /*
        输出：
            静态代码块
            非静态代码块
            构造方法
            非静态代码块
            构造方法
            非静态方法
            非静态方法中的代码块！
            静态方法中的内容!
            静态方法中的代码块！
         */
    }
}
