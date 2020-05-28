package com.moon;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146. LRU缓存机制<br/>
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。<br/>
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。<br/>
 * 写入数据 put(key, value) - 如果密钥已经存在，则变更其数据值；如果密钥不存在，则插入该组「密钥/数据值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。<br/>
 * <p>
 * 进阶:<br/>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？<br/>
 * <p>
 * 示例:<br/>
 * <code>
 * LRUCache cache = new LRUCache( 2 );<br/>
 * cache.put(1,1);<br/>
 * cache.put(2,2);
 * cache.get(1);       // 返回  1<br/>
 * cache.put(3,3);    // 该操作会使得密钥 2 作废<br/>
 * cache.get(2);       // 返回 -1 (未找到)<br/>
 * cache.put(4,4);    // 该操作会使得密钥 1 作废<br/>
 * cache.get(1);       // 返回 -1 (未找到)<br/>
 * cache.get(3);       // 返回  3<br/>
 * cache.get(4);       // 返回  4<br/>
 * </code>
 */
public class No146_LRUCache {
    // /**
    //  * hashMap + 版本号
    //  */
    // static class LRUCache {
    //     /**
    //      * 版本号
    //      */
    //     private volatile AtomicLong version = new AtomicLong(0);
    //
    //     /**
    //      * 存储map
    //      */
    //     private HashMap<Integer, Node> map;
    //     /**
    //      * 存储map
    //      */
    //     private Integer capacity;
    //
    //     public LRUCache(int capacity) {
    //         capacity = capacity;
    //         map = new HashMap<>(capacity);
    //     }
    //
    //     public int get(int key) {
    //         Node node = map.get(key);
    //         if (node == null) {
    //             System.out.println(-1);
    //             return -1;
    //         }
    //         node.setVersion(version.incrementAndGet());
    //         map.remove(key);
    //         map.put(key, node);
    //         System.out.println(node.getValue());
    //         return node.getValue();
    //     }
    //
    //     public void put(int key, int value) {
    //         Node node;
    //         Integer size = map.size();
    //         if (capacity <= size && !map.containsKey(key)) {
    //             node = map.values().stream().min(Comparator.comparingLong(Node::getVersion)).orElse(new Node());
    //             map.remove(node.getKey());
    //             node.setKey(key);
    //             node.setValue(value);
    //             node.setVersion(version.incrementAndGet());
    //         } else {
    //             node = new Node(key, value, version.incrementAndGet());
    //         }
    //         map.put(key, node);
    //         System.out.println("null");
    //     }
    //
    //     private static class Node {
    //         /**
    //          * 最后使用时间戳
    //          */
    //         private Long version;
    //         /**
    //          * value
    //          */
    //         private Integer value;
    //         /**
    //          * value
    //          */
    //         private Integer key;
    //
    //         public Node(Integer key, Integer value, Long version) {
    //             version = version;
    //             value = value;
    //             key = key;
    //         }
    //
    //         public Node() {
    //         }
    //
    //         public Long getVersion() {
    //             return version;
    //         }
    //
    //         public void setVersion(Long version) {
    //             version = version;
    //         }
    //
    //         public Integer getValue() {
    //             return value;
    //         }
    //
    //         public void setValue(Integer value) {
    //             value = value;
    //         }
    //
    //         public Integer getKey() {
    //             return key;
    //         }
    //
    //         public void setKey(Integer key) {
    //             key = key;
    //         }
    //
    //         @Override
    //         public String toString() {
    //             return "Node{" +
    //                     "version=" + version +
    //                     ", value=" + value +
    //                     ", key=" + key +
    //                     '}';
    //         }
    //     }

