package category.DataStructure.LRU;

import java.util.*;

/**
 * LRU (Least recently used)
 * Solution: (HashMap + Doubly LinkedList) = Java LinkedHashMap
 * doubly linkedList: create a DNode which will be used to generated a doubly linkedList
 *
 * https://medium.com/@krishankantsinghal/my-first-blog-on-medium-583159139237
 *
 * In HashMap, will hold the keys and address of the Nodes in Doubly LinkedList. And Doubly LinkedList will hold the values of keys.
 *
 * As We need to keep track of recently used entries. We will remove element from bottom and add element on the top of LinkedList
 * and whenever any entry is accessed again, it will be moved to the top. So, recently used entries will be on Top and Least used will be on Bottom.
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
    private Map<Integer, DNode> map = Collections.synchronizedMap(new HashMap()); // with sync enhanced
    private DNode head = new DNode(0, 0), tail = new DNode(0, 0);

    public LRUCache(int capacity) {
        this.cacheSize = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            DNode node = map.get(key);
            removeNode(node);
            addToTop(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            removeNode(map.get(key));
        }

        if (map.size() == cacheSize) {
            removeNode(tail.prev);
        }

        addToTop(new DNode(key, value));
    }

    public void removeNode(DNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        synchronized (map){
            map.remove(node.key);
        }
    }

    public void addToTop(DNode node) {
        synchronized (map){
            map.put(node.key, node);
        }
        DNode headNext = head.next;
        head.next = node;
        node.prev = head;
        node.next = headNext;
        headNext.prev = node;
    }
}

class DNode {
    int value;
    int key;
    DNode prev, next;

    public DNode(int key, int value) {
        this.value = value;
        this.key = key;
    }
}
