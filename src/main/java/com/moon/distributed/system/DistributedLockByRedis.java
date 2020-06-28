package com.moon.distributed.system;

import com.google.common.collect.Lists;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.jedis.params.SetParams;

import java.util.List;
import java.util.UUID;

/**
 * 分布式锁的redis实现
 *
 * @author moon
 */
public class DistributedLockByRedis {

    private final JedisPool jedisPool;
    /**
     * 锁的前缀
     */
    private final static String KEY_PREF = "lock:";

    public DistributedLockByRedis(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 加锁
     *
     * @param lockName       String 锁的名称(key)
     * @param acquireTimeout long 获取超时时间
     * @param timeout        long 锁的超时时间
     * @return 锁标识
     */
    public String lockWithTimeout(String lockName, long acquireTimeout, long timeout) {
        SetParams params = SetParams.setParams().nx().px(acquireTimeout);

        try (Jedis conn = jedisPool.getResource()) {
            // 随机生成一个value
            String identifier = UUID.randomUUID().toString();
            // 锁名,即 key值
            String lockKey = KEY_PREF + lockName;
            // 超时时间, 上锁后超过此时间则自动释放锁
            int lockExpire = (int) (timeout / 1000);

            // 获取锁的超时时间,超过这个时间则放弃获取锁
            long end = System.currentTimeMillis() + acquireTimeout;
            while (System.currentTimeMillis() < end) {
                // 注意，这里设置锁和设置超时时间不是原子操作，所以可能会有问题，这里推荐使用lock()中的用法
                if (conn.setnx(lockKey, identifier) == 1) {
                    conn.expire(lockKey, lockExpire);
                    // 返回value值,用于释放锁时间确认
                    return identifier;
                }
                // 返回-1代表key没有设置超时时间,为key设置一个超时时间
                if (conn.ttl(lockKey) == -1) {
                    conn.expire(lockKey, lockExpire);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (JedisException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 释放锁
     *
     * @param lockName 锁key
     * @param id       释放锁的标识
     * @return boolean
     */
    public boolean releaseLock(String lockName, String id) {
        String lockKey = KEY_PREF + lockName;
        boolean retFlag = false;
        try (Jedis conn = jedisPool.getResource()) {
            while (true) {
                // 监视lock, 准备开始事务
                conn.watch(lockKey);
                // 通过前面返回的value值判断是不是该锁,若时该锁,则删除释放锁
                if (id.equals(conn.get(lockKey))) {
                    Transaction transaction = conn.multi();
                    transaction.del(lockKey);
                    List<Object> results = transaction.exec();
                    if (results == null) {
                        continue;
                    }
                    retFlag = true;
                }
                conn.unwatch();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retFlag;
    }

    // ---------------------------------------------------------------------------------------------//

    /**
     * 加锁
     *
     * @param lockName       锁的名称(key)
     * @param id             锁id（可以使用UUID或者其他手段来表示）
     * @param acquireTimeout 获取超时时间
     * @param timeout        锁的超时时间
     */
    public boolean lock(String lockName, String id, long acquireTimeout, long timeout) {
        // 锁名,即 key值
        String lockKey = KEY_PREF + lockName;
        // SET命令的参数
        SetParams params = SetParams.setParams().nx().px(acquireTimeout);
        try (Jedis jedis = jedisPool.getResource()) {
            long start = System.currentTimeMillis();
            while (true) {
                //SET命令返回OK ，则证明获取锁成功
                String lock = jedis.set(lockKey, id, params);
                if ("OK".equals(lock)) {
                    return true;
                }
                //否则循环等待，在timeout时间内仍未获取到锁，则获取失败
                long l = System.currentTimeMillis() - start;
                if (l >= timeout) {
                    return false;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 解锁
     *
     * @param id       锁id（可以使用UUID或者其他手段来表示）
     * @param lockName 锁的名称(key)
     */
    public boolean unlock(String id, String lockName) {
        // 锁名,即 key值
        String lockKey = KEY_PREF + lockName;
        try (Jedis jedis = jedisPool.getResource()) {
            String script = "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                    "   return redis.call('del',KEYS[1]) " +
                    "else" +
                    "   return 0 " +
                    "end";
            Object result = jedis.eval(script, Lists.newArrayList(lockKey), Lists.newArrayList(id));
            return "1".equals(result.toString());
        }
    }
}