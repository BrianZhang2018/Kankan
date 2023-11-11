package category.ImplDataStructure.LRU;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;
class LRUCacheThreadSafeVersion {
    public static void main(String[] args) {
        LRUCacheThreadSafeVersion cache = new LRUCacheThreadSafeVersion(2);
        cache.put(1, 1);            // cache is {1=1}
        cache.put(2, 2);            // cache is {2=2,1=1}
        System.out.println(cache.get(1));    // return 1, update LRU to {1=1, 2=2} since we just get key 1
        cache.put(3, 3);            // LRU (tail)key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(cache.get(2));    // returns -1 (not found)
        cache.put(4, 4);            // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(cache.get(1));    // return -1 (not found)
        System.out.println(cache.get(3));    // return 3
        System.out.println(cache.get(4));    // return 4
    }
    class DllNode {
        int key, value;
        DllNode prev, next;
        public DllNode(int key, int value) {
            this.value = value;
            this.key = key;
        }
    }
    private final AtomicInteger size;
    private final int capacity;
    private final Map<Integer, DllNode> cache;
    private final DllNode head, tail; // Head and Tail of the DLL
    private final ReentrantLock lock; // ReentrantLock is mutually exclusive lock
    public LRUCacheThreadSafeVersion(int capacity) {
        this.capacity = capacity;
        this.size = new AtomicInteger();
        this.cache = new ConcurrentHashMap<>(); // ConcurrentHashMap, segment/bucket locking, default 16 threads to write at the same time, better performance compare with sync map
        this.head = new DllNode(0, 0);
        this.tail = new DllNode(0, 0);
        this.head.next = tail;
        this.tail.prev = head;
        this.lock = new ReentrantLock();
    }
    public int get(int key) {
        this.lock.lock();
        // the try-finally block ensures that write lock is always released, regardless of any exceptions that may occur.
        try {
            DllNode node = cache.getOrDefault(key, null);
            if (node != null) {
                // moving the key to the front of the DLL
                removeNode(node);
                addToHead(node);
                return node.value;
            }
            return -1;
        } finally {
            this.lock.unlock();
        }
    }
    public void put(int key, int val) {
        this.lock.lock();
        try {
            DllNode node = cache.get(key);
            if (node != null) {
                removeNode(node);
            }
            if (size.get() == capacity) {
                removeNode(tail.prev); // remove tail.prev node
            }
            addToHead(new DllNode(key, val));
        } finally {
            this.lock.unlock();
        }
    }
    private void removeNode(final DllNode node) {
            cache.remove(node.key);
            size.decrementAndGet();

            node.prev.next = node.next;
            node.next.prev = node.prev;
    }
    private void addToHead(final DllNode node) {
            cache.put(node.key, node);
            size.incrementAndGet();

            node.prev = this.head;
            node.next = this.head.next;
            this.head.next.prev = node;
            this.head.next = node;
    }
}

