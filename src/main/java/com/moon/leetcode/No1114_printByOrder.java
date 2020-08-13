package com.moon.leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1114. 按序打印<br/>
 * <br/>
 * 我们提供了一个类：<br/>
 * <code>
 * public class Foo {<br/>
 * public void first() { print("first"); }<br/>
 * public void second() { print("second"); }<br/>
 * public void third() { print("third"); }<br/>
 * } <br/>
 * </code>
 * 三个不同的线程将会共用一个 Foo 实例。<br/>
 * <br/>
 * 线程 A 将会调用 first() 方法<br/>
 * 线程 B 将会调用 second() 方法<br/>
 * 线程 C 将会调用 third() 方法<br/>
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。<br/>
 * <br/>
 * 示例 1:<br/>
 * 输入: [1,2,3]<br/>
 * 输出: "firstsecondthird"<br/>
 * 解释: <br/>
 * 有三个线程会被异步启动。<br/>
 * 输入 [1,2,3] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。<br/>
 * 正确的输出是 "firstsecondthird"。<br/>
 * <br/>
 * 示例 2:<br/>
 * 输入: [1,3,2]<br/>
 * 输出: "firstsecondthird"<br/>
 * 解释: <br/>
 * 输入 [1,3,2] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 third() 方法，线程 C 将会调用 second() 方法。<br/>
 * 正确的输出是 "firstsecondthird"。<br/>
 * <br/>
 * 提示：<br/>
 * 尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。<br/>
 * 你看到的输入格式主要是为了确保测试的全面性。<br/>
 */
public class No1114_printByOrder {
    // /**
    //  * 加锁，利用乐观锁+自旋锁，
    //  */
    // private AtomicInteger firstJobDone = new AtomicInteger(0);
    // private AtomicInteger secondJobDone = new AtomicInteger(0);
    //
    // public No1114_printByOrder() {
    // }
    //
    // public void first(Runnable printFirst) throws InterruptedException {
    //     // printFirst.run() outputs "first".
    //     printFirst.run();
    //     // mark the first job as done, by increasing its count.
    //     firstJobDone.incrementAndGet();
    // }
    //
    // public void second(Runnable printSecond) throws InterruptedException {
    //     while (firstJobDone.get() != 1) {
    //         // waiting for the first job to be done.
    //     }
    //     // printSecond.run() outputs "second".
    //     printSecond.run();
    //     // mark the second as done, by increasing its count.
    //     secondJobDone.incrementAndGet();
    // }
    //
    // public void third(Runnable printThird) throws InterruptedException {
    //     while (secondJobDone.get() != 1) {
    //         // waiting for the second job to be done.
    //     }
    //     // printThird.run() outputs "third".
    //     printThird.run();
    // }

    /**
     * CountDownLatch
     */
    // private CountDownLatch c2 = new CountDownLatch(1);
    // private CountDownLatch c3 = new CountDownLatch(1);
    //
    // public No1114_printByOrder() {
    // }
    //
    // public void first(Runnable printFirst) throws InterruptedException {
    //     // printFirst.run() outputs "first".
    //     printFirst.run();
    //     // mark the first job as done, by increasing its count.
    //     c2.countDown();
    // }
    //
    // public void second(Runnable printSecond) throws InterruptedException {
    //     c2.await();
    //     // printSecond.run() outputs "second".
    //     printSecond.run();
    //     // mark the second as done, by increasing its count.
    //     c3.countDown();
    // }
    //
    // public void third(Runnable printThird) throws InterruptedException {
    //     c3.await();
    //     // printThird.run() outputs "third".
    //     printThird.run();
    // }

    // /**
    //  * Semaphore
    //  */
    // private static Semaphore c2 = new Semaphore(0);
    // private static Semaphore c3 = new Semaphore(0);
    //
    // public No1114_printByOrder() throws InterruptedException {
    // }
    //
    // public static void first(Runnable printFirst) throws InterruptedException {
    //
    //     // printFirst.run() outputs "first".
    //     printFirst.run();
    //     // mark the first job as done, by increasing its count.
    //     c2.release();
    // }
    //
    // public static void second(Runnable printSecond) throws InterruptedException {
    //     c2.acquire();
    //     // printSecond.run() outputs "second".
    //     printSecond.run();
    //     // mark the second as done, by increasing its count.
    //     c3.release();
    // }
    //
    // public static void third(Runnable printThird) throws InterruptedException {
    //     c3.acquire();
    //     // printThird.run() outputs "third".
    //     printThird.run();
    // }

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int count = 1;

    public No1114_printByOrder() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        count = 2;
        //其实这里调用signal是有问题
        //假设这里唤醒第三个线程
        //第三个线程会再次调用await,进入等待池等待唤醒
        //第二个线程此时也在等待池中
        //这样就会导致第一个线程和第二个线程都在等待池中
        //condition.signal();
        condition.signalAll();
        lock.unlock();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        //为什么这里可以使用if,而下面必须使用while呢
        //如果线程一运行完,线程三拿到了锁,则需要再次判断count值让其进入等待池
        if (count != 2) {
            condition.await();
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        count = 3;
        //这里可以调用signal方法,因为只剩下一个线程在等待了
        condition.signal();
        lock.unlock();
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        while (count != 3) {
            condition.await();
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        No1114_printByOrder a = new No1114_printByOrder();
        new Thread(() -> {
            try {
                a.third(() -> System.out.println("third"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                a.second(() -> System.out.println("second"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                a.first(() -> System.out.println("first"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
