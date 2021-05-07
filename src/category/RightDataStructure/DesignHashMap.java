package category.RightDataStructure;

/**
 * https://leetcode.com/problems/design-hashmap/discuss/152746/Java-Solution
 *
 * Apple phone screen interview
 *
 * Created by brianzhang on 11/3/19.
 */
public class DesignHashMap<T> {

    public static void main(String[] args) {
        DesignHashMap test = new DesignHashMap();
        test.put(1, 1);
        System.out.println(test.get(1));
        test.put(2,2);
        System.out.println(test.remove(2));
    }

    class ListNode<T> {
        T key, val;
        ListNode next;

        ListNode(T key, T val) {
            this.key = key;
            this.val = val;
        }
    }

    final ListNode[] nodes = new ListNode[10000];

    public void put(T key, T value) {
        int index = hashcode(key);
        if (nodes[index] == null) {
            nodes[index] = new ListNode(-1, -1); // dummy head node
        }

        ListNode prev = locatePrevNodeOfTarget(nodes[index], key);
        if (prev.next == null){
            prev.next = new ListNode(key, value);
        }
        else{ // means the the same key node already existing, so just need override the node value
            prev.next.val = value;
        }
    }

    public T get(T key) {
        int index = hashcode(key);
        if (nodes[index] == null) return null;

        ListNode node = locatePrevNodeOfTarget(nodes[index], key);
        return node.next == null ? null : (T)node.next.val;
    }

    public T remove(T key) {
        int index = hashcode(key);
        if (nodes[index] == null) return null;

        ListNode prev = locatePrevNodeOfTarget(nodes[index], key);
        if (prev.next == null) return null;

        T removed = (T)prev.next.val;
        prev.next = prev.next.next;

        return removed;
    }

    /**
     * @param head, the head of single linkedList
     * @param key, the key of node of target
     * @return the previous node of the key node, otherwise will return the tail node
     */
    private ListNode locatePrevNodeOfTarget(ListNode head, T key) {
        ListNode curr = head, prev = null;
        while (curr != null && curr.key != key) {
            prev = curr;
            curr = curr.next;
        }
        return prev;
    }

    // generate hash key
    private int hashcode(T key) {
        return Integer.hashCode((Integer)key) % nodes.length;
    }
}

