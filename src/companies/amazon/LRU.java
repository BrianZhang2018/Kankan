package companies.amazon;

import companies.amazon.model.DLinkedListNode;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * https://medium.com/@krishankantsinghal/my-first-blog-on-medium-583159139237
 * <p>
 * So our Implementation of LRU cache will have HashMap and Doubly LinkedList.
 * In Which HashMap will hold the keys and address of the Nodes of Doubly LinkedList . And Doubly LinkedList will hold the values of keys.
 * <p>
 * As We need to keep track of Recently used entries, We will use a clever approach. We will remove element from bottom and add element on start
 * of LinkedList and whenever any entry is accessed , it will be moved to top. so that recently used entries will be on Top and Least used will be on Bottom.
 * <p>
 * hashMap 利用Key，DLinkedListNode
 */
public class LRU {

    DLinkedListNode start, end;
    HashMap<Integer, DLinkedListNode> map = new HashMap<Integer, DLinkedListNode>();
    int LRU_SIZE = 2;


    //先写主体吧，感觉可以更好掌握总体
    public void putNode(int key, String value) {

        if (map.containsKey(key)) {

            DLinkedListNode node = map.get(key);
            node.value = value;
            removeNode(key);
            addToTop(node);

        } else {

            DLinkedListNode newNode = new DLinkedListNode();
            newNode.key = key;
            newNode.value = value;
            newNode.right = null;
            newNode.left = null;

            if (map.size() < LRU_SIZE) {
                addToTop(newNode);
            } else {
                removeNode(end.key);
                map.remove(end.key);
                addToTop(newNode);
            }

            map.put(key, newNode);
        }
    }


    public void addToTop(DLinkedListNode node) {
        node.right = start;
        node.left = null;

        if (start != null) {
            start.left = node;
        }
        start = node;

        if (end == null)
            end = start;

    }


    public void removeNode(int key) {

        DLinkedListNode node = map.get(key);

        if (node.left == null) {
            start = node.right;
        } else {
            node.left.right = node.right;
        }


        if (node.right == null) {
            end = node.left;
        } else {
            node.right.left = node.left;
        }

    }


    public DLinkedListNode popNode(int key) {

        DLinkedListNode node = null;

        if (map.get(key) != null) {
            node = map.get(key);
            removeNode(key);
        }

        return node;
    }


    public static void main(String[] args) {

        LinkedList<Integer> lists = new LinkedList<>();
        lists.add(1);

        //lists.getFirst();
    }


}



