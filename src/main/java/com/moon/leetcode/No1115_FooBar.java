package com.moon.leetcode;

import java.util.concurrent.Semaphore;

/**
 * 1115. 交替打印FooBar<br/>
 * 我们提供一个类：<br/>
 * <br/>
 * <code>
 * class FooBar {<br/>
 * public void foo() {<br/>
 *     for (int i = 0; i < n; i++) {<br/>
 *       print("foo");<br/>
 *     }<br/>
 * }<br/>
 * <br/>
 * public void bar() {<br/>
 *     for (int i = 0; i < n; i++) {<br/>
 *       print("bar");<br/>
 *     }<br/>
 * }<br/>
 * }<br/>
 * </code>
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。<br/>
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。<br/>
 * <br/>
 * 示例 1:<br/>
 * 输入: n = 1<br/>
 * 输出: "foobar"<br/>
 * 解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。<br/>
 * <br/>
 * 示例 2:<br/>
 * 输入: n = 2<br/>
 * 输出: "foobarfoobar"<br/>
 * 解释: "foobar" 将被输出两次。<br/>
 */
public class No1115_FooBar {
    /**
     * 因为要保证foo()方法先获取到锁，所以在bar方法中使用死循环阻塞线程，会造成耗时
     */
    // private int n;
    // private volatile AtomicInteger i = new AtomicInteger(0);
    // private Lock lock = new ReentrantLock();
    // private Condition condition = lock.newCondition();
    // public No1115_FooBar(int n) {
    //     this.n = n;
    // }
    // public void foo(Runnable printFoo) throws InterruptedException {
    //     printFoo.run();
    //     lock.lock();
    //     i.compareAndSet(0, 2);
    //     for (int i = 1; i < n; i++) {
    //         // printFoo.run() outputs "foo". Do not change or remove this line.
    //         condition.await();
    //         printFoo.run();
    //         condition.signal();
    //     }
    //     lock.unlock();
    // }
    // public void bar(Runnable printBar) throws InterruptedException {
    //     while (i.get() == 0) {
    //     }
    //     lock.lock();
    //     for (int i = 0; i < n - 1; i++) {
    //         // printBar.run() outputs "bar". Do not change or remove this line.
    //         printBar.run();
    //         condition.signal();
    //         condition.await();
    //     }
    //     lock.unlock();
    //     printBar.run();
    // }

    // private int n;
    // private CountDownLatch a;
    // // 使用CyclicBarrier保证任务按组执行
    // private CyclicBarrier barrier;
    //
    // public No1115_FooBar(int n) {
    //     this.n = n;
    //     a = new CountDownLatch(1);
    //     // 保证每组内有两个任务
    //     barrier = new CyclicBarrier(2);
    // }
    //
    // public void foo(Runnable printFoo) throws InterruptedException, BrokenBarrierException {
    //     for (int i = 0; i < n; i++) {
    //         printFoo.run();
    //         // printFoo方法完成调用countDown
    //         a.countDown();
    //         // 等待printBar方法执行完成
    //         barrier.await();
    //     }
    // }
    //
    // public void bar(Runnable printBar) throws InterruptedException, BrokenBarrierException {
    //     for (int i = 0; i < n; i++) {
    //         // 等待printFoo方法先执行
    //         a.await();
    //         printBar.run();
    //         // 保证下一次依旧等待printFoo方法先执行
    //         a = new CountDownLatch(1);
    //         // 等待printFoo方法执行完成
    //         barrier.await();
    //     }
    // }
    /**
     * 下面这种利用信号量的做法非常巧妙，和CountDownLatch有同工之秒
     */
    private int n;

    public No1115_FooBar(int n) {
        this.n = n;
    }

    private Semaphore fooSemaphore = new Semaphore(1);
    private Semaphore barSemaphore = new Semaphore(0);

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            fooSemaphore.acquire();
            printFoo.run();
            barSemaphore.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            barSemaphore.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            fooSemaphore.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        No1115_FooBar fooBar = new No1115_FooBar(3);

        Runnable r1 = () -> System.out.print("foo");
        Runnable r2 = () -> System.out.print("bar");

        new Thread(() -> {
            try {
                fooBar.foo(r1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                fooBar.bar(r2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }

}
