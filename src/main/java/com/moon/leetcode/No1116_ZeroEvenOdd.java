package com.moon.leetcode;

import java.util.function.IntConsumer;

/**
 * 1116. 打印零与奇偶数
 * <p>
 * 假设有这么一个类：
 * <p>
 * class ZeroEvenOdd {<br/>
 * public ZeroEvenOdd(int n) { ... }      // 构造函数<br/>
 * public void zero(printNumber) { ... }  // 仅打印出 0<br/>
 * public void even(printNumber) { ... }  // 仅打印出 偶数<br/>
 * public void odd(printNumber) { ... }   // 仅打印出 奇数<br/>
 * }<br/>
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 * <p>
 * 线程 A 将调用 zero()，它只输出 0 。<br/>
 * 线程 B 将调用 even()，它只输出偶数。<br/>
 * 线程 C 将调用 odd()，它只输出奇数。<br/>
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 * <p>
 * 示例 1：<br/>
 * 输入：n = 2<br/>
 * 输出："0102"<br/>
 * 说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
 * <br/>
 * 示例 2：<br/>
 * 输入：n = 5<br/>
 * 输出："0102030405"<br/>
 */
public class No1116_ZeroEvenOdd {
    public static void main(String[] args) {
        IntConsumer printNumber = System.out::print;
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(20);
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "0").start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "奇数").start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "偶数").start();
    }
}

/**
 * 利用信号量控制顺序
 * <p>
 * 利用CountDownLatch和CyclicBarrier控制顺序
 * 但是因为CountDownLatch只能使用一次的原因，多次循环中，需要不停的创建新对象，所以效率略低
 */
// class ZeroEvenOdd {
//     private int n;
//     private Semaphore s1 = new Semaphore(1);
//     private Semaphore s2 = new Semaphore(0);
//     private Semaphore s3 = new Semaphore(0);
//
//     public ZeroEvenOdd(int n) {
//         this.n = n;
//     }
//
//     // printNumber.accept(x) outputs "x", where x is an integer.
//     public void zero(IntConsumer printNumber) throws InterruptedException {
//         for (int i = 1; i <= n; i++) {
//             s1.acquire();
//             printNumber.accept(0);
//             s2.release();
//         }
//     }
//
//     public void even(IntConsumer printNumber) throws InterruptedException {
//         for (int i = 1; i <= n; i++) {
//             s3.acquire();
//             if ((i & 1) == 0) {
//                 printNumber.accept(i);
//             }
//             s1.release();
//         }
//     }
//
//     public void odd(IntConsumer printNumber) throws InterruptedException {
//         for (int i = 1; i <= n; i++) {
//             s2.acquire();
//             if ((i & 1) == 1) {
//                 printNumber.accept(i);
//             }
//             s3.release();
//         }
//     }
// }

/**
 * 利用CountDownLatch和CyclicBarrier控制顺序
 * 但是因为CountDownLatch只能使用一次的原因，多次循环中，需要不停的创建新对象，所以效率略低
 */
// class ZeroEvenOdd {
//     private int n;
//     private CountDownLatch c1 = new CountDownLatch(1);
//     private CountDownLatch c2 = new CountDownLatch(1);
//     private CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
//
//     public ZeroEvenOdd(int n) {
//         this.n = n;
//     }
//
//     // printNumber.accept(x) outputs "x", where x is an integer.
//     public void zero(IntConsumer printNumber) throws InterruptedException {
//         for (int i = 1; i <= n; i++) {
//             try {
//                 printNumber.accept(0);
//                 c1.countDown();
//                 cyclicBarrier.await();
//             } catch (BrokenBarrierException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
//
//     public void even(IntConsumer printNumber) throws InterruptedException {
//         for (int i = 1; i <= n; i++) {
//             try {
//                 c1.await();
//                 if ((i & 1) == 0) {
//                     printNumber.accept(i);
//                 }
//                 c2.countDown();
//                 c1 = new CountDownLatch(1);
//                 cyclicBarrier.await();
//             } catch (BrokenBarrierException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
//
//     public void odd(IntConsumer printNumber) throws InterruptedException {
//         for (int i = 1; i <= n; i++) {
//             try {
//                 c2.await();
//                 if ((i & 1) == 1) {
//                     printNumber.accept(i);
//                 }
//                 c2 = new CountDownLatch(1);
//                 cyclicBarrier.await();
//             } catch (BrokenBarrierException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
// }

// class ZeroEvenOdd {
//     private int n;
//     private CyclicBarrier c1 = new CyclicBarrier(2);
//     private CyclicBarrier c2 = new CyclicBarrier(2);
//     private CyclicBarrier c3 = new CyclicBarrier(2);
//
//     public ZeroEvenOdd(int n) {
//         this.n = n;
//     }
//
//     // printNumber.accept(x) outputs "x", where x is an integer.
//     public void zero(IntConsumer printNumber) throws InterruptedException {
//         for (int i = 1; i <= n; i++) {
//             try {
//                 c3.await();
//                 printNumber.accept(0);
//                 c1.await();
//             } catch (BrokenBarrierException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
//
//     public void even(IntConsumer printNumber) throws InterruptedException {
//         for (int i = 1; i <= n; i++) {
//             try {
//                 c2.await();
//                 if ((i & 1) == 0) {
//                     printNumber.accept(i);
//                 }
//                 if (i != n || (i & 1) != 0) {
//                     c3.await();
//                 }
//             } catch (BrokenBarrierException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
//
//     public void odd(IntConsumer printNumber) throws InterruptedException {
//         try {
//             c3.await();
//         } catch (BrokenBarrierException e) {
//             e.printStackTrace();
//         }
//         for (int i = 1; i <= n; i++) {
//             try {
//                 c1.await();
//                 if ((i & 1) == 1) {
//                     printNumber.accept(i);
//                 }
//                 if (i != n || (i & 1) != 1) {
//                     c2.await();
//                 }
//             } catch (BrokenBarrierException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
// }

/**
 * 利用synchronized关键字加锁，如果不满足条件就wait，如果满足就执行，然后唤醒全部线程，
 * 竞争锁，再重复前面的步骤。
 * 注意这里使用了wait()方法，而不是Thread.yield()，是因为后者即使因为不满足而放弃当前时间片段，
 * 也还是会参与竞争锁，可能会立马再获取锁，影响效率,可能会超时
 */
class ZeroEvenOdd {
    public int n;
    public float start = 0.5f;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (start > n)
                    break;
                if (start % 1 > 0) {
                    printNumber.accept(0);
                    start += 0.5f;
                    // this.notifyAll();
                } else {
                    Thread.yield();
                    // this.wait();
                }
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (start > n)
                    break;
                if (start % 1 == 0 && ((int) start & 1) == 0) {
                    printNumber.accept((int) start);
                    start += 0.5f;
                    // this.notifyAll();
                } else {
                    Thread.yield();
                    // this.wait();
                }
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (start > n)
                    break;
                if (start % 1 == 0 && ((int) start & 1) == 1) {
                    printNumber.accept((int) start);
                    start += 0.5f;
                    // this.notifyAll();
                } else {
                    Thread.yield();
                    // this.wait();
                }
            }
        }
    }
}