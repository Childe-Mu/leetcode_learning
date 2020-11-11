package com.moon.leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1117. H2O 生成
 * <p>
 * 现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。
 * <p>
 * 存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。
 * <p>
 * 氢和氧线程会被分别给予 releaseHydrogen 和 releaseOxygen 方法来允许它们突破屏障。
 * <p>
 * 这些线程应该三三成组突破屏障并能立即组合产生一个水分子。
 * <p>
 * 你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。
 * <p>
 * 换句话说:
 * <p>
 * 如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。
 * 如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。
 * <p>
 * 书写满足这些限制条件的氢、氧线程同步代码。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "HOH"
 * 输出: "HHO"
 * 解释: "HOH" 和 "OHH" 依然都是有效解。
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: "OOHHHH"
 * 输出: "HHOHHO"
 * 解释: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" 和 "OH
 * HOHH" 依然都是有效解。
 * <p>
 * 提示：
 * <p>
 * 输入字符串的总长将会是 3n, 1 ≤ n ≤ 50；
 * 输入字符串中的 “H” 总数将会是 2n 。
 * 输入字符串中的 “O” 总数将会是 n 。
 */
public class No1117_H2O {
    public static void main(String[] args) {
        int n = 3;
        H2O obj = new H2O();
        Runnable releaseHydrogen = () -> System.out.print("H");
        Runnable releaseOxygen = () -> System.out.print("O");

        new Thread(() -> {
            for (int i = 0; i < n; i++) {
                try {
                    obj.oxygen(releaseOxygen);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "O").start();
        new Thread(() -> {
            for (int i = 0; i < n; i++) {
                try {
                    obj.hydrogen(releaseHydrogen);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "H1").start();
        new Thread(() -> {
            for (int i = 0; i < n; i++) {
                try {
                    obj.hydrogen(releaseHydrogen);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "H2").start();
    }
}

/**
 * 利用Semaphore和CyclicBarrier来控制顺序
 * 相较于其他方法，这种方法最为优雅简洁
 * 与前几道题不一样，因为 O 和 H 的输出没有先后要求，所以初始信号量许可需要多少给多少，即O-1，H-2，
 * 然后利用栅栏保证每次只能通过三个，和信号量配合，就可以保证每次通过的三个都是2个H,1个O
 * <p>
 * 锁+计数
 * 原理是一次只有一个线程可以获取到锁，获取到锁以后输出的规律是：
 * 1.只要输出一次O，O的计数归零，必须要输出两次H才会还原到1。
 * 2.只要输出两次H，H的计数归零，必须要输出一次O才会还原为2。
 * 3.先输出一次H，然后再输出一次O，此时H的计数还原为2，O计数为0，也就是说，接下来必须连着输出两次H，
 * 而这两次H，其实一次是本轮的，一次是下一轮的，所以不会有问题。
 * <p>
 * 原理同上一个 锁+计数
 * 只是换用Lock锁 和 Condition来实现，
 * 但是因为最后的await没有人通知释放，会造成阻塞，所以加入超时释放
 * <p>
 * 锁+计数
 * 原理是一次只有一个线程可以获取到锁，获取到锁以后输出的规律是：
 * 1.只要输出一次O，O的计数归零，必须要输出两次H才会还原到1。
 * 2.只要输出两次H，H的计数归零，必须要输出一次O才会还原为2。
 * 3.先输出一次H，然后再输出一次O，此时H的计数还原为2，O计数为0，也就是说，接下来必须连着输出两次H，
 * 而这两次H，其实一次是本轮的，一次是下一轮的，所以不会有问题。
 * <p>
 * 锁+计数
 * 原理是一次只有一个线程可以获取到锁，获取到锁以后输出的规律是：
 * 1.只要输出一次O，O的计数归零，必须要输出两次H才会还原到1。
 * 2.只要输出两次H，H的计数归零，必须要输出一次O才会还原为2。
 * 3.先输出一次H，然后再输出一次O，此时H的计数还原为2，O计数为0，也就是说，接下来必须连着输出两次H，
 * 而这两次H，其实一次是本轮的，一次是下一轮的，所以不会有问题。
 * <p>
 * 锁+计数
 * 原理是一次只有一个线程可以获取到锁，获取到锁以后输出的规律是：
 * 1.只要输出一次O，O的计数归零，必须要输出两次H才会还原到1。
 * 2.只要输出两次H，H的计数归零，必须要输出一次O才会还原为2。
 * 3.先输出一次H，然后再输出一次O，此时H的计数还原为2，O计数为0，也就是说，接下来必须连着输出两次H，
 * 而这两次H，其实一次是本轮的，一次是下一轮的，所以不会有问题。
 * <p>
 * 锁+计数
 * 原理是一次只有一个线程可以获取到锁，获取到锁以后输出的规律是：
 * 1.只要输出一次O，O的计数归零，必须要输出两次H才会还原到1。
 * 2.只要输出两次H，H的计数归零，必须要输出一次O才会还原为2。
 * 3.先输出一次H，然后再输出一次O，此时H的计数还原为2，O计数为0，也就是说，接下来必须连着输出两次H，
 * 而这两次H，其实一次是本轮的，一次是下一轮的，所以不会有问题。
 */
// class H2O {
//     private Semaphore o = new Semaphore(1);
//     private Semaphore h = new Semaphore(2);
//     private CyclicBarrier c = new CyclicBarrier(3);
//
//     public H2O() {
//
//     }
//
//     public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
//         // releaseHydrogen.run() outputs "H". Do not change or remove this line.
//         try {
//             h.acquire();
//             releaseHydrogen.run();
//             c.await();
//             h.release();
//         } catch (BrokenBarrierException e) {
//             e.printStackTrace();
//         }
//     }
//
//     public void oxygen(Runnable releaseOxygen) throws InterruptedException {
//         // releaseOxygen.run() outputs "O". Do not change or remove this line.
//         try {
//             o.acquire();
//             releaseOxygen.run();
//             c.await();
//             o.release();
//         } catch (BrokenBarrierException e) {
//             e.printStackTrace();
//         }
//     }
// }

/**
 * 锁+计数
 * 原理是一次只有一个线程可以获取到锁，获取到锁以后输出的规律是：
 * 1.只要输出一次O，O的计数归零，必须要输出两次H才会还原到1。
 * 2.只要输出两次H，H的计数归零，必须要输出一次O才会还原为2。
 * 3.先输出一次H，然后再输出一次O，此时H的计数还原为2，O计数为0，也就是说，接下来必须连着输出两次H，
 * 而这两次H，其实一次是本轮的，一次是下一轮的，所以不会有问题。
 */
// class H2O {
//     private Integer o = 1;
//     private Integer h = 2;
//
//     public H2O() {
//
//     }
//
//     public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
//         // releaseHydrogen.run() outputs "H". Do not change or remove this line.
//         synchronized (this) {
//             while (h <= 0) {
//                 wait();
//             }
//             releaseHydrogen.run();
//             if (--h == 0) {
//                 o = 1;
//             }
//             notifyAll();
//         }
//     }
//
//     public void oxygen(Runnable releaseOxygen) throws InterruptedException {
//         // releaseOxygen.run() outputs "O". Do not change or remove this line.
//         synchronized (this) {
//             while (o <= 0) {
//                 wait();
//             }
//             releaseOxygen.run();
//             o--;
//             h = 2;
//             notifyAll();
//         }
//     }
// }

/**
 * 原理同上一个 锁+计数
 * 只是换用Lock锁 和 Condition来实现，
 * 但是因为最后的await没有人通知释放，会造成阻塞，所以加入超时释放
 */
// class H2O {
//     private Lock lock = new ReentrantLock();
//     private Condition o = lock.newCondition();
//     private Condition h = lock.newCondition();
//     private Integer hCount = 2;
//
//     public H2O() {
//
//     }
//
//     public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
//         // releaseHydrogen.run() outputs "H". Do not change or remove this line.
//         lock.lock();
//         releaseHydrogen.run();
//         if (--hCount == 0) {
//             o.signalAll();
//         }
//         h.await(10, TimeUnit.MILLISECONDS);
//         lock.unlock();
//     }
//
//     public void oxygen(Runnable releaseOxygen) throws InterruptedException {
//         // releaseOxygen.run() outputs "O". Do not change or remove this line.
//         lock.lock();
//         releaseOxygen.run();
//         hCount = 2;
//         h.signalAll();
//         o.await(10, TimeUnit.MILLISECONDS);
//         lock.unlock();
//     }
// }
class H2O {
    private Lock lock = new ReentrantLock();
    private Condition o = lock.newCondition();
    private Condition h = lock.newCondition();
    private Integer hCount = 2;
    private Integer oCount = 1;

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        lock.lock();
        while (hCount == 0) {
            h.await();
        }
        releaseHydrogen.run();
        if (--hCount == 0) {
            oCount = 1;
        }
        o.signal();
        lock.unlock();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        lock.lock();
        while (oCount == 0) {
            o.await();
        }
        releaseOxygen.run();
        oCount--;
        hCount = 2;
        h.signal();
        lock.unlock();
    }
}


/**
 * ReentrantLock + Condition + 计数
 */
// class H2O {
//     private ReentrantLock lock = new ReentrantLock();
//     private Condition h = lock.newCondition();
//     private Condition o = lock.newCondition();
//     //前2位代表氢元素的个数,第3位代表氧元素的个数
//     private int h2o = 0b000;
//
//     public H2O() {
//     }
//
//     public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
//         lock.lock();
//         //00(0个H元素) 01(1个H元素) 11(2个H元素)
//         //((h2o & 3) == 3)表示H元素已经到了2个
//         while ((h2o & 0b011) == 0b011) {
//             System.out.println("--h 开始 await");
//             h.await();
//             System.out.println("--h 结束 await");
//         }
//         // releaseHydrogen.run() outputs "H". Do not change or remove this line.
//         releaseHydrogen.run();
//         //==0代表没有1个氢元素,否则一定会有1个氢元素
//         h2o += ((h2o & 0b011) == 0b000) ? 0b001 : 0b010;
//         //水分子已经生成
//         if (h2o == 0b111) {
//             h2o = 0b000;
//         }
//         //唤醒一个氧线程
//         o.signal();
//         //如果((h2o & 3) == 3)表示氢线程不需要再唤醒了
//         if ((h2o & 3) != 3) {
//             h.signal();
//         }
//         lock.unlock();
//     }
//
//     public void oxygen(Runnable releaseOxygen) throws InterruptedException {
//         lock.lock();
//         //((h2o & 4) == 4)表示O元素已经到了1个
//         while ((h2o & 4) == 4) {
//             System.out.println("--o 开始 await");
//             o.await();
//             System.out.println("--o 结束 await");
//         }
//         // releaseOxygen.run() outputs "O". Do not change or remove this line.
//         releaseOxygen.run();
//         h2o += 4;
//         //水分子已经生成
//         if (h2o == 7) {
//             h2o = 0;
//         }
//         //因为只需要1个氧元素,所以这里只需要唤醒氢线程就可以了
//         h.signal();
//         lock.unlock();
//     }
// }
