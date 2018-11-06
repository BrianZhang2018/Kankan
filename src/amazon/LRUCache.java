package amazon;

import java.util.HashMap;

/**
 * Created by brianzhang on 7/15/18.
 */
public class LRUCache {

    private int cacheSize;
    private HashMap<Integer, Node> map = new HashMap();
    private Node start, end;

    public LRUCache(int capacity) {
        this.cacheSize = capacity;
    }

    public int get(int key) {

        if (map.get(key) == null) {
            return -1;
        } else {

            Node node = map.get(key);
            removeNode(node);
            addToTop(node);

            return node.value;
        }

    }

    public void put(int key, int value) {

        Node newNode = new Node(key, value);

        if (map.containsKey(key)) {
            Node oldNode = map.get(key);
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


    public void removeNode(Node node) {

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


    public void addToTop(Node node) {
        node.right = start;
        node.left = null;

        if (start != null)
            start.left = node;

        start = node;

        if (end == null) {
            end = node;
        }

    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* capacity */);

        cache.put(2, 1);
        cache.put(2, 2);
        System.out.println(cache.get(2));       // returns 1
       /* cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4*/

    }


}


class Node {
    int value;
    int key;
    Node left, right;

    public Node(int key, int value) {
        this.value = value;
        this.key = key;
    }
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */