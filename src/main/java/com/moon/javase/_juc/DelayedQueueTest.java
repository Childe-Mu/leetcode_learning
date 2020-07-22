package com.moon.javase._juc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列测试demo
 * 由Delayed定义可以得知，队列元素需要实现getDelay(TimeUnit unit)方法和compareTo(Delayed o)方法, getDelay定义了剩余到期时间，
 * compareTo方法定义了元素排序规则，注意，元素的排序规则影响了元素的获取顺序，将在后面说明。
 */
public class DelayedQueueTest {
    public static void main(String[] args) throws InterruptedException {
        Item item1 = new Item("item1", 20, TimeUnit.SECONDS);
        Item item2 = new Item("item2", 100, TimeUnit.SECONDS);
        Item item3 = new Item("item3", 50, TimeUnit.SECONDS);
        DelayQueue<Item> queue = new DelayQueue<>();
        queue.put(item1);
        queue.put(item2);
        queue.put(item3);
        System.out.println("begin time:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        for (int i = 0; i < 3; i++) {
            // 阻塞方式获取
            Item take = queue.take();
            System.out.format("name:{%s}, time:{%s}\n", take.name, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        }
        // 非阻塞方式获取
        // Item take;
        // while (!queue.isEmpty()) {
        //     if ((take = queue.poll()) != null) {
        //         System.out.format("name:{%s}, time:{%s}\n", take.name, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        //     }
        // }
    }

}

class Item implements Delayed {
    /* 触发时间*/
    private long time;
    String name;

    public Item(String name, long time, TimeUnit unit) {
        this.name = name;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        // 从这个打印可以看出，刚开始差不多每1.5毫秒调用一次getDelay()方法，随着时间靠近队列首位元素，频率越来越快，最终可以达到微秒级别。
        // 用来比较当前时间和队列首位元素时间，如果小于等于0，则取出，
        // 这样导致CPU一直忙碌，浪费性能
        System.out.println(time - System.currentTimeMillis());
        // int i = 0;
        // if (++i % 10 == 0) {
        //     System.out.println();
        // }
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        Item item = (Item) o;
        long diff = this.time - item.time;
        // 改成>=会造成问题
        if (diff <= 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Item{" +
                "time=" + time +
                ", name='" + name + '\'' +
                '}';
    }
}