    // /**
    //  * hashMap + 双向链表
    //  */
    // static class LRUCache {
    //     /**
    //      * 存储map
    //      */
    //     private HashMap<Integer, Node> map;
    //     /**
    //      * 存储容量
    //      */
    //     private Integer capacity;
    //
    //     /**
    //      * 头结点(不存放数据)
    //      */
    //     private Node head;
    //
    //     /**
    //      * 尾结点(不存放数据)
    //      */
    //     private Node tail;
    //
    //     public LRUCache(int capacity) {
    //         this.head = new Node();
    //         this.tail = new Node();
    //         this.head.next = this.tail;
    //         this.tail.prev = this.head;
    //         this.capacity = capacity;
    //         this.map = new HashMap<>(capacity);
    //     }
    //
    //     public int get(int key) {
    //         Node node = map.get(key);
    //         if (node == null) {
    //             System.out.println(-1);
    //             return -1;
    //         }
    //         moveToTail(node);
    //         System.out.println(node.value);
    //         return node.value;
    //     }
    //
    //     public void put(int key, int value) {
    //         if (map.containsKey(key)) {
    //             Node node = map.get(key);
    //             node.value = value;
    //             moveToTail(node);
    //         } else {
    //             Node node = new Node(key, value);
    //             map.put(key, node);
    //             addToTail(node);
    //             if (map.size() > capacity) {
    //                 Node oldest = removeOldestNode();
    //                 map.remove(oldest.key);
    //             }
    //         }
    //         System.out.println("null");
    //     }
    //
    //     private Node removeOldestNode() {
    //         Node node = head.next;
    //         removeNode(node);
    //         return node;
    //     }
    //
    //     private void addToTail(Node node) {
    //         node.prev = tail.prev;
    //         node.next = tail;
    //         tail.prev.next = node;
    //         tail.prev = node;
    //     }
    //
    //     private void moveToTail(Node node) {
    //         removeNode(node);
    //         addToTail(node);
    //     }
    //
    //     private void removeNode(Node node) {
    //         node.prev.next = node.next;
    //         node.next.prev = node.prev;
    //     }
    //
    //     private static class Node {
    //         /**
    //          * key
    //          */
    //         private Integer key;
    //         /**
    //          * value
    //          */
    //         private Integer value;
    //         /**
    //          * 前驱节点
    //          */
    //         private Node prev;
    //         /**
    //          * 后置节点
    //          */
    //         private Node next;
    //
    //         public Node(Integer key, Integer value) {
    //             this.key = key;
    //             this.value = value;
    //         }
    //
    //         public Node() {
    //         }
    //         @Override
    //         public String toString() {
    //             return "Node{" +
    //                     "key=" + key +
    //                     ", value=" + value +
    //                     '}';
    //         }
    //     }
    //
    // }

    /**
     * LinkedHashMap
     */
    static class LRUCache {

        private Cache<Integer, Integer> cache;

        public LRUCache(int maxEntries) {
            this.cache = new Cache<>(maxEntries);
        }

        public Integer get(Integer key) {
            Integer value = cache.get(key);
            if (value == null) {
                value = -1;
            }
            System.out.println(value);
            return value;
        }

        public Integer put(Integer key, Integer value) {
            System.out.println("null");
            return cache.put(key, value);
        }

        static class Cache<K, V> extends LinkedHashMap<K, V> {

            private int maxEntries;

            public Cache(int maxEntries) {
                super(16, 0.75f, true);
                this.maxEntries = maxEntries;
            }

            @Override
            public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxEntries;
            }
        }

    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);/* 缓存容量 */

        // cache.put(1, 1);
        // cache.put(2, 2);
        // cache.get(1);       // 返回  1
        // cache.put(3, 3);    // 该操作会使得密钥 2 作废
        // cache.get(2);       // 返回 -1 (未找到)
        // cache.put(4, 4);    // 该操作会使得密钥 1 作废
        // cache.get(1);       // 返回 -1 (未找到)
        // cache.get(3);       // 返回  3
        // cache.get(4);       // 返回  4

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        cache.get(2);
        cache.put(4, 4);
        cache.get(1);
        cache.get(3);
        cache.get(4);
    }
}


/**
 * ["LRUCache","put","put","put","put","get","get"]
 * [[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
 * [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */