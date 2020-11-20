package com.moon.leetcode;

import java.util.function.IntConsumer;

/**
 * 1195. 交替打印字符串
 * <p>
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 * <p>
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 * <p>
 * 假设有这么一个类：
 * <p>
 * <code>
 * class FizzBuzz {
 * public FizzBuzz(int n) { ... }               // constructor
 * public void fizz(printFizz) { ... }          // only output "fizz"
 * public void buzz(printBuzz) { ... }          // only output "buzz"
 * public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
 * public void number(printNumber) { ... }      // only output the numbers
 * }
 * </code>
 * <p>
 * 请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：
 * <p>
 * 线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
 * 线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
 * 线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
 * 线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
 */
public class No1195_FizzBuzz {

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        Runnable r1 = () -> System.out.println("fizz, ");
        Runnable r2 = () -> System.out.println("buzz, ");
        Runnable r3 = () -> System.out.println("fizzbuzz, ");
        IntConsumer ic = value -> System.out.println(value + ", ");

        new Thread(() -> {
            try {
                fizzBuzz.fizz(r1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                fizzBuzz.buzz(r2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
        new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(r3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3").start();
        new Thread(() -> {
            try {
                fizzBuzz.number(ic);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t4").start();
    }
}

/**
 * 用栅栏CyclicBarrier代码写起来最为简单，但是耗时不用想，会很久，因为必须要 4*n 次线程切换 和 4*n 次判断处理，绝对比单线程慢好多
 * <p>
 * 相比于CyclicBarrier，用Semaphore效率会略高一些，这是因为避免一些不必要的线程切换
 * <p>
 * 使用 ReentrantLock 和 Condition，能用信号量的地方一定可以用管程，但是会麻烦许多
 * <p>
 * 相比于CyclicBarrier，用Semaphore效率会略高一些，这是因为避免一些不必要的线程切换
 * <p>
 * 使用 ReentrantLock 和 Condition，能用信号量的地方一定可以用管程，但是会麻烦许多
 * <p>
 * 相比于CyclicBarrier，用Semaphore效率会略高一些，这是因为避免一些不必要的线程切换
 * <p>
 * 使用 ReentrantLock 和 Condition，能用信号量的地方一定可以用管程，但是会麻烦许多
 * <p>
 * 相比于CyclicBarrier，用Semaphore效率会略高一些，这是因为避免一些不必要的线程切换
 * <p>
 * 使用 ReentrantLock 和 Condition，能用信号量的地方一定可以用管程，但是会麻烦许多
 */
// class FizzBuzz {
//     private int n;
//     private CyclicBarrier c = new CyclicBarrier(4);
//
//     public FizzBuzz(int n) {
//         this.n = n;
//     }
//
//     // printFizz.run() outputs "fizz".
//     public void fizz(Runnable printFizz) throws InterruptedException {
//         for (int i = 1; i <= n; i++) {
//             if (i % 3 == 0 && i % 5 != 0) {
//                 printFizz.run();
//             }
//             try {
//                 c.await();
//             } catch (BrokenBarrierException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
//
//     // printBuzz.run() outputs "buzz".
//     public void buzz(Runnable printBuzz) throws InterruptedException {
//         for (int i = 1; i <= n; i++) {
//             if (i % 5 == 0 && i % 3 != 0) {
//                 printBuzz.run();
//             }
//             try {
//                 c.await();
//             } catch (BrokenBarrierException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
//
//     // printFizzBuzz.run() outputs "fizzbuzz".
//     public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
//         for (int i = 1; i <= n; i++) {
//             if (i % 5 == 0 && i % 3 == 0) {
//                 printFizzBuzz.run();
//             }
//             try {
//                 c.await();
//             } catch (BrokenBarrierException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
//
//     // printNumber.accept(x) outputs "x", where x is an integer.
//     public void number(IntConsumer printNumber) throws InterruptedException {
//         for (int i = 1; i <= n; i++) {
//             if (i % 3 != 0 && i % 5 != 0) {
//                 printNumber.accept(i);
//             }
//             try {
//                 c.await();
//             } catch (BrokenBarrierException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
// }
//
/**
 * 相比于CyclicBarrier，用Semaphore效率会略高一些，这是因为避免一些不必要的线程切换
 */
// class FizzBuzz {
//     private int n;
//     private Semaphore s1 = new Semaphore(0);
//     private Semaphore s2 = new Semaphore(0);
//     private Semaphore s3 = new Semaphore(0);
//     private Semaphore s4 = new Semaphore(1);
//
//     public FizzBuzz(int n) {
//         this.n = n;
//     }
//
//     // printFizz.run() outputs "fizz".
//     public void fizz(Runnable printFizz) throws InterruptedException {
//         int i = 3;
//         while (i <= n) {
//             s1.acquire();
//             printFizz.run();
//             i = ((i + 3) % 15 == 0) ? i + 6 : i + 3;
//             s4.release();
//         }
//     }
//
//     // printBuzz.run() outputs "buzz".
//     public void buzz(Runnable printBuzz) throws InterruptedException {
//         int i = 5;
//         while (i <= n) {
//             s2.acquire();
//             printBuzz.run();
//             i = ((i + 5) % 15 == 0) ? i + 10 : i + 5;
//             s4.release();
//         }
//     }
//
//     // printFizzBuzz.run() outputs "fizzbuzz".
//     public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
//         int i = 15;
//         while (i <= n) {
//             System.out.println("----" + Thread.currentThread().getName());
//             s3.acquire();
//             printFizzBuzz.run();
//             i += 15;
//             s4.release();
//         }
//     }
//
//     // printNumber.accept(x) outputs "x", where x is an integer.
//     public void number(IntConsumer printNumber) throws InterruptedException {
//         for (int i = 1; i <= n; i++) {
//             s4.acquire();
//             if (i % 3 != 0 && i % 5 != 0) {
//                 printNumber.accept(i);
//                 s4.release();
//             } else if (i % 3 == 0 && i % 5 != 0) {
//                 s1.release();
//             } else if (i % 3 != 0) {
//                 s2.release();
//             } else {
//                 s3.release();
//             }
//         }
//     }
// }
/**
 * 使用 ReentrantLock 和 Condition，能用信号量的地方一定可以用管程，但是会麻烦许多
 */
// class FizzBuzz {
//     private int n;
//     private volatile boolean b = true;
//     private Lock lock = new ReentrantLock();
//     private Condition c1 = lock.newCondition();
//     private Condition c2 = lock.newCondition();
//     private Condition c3 = lock.newCondition();
//     private Condition c4 = lock.newCondition();
//
//     public FizzBuzz(int n) {
//         this.n = n;
//     }
//
//     // printFizz.run() outputs "fizz".
//     public void fizz(Runnable printFizz) throws InterruptedException {
//         int i = 3;
//         while (i <= n) {
//             lock.lock();
//             while (b) {
//                 c1.await();
//             }
//             printFizz.run();
//             i = ((i + 3) % 15 == 0) ? i + 6 : i + 3;
//             b = true;
//             c4.signal();
//             lock.unlock();
//         }
//     }
//
//     // printBuzz.run() outputs "buzz".
//     public void buzz(Runnable printBuzz) throws InterruptedException {
//         int i = 5;
//         while (i <= n) {
//             lock.lock();
//             while (b) {
//                 c2.await();
//             }
//             printBuzz.run();
//             i = ((i + 5) % 15 == 0) ? i + 10 : i + 5;
//             b = true;
//             c4.signal();
//             lock.unlock();
//         }
//     }
//
//     // printFizzBuzz.run() outputs "fizzbuzz".
//     public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
//         int i = 15;
//         while (i <= n) {
//             lock.lock();
//             while (b) {
//                 c3.await();
//             }
//             printFizzBuzz.run();
//             i += 15;
//             b = true;
//             c4.signal();
//             lock.unlock();
//         }
//     }
//
//     // printNumber.accept(x) outputs "x", where x is an integer.
//     public void number(IntConsumer printNumber) throws InterruptedException {
//         for (int i = 1; i <= n; i++) {
//             lock.lock();
//             while (!b) {
//                 c4.await();
//             }
//             if (i % 3 != 0 && i % 5 != 0) {
//                 printNumber.accept(i);
//                 c4.signal();
//             } else if (i % 3 == 0 && i % 5 != 0) {
//                 b = false;
//                 c1.signal();
//             } else if (i % 3 != 0) {
//                 b = false;
//                 c2.signal();
//             } else {
//                 b = false;
//                 c3.signal();
//             }
//             lock.unlock();
//         }
//     }
// }

/**
 * 使用
 */
class FizzBuzz {
    private int n;
    private volatile int i = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        synchronized (this) {
            while (i <= n) {
                while (i % 3 != 0 || i % 15 == 0) {
                    notifyAll();
                    wait();
                    if (i > n) {
                        notifyAll();
                        return;
                    }
                }
                printFizz.run();
                i++;
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        synchronized (this) {
            while (i <= n) {
                while (i % 5 != 0 || i % 15 == 0) {
                    notifyAll();
                    wait();
                    if (i > n) {
                        notifyAll();
                        return;
                    }
                }
                printBuzz.run();
                i++;
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        synchronized (this) {
            while (i <= n) {
                while (i % 15 != 0) {
                    notifyAll();
                    wait();
                    if (i > n) {
                        notifyAll();
                        return;
                    }
                }
                printFizzBuzz.run();
                i++;
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        synchronized (this) {
            while (i <= n) {
                while (i % 3 == 0 || i % 5 == 0) {
                    notifyAll();
                    wait();
                    if (i > n) {
                        notifyAll();
                        return;
                    }
                }
                printNumber.accept(i);
                i++;
            }
        }
    }
}