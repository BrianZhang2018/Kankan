package companies.amazon;

import java.util.HashMap;

/**
 * Solution: (HashMap + Double LinkedList) = Java LinkedHashMap
 * 
 * https://medium.com/@krishankantsinghal/my-first-blog-on-medium-583159139237
 * <p>
 * So our Implementation of LRU cache will have HashMap and Doubly LinkedList.
 * In Which HashMap will hold the keys and address of the Nodes of Doubly LinkedList . And Doubly LinkedList will hold the values of keys.
 * <p>
 * As We need to keep track of Recently used entries. We will remove element from bottom and add element on the top
 * of LinkedList and whenever any entry is accessed again, it will be moved to the top. So, recently used entries will be on Top and Least used will be on Bottom.
 * <p>
 */
public class LRUCache {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* capacity */);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }

    private int cacheSize;
    private HashMap<Integer, DNode> map = new HashMap();
    private DNode start, end;

    public LRUCache(int capacity) {
        this.cacheSize = capacity;
    }

    public int get(int key) {
        if (map.get(key) == null) {
            return -1;
        } else {
            DNode node = map.get(key);
            removeNode(node);
            addToTop(node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        DNode newNode = new DNode(key, value);
        if (map.containsKey(key)) {
            DNode oldNode = map.get(key);
            removeNode(oldNode);
            map.remove(key);
            addToTop(newNode);
        } else {
            if (map.size() == cacheSize) {
                map.remove(end.key);
                removeNode(end);
                addToTop(newNode);
            } else {
                addToTop(newNode);
            }
        }
        map.put(key, newNode);
    }

    public void removeNode(DNode node) {
        if (node.left != null) {
            node.left.right = node.right;
        } else {
            start = node.right;
        }

        if (node.right != null) {
            node.right.left = node.left;
        } else {
            end = node.left;
        }
    }

    public void addToTop(DNode node) {
        node.right = start;
        node.left = null;  // if you just used (invoke the get() method) a node, you will need add it to the top as it just used. And, you have to set "left = null".

        if (start != null){
            start.left = node;
        }
        start = node;
        if (end == null) {
            end = node;
        }
    }
}

class DNode {
    int value;
    int key;
    DNode left, right;

    public DNode(int key, int value) {
        this.value = value;
        this.key = key;
    }
}
