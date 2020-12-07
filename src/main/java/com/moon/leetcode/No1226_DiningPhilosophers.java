package com.moon.leetcode;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 1226. 哲学家进餐
 * <p>
 * 5 个沉默寡言的哲学家围坐在圆桌前，每人面前一盘意面。叉子放在哲学家之间的桌面上。（5 个哲学家，5 根叉子）
 * <p>
 * 所有的哲学家都只会在思考和进餐两种行为间交替。哲学家只有同时拿到左边和右边的叉子才能吃到面，而同一根叉子在同一时间只能被一个哲学家使用。
 * 每个哲学家吃完面后都需要把叉子放回桌面以供其他哲学家吃面。只要条件允许，哲学家可以拿起左边或者右边的叉子，但在没有同时拿到左右叉子时不能进食。
 * <p>
 * 假设面的数量没有限制，哲学家也能随便吃，不需要考虑吃不吃得下。
 * <p>
 * 设计一个进餐规则（并行算法）使得每个哲学家都不会挨饿；也就是说，在没有人知道别人什么时候想吃东西或思考的情况下，
 * 每个哲学家都可以在吃饭和思考之间一直交替下去。
 * <p>
 * 问题描述和图片来自维基百科 wikipedia.org
 * <p>
 * 哲学家从 0 到 4 按 顺时针 编号。请实现函数
 * void wantsToEat(philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork)：
 * <p>
 * philosopher 哲学家的编号。
 * pickLeftFork 和 pickRightFork 表示拿起左边或右边的叉子。
 * eat 表示吃面。
 * putLeftFork 和 putRightFork 表示放下左边或右边的叉子。
 * 由于哲学家不是在吃面就是在想着啥时候吃面，所以思考这个方法没有对应的回调。
 * 给你 5 个线程，每个都代表一个哲学家，请你使用类的同一个对象来模拟这个过程。在最后一次调用结束之前，可能会为同一个哲学家多次调用该函数。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：n = 1
 * 输出：[[4,2,1],[4,1,1],[0,1,1],[2,2,1],[2,1,1],[2,0,3],[2,1,2],[2,2,2],[4,0,3],[4,1,2],[0,2,1],[4,2,2],[3,2,1],[3,1,1],[0,0,3],[0,1,2],[0,2,2],[1,2,1],[1,1,1],[3,0,3],[3,1,2],[3,2,2],[1,0,3],[1,1,2],[1,2,2]]
 * 解释:
 * n 表示每个哲学家需要进餐的次数。
 * 输出数组描述了叉子的控制和进餐的调用，它的格式如下：
 * output[i] = [a, b, c] (3个整数)
 * - a 哲学家编号。
 * - b 指定叉子：{1 : 左边, 2 : 右边}.
 * - c 指定行为：{1 : 拿起, 2 : 放下, 3 : 吃面}。
 * 如 [4,2,1] 表示 4 号哲学家拿起了右边的叉子。
 * <p>
 * 提示：
 * 1 <= n <= 60
 */

/**
 * 测试类，main方法
 */
