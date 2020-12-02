package category.linkedList;

import java.util.*;
/**
 * https://leetcode.com/problems/all-oone-data-structure/
 * http://cqbbshuashua.blogspot.com/2018/04/432-all-oone-data-structure.html
 *
 * Created by brianzhang on 11/23/20.
 */
public class AllOoneDataStructure {
    public static void main(String[] args) {
        AllOoneDataStructure test = new AllOoneDataStructure();
        test.inc("test1");
        test.inc("test1");
        test.inc("test2");
        System.out.println(test.getMaxKey());
        System.out.println(test.getMinKey());
        test.inc("test2");
        test.inc("test2");
        System.out.println(test.getMaxKey());
        System.out.println(test.getMinKey());
    }
    class DoublyLinkedNode {
        int val;
        String key;
        DoublyLinkedNode prev, next;
        public DoublyLinkedNode(int val, String key) {
            this.val = val;
            this.key = key;
        }
    }

    DoublyLinkedNode head, tail;
    Map<String, DoublyLinkedNode> map;
    public AllOoneDataStructure() {
        head = new DoublyLinkedNode(0, "");
        tail = new DoublyLinkedNode(0, "");
        map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }

    private void remove(DoublyLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAHead(DoublyLinkedNode front, DoublyLinkedNode back) {
        front.next = back;
        back.prev.next = front;
        front.prev = back.prev;
        back.prev = front;
    }

    private void moveForward(DoublyLinkedNode node) {
        while (node.prev != head && node.val > node.prev.val) {
            remove(node);
            insertAHead(node, node.prev);
        }
    }

    private void moveBackward(DoublyLinkedNode node) {
        while (node.next != tail && node.val < node.next.val) {
            DoublyLinkedNode next = node.next; // be careful here
            remove(node.next);
            insertAHead(next, node);
        }
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (!map.containsKey(key)) {
            DoublyLinkedNode node = new DoublyLinkedNode(1, key);
            map.put(key, node);
            insertAHead(node, tail);
        } else {
            DoublyLinkedNode node = map.get(key);
            node.val++;
            moveForward(node);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (map.containsKey(key)) {
            DoublyLinkedNode node = map.get(key);
            if (node.val == 1)
                remove(node);
            else{
                node.val--;
                moveBackward(node);
            }
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (map.isEmpty()) return "";
        else
            return head.next.key;
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (map.isEmpty()) return "";
        else
            return tail.prev.key;
    }
}
