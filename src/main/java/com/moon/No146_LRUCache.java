package com.moon;

import java.util.HashMap;
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
    //         this.capacity = capacity;
    //         this.map = new HashMap<>(capacity);
    //     }
    //
    //     public int get(int key) {
    //         Node node = this.map.get(key);
    //         if (node == null) {
    //             System.out.println(-1);
    //             return -1;
    //         }
    //         node.setVersion(version.incrementAndGet());
    //         this.map.remove(key);
    //         this.map.put(key, node);
    //         System.out.println(node.getValue());
    //         return node.getValue();
    //     }
    //
    //     public void put(int key, int value) {
    //         Node node;
    //         Integer size = this.map.size();
    //         if (this.capacity <= size && !this.map.containsKey(key)) {
    //             node = this.map.values().stream().min(Comparator.comparingLong(Node::getVersion)).orElse(new Node());
    //             this.map.remove(node.getKey());
    //             node.setKey(key);
    //             node.setValue(value);
    //             node.setVersion(version.incrementAndGet());
    //         } else {
    //             node = new Node(key, value, version.incrementAndGet());
    //         }
    //         this.map.put(key, node);
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
    //             this.version = version;
    //             this.value = value;
    //             this.key = key;
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
    //             this.version = version;
    //         }
    //
    //         public Integer getValue() {
    //             return value;
    //         }
    //
    //         public void setValue(Integer value) {
    //             this.value = value;
    //         }
    //
    //         public Integer getKey() {
    //             return key;
    //         }
    //
    //         public void setKey(Integer key) {
    //             this.key = key;
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

    /**
     * hashMap + 双向链表
     */
    // static class LRUCache {
    //     /**
    //      * 存储map
    //      */
    //     private HashMap<Integer, Node> map;
    //     /**
    //      * 存储map
    //      */
    //     private Integer capacity;
    //     /**
    //      * 头结点(不存放数据)
    //      */
    //     private Node head;
    //     /**
    //      * 尾结点(不存放数据)
    //      */
    //     private Node tail;
    //
    //     public LRUCache(int capacity) {
    //         this.head = Node.headNode(-1, -1);
    //         this.tail = Node.tailNode(-1, -1);
    //         head.next = tail;
    //         tail.prev = head;
    //         this.capacity = capacity;
    //         this.map = new HashMap<>(capacity);
    //     }
    //
    //     public int get(int key) {
    //         Node node = this.map.get(key);
    //         if (node == null) {
    //             System.out.println(-1);
    //             return -1;
    //         }
    //         moveToTail(node);
    //         System.out.println(node.getValue());
    //         return node.getValue();
    //     }
    //
    //     public void put(int key, int value) {
    //         Integer size = this.map.size();
    //         if (this.capacity <= size) {
    //             if (!this.map.containsKey(key)) {
    //                 deleteNode(head.getNext());
    //             } else {
    //                 deleteNode(this.map.get(key));
    //             }
    //         }
    //         addNode(key, value);
    //         System.out.println("null");
    //     }
    //
    //     private void deleteNode(Node node) {
    //         node.getPrev().setNext(node.getNext());
    //         node.getNext().setPrev(node.getPrev());
    //         node.setNext(null);
    //         node.setPrev(null);
    //         this.map.remove(node.getKey());
    //     }
    //
    //     private void moveToTail(Node node) {
    //         node.getPrev().setNext(node.getNext());
    //         node.getNext().setPrev(node.getPrev());
    //         node.setNext(null);
    //         node.setPrev(null);
    //         this.map.remove(node.getKey());
    //         addNode(node.getKey(), node.getValue());
    //     }
    //
    //     private Node addNode(int key, int value) {
    //         Node temp = tail.getPrev();
    //         Node node = new Node(key, value, temp, tail);
    //         temp.setNext(node);
    //         tail.setPrev(node);
    //         this.map.put(key, node);
    //         return node;
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
    //         public static Node headNode(Integer key, Integer value) {
    //             Node node = new Node(key, value);
    //             node.setPrev(null);
    //             return node;
    //         }
    //
    //         public static Node tailNode(Integer key, Integer value) {
    //             Node node = new Node(key, value);
    //             node.setNext(null);
    //             return node;
    //         }
    //
    //         public Node(Integer key, Integer value, Node prev, Node next) {
    //             this.key = key;
    //             this.value = value;
    //             this.prev = prev;
    //             this.next = next;
    //         }
    //
    //         public Node() {
    //         }
    //
    //         public Node getPrev() {
    //             return prev;
    //         }
    //
    //         public void setPrev(Node prev) {
    //             this.prev = prev;
    //         }
    //
    //         public Node getNext() {
    //             return next;
    //         }
    //
    //         public void setNext(Node next) {
    //             this.next = next;
    //         }
    //
    //         public Integer getValue() {
    //             return value;
    //         }
    //
    //         public void setValue(Integer value) {
    //             this.value = value;
    //         }
    //
    //         public Integer getKey() {
    //             return key;
    //         }
    //
    //         public void setKey(Integer key) {
    //             this.key = key;
    //         }
    //
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
    static class LRUCache {
        static class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;

            public DLinkedNode() {
            }

            public DLinkedNode(int _key, int _value) {
                key = _key;
                value = _value;
            }
        }

        private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
        private int size;
        private int capacity;
        private DLinkedNode head, tail;

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            // 使用伪头部和伪尾部节点
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                return -1;
            }
            // 如果 key 存在，先通过哈希表定位，再移到头部
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                // 如果 key 不存在，创建一个新的节点
                DLinkedNode newNode = new DLinkedNode(key, value);
                // 添加进哈希表
                cache.put(key, newNode);
                // 添加至双向链表的头部
                addToHead(newNode);
                ++size;
                if (size > capacity) {
                    // 如果超出容量，删除双向链表的尾部节点
                    DLinkedNode tail = removeTail();
                    // 删除哈希表中对应的项
                    cache.remove(tail.key);
                    --size;
                }
            } else {
                // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
                node.value = value;
                moveToHead(node);
            }
        }

        private void addToHead(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        private DLinkedNode removeTail() {
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
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

        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);
        cache.get(1);
        cache.get(2);
        cache.put(5, 5);
    }
}


/**
 * ["LRUCache","put","put","put","put","get","get"]
 * [[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */