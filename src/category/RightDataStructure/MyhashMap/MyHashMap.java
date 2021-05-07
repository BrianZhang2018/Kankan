package category.RightDataStructure.MyhashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/design-hashmap/discuss/225312/hashmaparraylinkedlistcollision
 *
 * Created by brianzhang on 1/9/21.
 */
public class MyHashMap {

    public static void main(String[] args) {
    }
    // single linked list
    class Node {
        final int key;
        int value;
        Node next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    final int SIZE = 10001;
    Node[] array;
    public MyHashMap() {
        array = new Node[SIZE];
    }

    public void put(int key, int value) {
        int index = hash(key);
        Node head = array[index];
        Node node = head;
        while (node != null) {
            if (node.key == key) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        Node newNode = new Node(key, value);
        newNode.next = head;
        array[index] = newNode;
    }

    public int get(int key) {
        int index = hash(key);
        Node node = array[index];
        while (node != null) {
            if (node.key == key) return node.value;

            node = node.next;
        }
        return -1;
    }

    public void remove(int key) {
        int index = hash(key);
        Node node = array[index];
        while (node != null) {
            if (node.key == key) {
                node.value = -1;
                return;
            }
            node = node.next;
        }
    }

    private int hash(int key) {
        return Integer.hashCode(key) % SIZE;
    }
}
