package com.moon.distributed.system;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 通过Redisson实现分布式锁
 * <br/>
 * Redisson是架设在Redis基础上的一个Java驻内存数据网格（In-Memory Data Grid）。
 * 充分的利用了Redis键值数据库提供的一系列优势，基于Java实用工具包中常用接口，
 * 为使用者提供了一系列具有分布式特性的常用工具类。使得原本作为协调单机多线程并发程序的
 * 工具包获得了协调分布式多机多线程并发系统的能力，大大降低了设计和研发大规模分布式系统的难度。
 * 同时结合各富特色的分布式服务，更进一步简化了分布式环境中程序相互之间的协作。
 *
 * @author moon
 */
public class DistributedLockByRedisson {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.useSingleServer().setPassword("redis1234");
        final RedissonClient client = Redisson.create(config);
        RLock lock = client.getLock("lock1");
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
    }
}

