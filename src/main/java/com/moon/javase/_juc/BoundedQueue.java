package com.moon.javase._juc;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用Condition实现生产者消费者模式
 */
public class BoundedQueue {
    //生产者容器
    private LinkedList<Object> buffer;
    //容器最大值是多少
    private int maxSize;
    private Lock lock;
    private Condition fullCondition;
    private Condition notFullCondition;

    BoundedQueue(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        fullCondition = lock.newCondition();
        notFullCondition = lock.newCondition();
    }

    /**
     * 生产者
     *
     * @param obj
     * @throws InterruptedException
     */
    public void put(Object obj) throws InterruptedException {
        //获取锁
        lock.lock();
        try {
            while (maxSize >= buffer.size()) {
                //满了，添加的线程进入等待状态
                notFullCondition.await();
            }
            buffer.add(obj);
            //通知
            fullCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 消费者
     *
     * @return
     * @throws InterruptedException
     */
    public Object get() throws InterruptedException {
        Object obj;
        lock.lock();
        try {
            while (buffer.size() == 0) {
                //队列中没有数据了 线程进入等待状态
                fullCondition.await();
            }
            obj = buffer.poll();
            //通知
            notFullCondition.signal();
        } finally {
            lock.unlock();
        }
        return obj;
    }

}
