package category.RightDataStructure.LRU;

import java.util.*;

/**
 * https://leetcode.com/problems/lru-cache/
 *
 * LRU (Least recently used)
 * Solution: (HashMap + Doubly LinkedList) = Java LinkedHashMap
 * doubly linkedList: create a DllNode which will be used to generated a doubly linkedList
 *
 * https://medium.com/@krishankantsinghal/my-first-blog-on-medium-583159139237
 *
 * In HashMap, will hold the "keys" and "address" of the Nodes in Doubly LinkedList. And Doubly LinkedList will hold the "values" of keys.
 *
 * As We need to keep track of recently used entries. We will "remove element from bottom" and "add element on the top of LinkedList" and
 * whenever any entry is accessed again, it will be moved to the top. So, recently used entries will be on Top and Least used will be on Bottom.
 */
public class LRUCache {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2); // capacity is 2
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }

    private int cacheSize;
    private Map<Integer, DllNode> map = Collections.synchronizedMap(new HashMap()); // with sync enhanced
    private DllNode head = new DllNode(0, 0), tail = new DllNode(0, 0);

    public LRUCache(int capacity) {
        this.cacheSize = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            DllNode node = map.get(key);
            removeNode(node);
            addToTop(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            removeNode(map.get(key)); // remove existing node in LinkedList
        }

        if (map.size() == cacheSize) {
            removeNode(tail.prev);
        }

        addToTop(new DllNode(key, value));
    }

    public void removeNode(DllNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        synchronized (map){
            map.remove(node.key);
        }
    }

    public void addToTop(DllNode node) {
        synchronized (map){
            map.put(node.key, node);
        }
        DllNode headNext = head.next;
        head.next = node;
        node.prev = head;
        node.next = headNext;
        headNext.prev = node;
    }
}

class DllNode { // double linkedList node
    int key, value;
    DllNode prev, next;

    public DllNode(int key, int value) {
        this.value = value;
        this.key = key;
    }
}