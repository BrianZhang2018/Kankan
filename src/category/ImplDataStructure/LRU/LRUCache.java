package category.ImplDataStructure.LRU;

import java.util.*;

/**
 * https://leetcode.com/problems/lru-cache/
 *
 * LRU (Least recently used)
 * Solution: HashMap + Doubly LinkedList (self-defined)
 *                      Map<key, Node>
 *                              /
 * doubly linkedList:    |  | node | ... |
 *
 * https://medium.com/@krishankantsinghal/my-first-blog-on-medium-583159139237
 *
 * In HashMap, will hold the "key" and "address (node)" in Doubly LinkedList, and Doubly LinkedList will hold the "values" of keys.
 * As We need to keep track of recently used entries. We will "remove element from tail" and "add element on the top of LinkedList" and
 * whenever any entry is accessed again, it will be moved to the top. So, recently used entries will be on Top and Least used will be on Bottom.
 */
public class LRUCache{
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1);                     // cache is {1=1}
        lRUCache.put(2, 2);                     // cache is {2=2,1=1}
        System.out.println(lRUCache.get(1));    // return 1, update LRU to {1=1, 2=2} since we just get key 1
        lRUCache.put(3, 3);                     // LRU (tail)key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4);                     // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4
    }
    class DllNode { // double linkedList node
        private int key, value;
        private DllNode prev, next;
        public DllNode(int key, int value) {
            this.value = value;
            this.key = key;
        }
    }
    private int cacheSize;
    // sync is object level lock, bad performance. ConcurrentHashMap instead, segment/bucket locking, default 16 threads to write at the same time
    private Map<Integer, DllNode> cache = new HashMap();
    private DllNode head, tail;
    public LRUCache(int capacity) {
        this.cacheSize = capacity;
        head = new DllNode(0, 0);
        tail = new DllNode(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    // Returns the value of the key if found, otherwise returns -1 and Marks item as most recently used.
    public int get(int key) {
        if (!cache.containsKey(key)) return -1;

        DllNode node = cache.get(key);
        removeNode(node);
        addToTop(node);  // move to top since it just used
        return node.value;
    }
    // "Update" the value of the key if the "key exists" or adds the key-value pair to the cache.
    // If the number of keys "exceeds the capacity", evict the least recently used key.
    public void put(int key, int value) {
        if (cache.containsKey(key))
            removeNode(cache.get(key)); // remove existing node from doubly linkedList

        if (cache.size() == cacheSize)
            removeNode(tail.prev);

        addToTop(new DllNode(key, value));
    }

    public void removeNode(DllNode node) {
        cache.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void addToTop(DllNode node) {
        cache.put(node.key, node);
        DllNode headNext = head.next;
        head.next = node;
        node.prev = head;
        node.next = headNext;
        headNext.prev = node;

/*        head.next.prev = node;
        node.prev = head;
        node.next = head.next;
        head.next = node;*/
    }
}