public class No1226_DiningPhilosophers {
    public static void main(String[] args) throws InterruptedException {
        int n = 10;
        List<List<Integer>> output = Lists.newArrayList();
        Runnable pickLeftFork = () -> System.out.println(Integer.valueOf(Thread.currentThread().getName()) + ", 1, 1");
        Runnable pickRightFork = () -> System.out.println(Integer.valueOf(Thread.currentThread().getName()) + ", 2, 1");
        Runnable eat = () -> System.out.println(Integer.valueOf(Thread.currentThread().getName()) + ", 0, 3");
        Runnable putLeftFork = () -> System.out.println(Integer.valueOf(Thread.currentThread().getName()) + ", 1, 2");
        Runnable putRightFork = () -> System.out.println(Integer.valueOf(Thread.currentThread().getName()) + ", 2, 2");

        DiningPhilosophers d = new DiningPhilosophers();

        new Thread(() -> {
            try {
                for (int i = 0; i < n; i++) {
                    d.wantsToEat(0, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "0").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < n; i++) {
                    d.wantsToEat(1, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "1").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < n; i++) {
                    d.wantsToEat(2, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "2").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < n; i++) {
                    d.wantsToEat(4, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "4").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < n; i++) {
                    d.wantsToEat(3, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "3").start();
    }
}

/**
 * 同一时间，只有一个哲学家可以就餐，即将多线程问题转为串行，
 * 正常理解这种做法违背多线程的思想，不能很好利用cpu，
 * 但是从测试结果来看，效率不错（应该只是这里会这样），
 * （执行耗时:14 ms,击败了97.22% 的Java用户，内存消耗:39.7 MB,击败了77.42% 的Java用户）
 * <p>
 * 同一时间，只有可以有两个哲学家可以就餐，
 * 但是为了简化问题，规定每次就餐的两个哲学家，顺序必须是(0,2)->(1,3)->(4)
 * <p>
 * ！！！！ 这种考虑方式是错误的，因为真正产生竞争的是叉子，而不是哲学家，所以需要关注并锁定应该叉子
 * <p>
 * 同一时间，只有可以有两个哲学家可以就餐，
 * 但是为了简化问题，规定每次就餐的两个哲学家，顺序必须是(0,2)->(1,3)->(4)
 * <p>
 * ！！！！ 这种考虑方式是错误的，因为真正产生竞争的是叉子，而不是哲学家，所以需要关注并锁定应该叉子
 * <p>
 * 同一时间，只有可以有两个哲学家可以就餐，
 * 但是为了简化问题，规定每次就餐的两个哲学家，顺序必须是(0,2)->(1,3)->(4)
 * <p>
 * ！！！！ 这种考虑方式是错误的，因为真正产生竞争的是叉子，而不是哲学家，所以需要关注并锁定应该叉子
 * <p>
 * 同一时间，只有可以有两个哲学家可以就餐，
 * 但是为了简化问题，规定每次就餐的两个哲学家，顺序必须是(0,2)->(1,3)->(4)
 * <p>
 * ！！！！ 这种考虑方式是错误的，因为真正产生竞争的是叉子，而不是哲学家，所以需要关注并锁定应该叉子
 * <p>
 * 同一时间，只有可以有两个哲学家可以就餐，
 * 但是为了简化问题，规定每次就餐的两个哲学家，顺序必须是(0,2)->(1,3)->(4)
 * <p>
 * ！！！！ 这种考虑方式是错误的，因为真正产生竞争的是叉子，而不是哲学家，所以需要关注并锁定应该叉子
 * <p>
 * 同一时间，只有可以有两个哲学家可以就餐，
 * 但是为了简化问题，规定每次就餐的两个哲学家，顺序必须是(0,2)->(1,3)->(4)
 * <p>
 * ！！！！ 这种考虑方式是错误的，因为真正产生竞争的是叉子，而不是哲学家，所以需要关注并锁定应该叉子
 */
// class DiningPhilosophers {
//     private Semaphore semaphore = new Semaphore(1);
//     public DiningPhilosophers() {
//
//     }
//     // call the run() method of any runnable to execute its code
//     public void wantsToEat(int philosopher,
//                            Runnable pickLeftFork,
//                            Runnable pickRightFork,
//                            Runnable eat,
//                            Runnable putLeftFork,
//                            Runnable putRightFork) throws InterruptedException {
//         semaphore.acquire();
//         pickLeftFork.run();
//         pickRightFork.run();
//         eat.run();
//         putLeftFork.run();
//         putRightFork.run();
//         semaphore.release();
//     }
// }

/**
 * 同一时间，只有可以有两个哲学家可以就餐，
 * 但是为了简化问题，规定每次就餐的两个哲学家，顺序必须是(0,2)->(1,3)->(4)
 *
 * ！！！！ 这种考虑方式是错误的，因为真正产生竞争的是叉子，而不是哲学家，所以需要关注并锁定应该叉子
 */
// class DiningPhilosophers {
//     private Semaphore s0 = new Semaphore(1);
//     private Semaphore s1 = new Semaphore(0);
//     private Semaphore s2 = new Semaphore(1);
//     private Semaphore s3 = new Semaphore(0);
//     private Semaphore s4 = new Semaphore(0);
//     private volatile boolean b0 = false;
//     private volatile boolean b1 = false;
//     private volatile boolean b2 = false;
//     private volatile boolean b3 = false;
//     private volatile boolean b4 = false;
//
//     public DiningPhilosophers() {
//
//     }
//
//     public void wantsToEat(int philosopher,
//                            Runnable pickLeftFork,
//                            Runnable pickRightFork,
//                            Runnable eat,
//                            Runnable putLeftFork,
//                            Runnable putRightFork) throws InterruptedException {
//         if (philosopher == 0) {
//             s0.acquire(1);
//             b0 = true;
//         } else if (philosopher == 1) {
//             s1.acquire(1);
//             b1 = true;
//         } else if (philosopher == 2) {
//             s2.acquire(1);
//             b2 = true;
//         } else if (philosopher == 3) {
//             s3.acquire(1);
//             b3 = true;
//         } else if (philosopher == 4) {
//             s4.acquire(1);
//             b4 = true;
//         }
//         pickLeftFork.run();
//         pickRightFork.run();
//         eat.run();
//         putLeftFork.run();
//         putRightFork.run();
//
//         if (b0 && b2) {
//             s1.release();
//             s3.release();
//             b0 = false;
//             b2 = false;
//         } else if (b1 && b3) {
//             s4.release();
//             b1 = false;
//             b3 = false;
//         } else {
//             s0.release();
//             s2.release();
//             b4 = false;
//         }
//
//         if (philosopher == 0) {
//             s0.release();
//         } else if (philosopher == 1) {
//             s1.release();
//         } else if (philosopher == 2) {
//             s2.release();
//         } else if (philosopher == 3) {
//             s3.release();
//         } else if (philosopher == 4) {
//             s4.release();
//         }
//
//     }
// }

/**
 * 同一时间，只有可以有两个哲学家可以就餐，</br>
 * 但是因为真正产生竞争的是叉子，所以从这方面来考虑</br>
 * 叉子的编号和顺时针下一位哲学家的编号相同，</br>
 * 可以先考虑一种简化场景，即先让五个哲学家中的一位获取左右叉子，然后判断剩下的3个刀叉可以被谁同时拥有，然后给对应的线程发信号，
 * 这种想法看似简单，但实现起来就会发现，有无数麻烦</br>
 * 思考这个问题的本质，就是为了避免死锁，即五位哲学家全部左手拿到叉子或者右手拿到叉子，既然如此，那我们只要破坏这一条件，即可破坏死锁，
 * 所以可以考虑让一部分哲学家先拿左叉子，一部分先拿右叉子，即可解决这一问题</br>
 * 可以从下面的实现看出，抓住了根源来考虑问题，就会发现实现思路简单，实现方案也非常简单</br>
 * 不过下面实现也有一个小问题，那就是一旦某个哲学家获取一个叉子，但是另一个没有获取到，这个时候就会一直持有这个叉子，
 * （但是后来思考发现，这个单独叉子即使放弃了，后续也还是要重新获取，还不如一直拿在手里，单等另一个叉子）
 */
class DiningPhilosophers {

    private Semaphore[] s = new Semaphore[]{
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1)
    };


    public DiningPhilosophers() {
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        // 先获取要拿到的两个叉子的下标
        int leftFork = (philosopher + 1) % 5;
        int rightFork = philosopher % 5;

        // 奇数哲学家先拿左边的叉子，偶数的哲学家先拿右边的叉子
        if ((philosopher & 1) == 1) {
            s[leftFork].acquire();
            s[rightFork].acquire();
        } else {
            s[rightFork].acquire();
            s[leftFork].acquire();
        }
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();

        s[leftFork].release();
        s[rightFork].release();
    }
}