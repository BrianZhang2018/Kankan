package category.ImplDataStructure;

/**
 * https://leetcode.com/problems/design-hashmap/discuss/152746/Java-Solution
 *
 * Some of the questions which can be asked to the interviewer before implementing the solution
     * For simplicity, are the keys integers only? --> (important)
     * For collision resolution, can we use chaining?
     * Do we have to worry about load factors?
     * Can we assume inputs are valid or do we have to validate them?
     * Can we assume this fits memory?
 *
 * Array + SingleLinkedList (dummy node -> node1 -> node2 -> null)
 *
 * Apple, includedHealth screen interview
 * Created by brianzhang on 11/3/19.
 */
public class DesignHashMap<T> {
    public static void main(String[] args) {
        DesignHashMap test = new DesignHashMap();
        test.put(20003, 1);
        test.put(10002, 5);
        System.out.println(test.get(10002));
/*        test.put(2,2);
        System.out.println(test.remove(2));*/
    }

    class ListNode<T> {
        T key, val;
        ListNode next;
        ListNode(T key, T val) {
            this.key = key;
            this.val = val;
        }
    }

    final ListNode[] nodes = new ListNode[10001];

    public void put(T key, T value) {
        int index = hashcode(key);
        ListNode prev = findNode(index, key);
        if (prev.next == null) {
            // 1. empty bucket
            // 2. bucket is not empty, but key doesn't exist, so append to the tail node
            prev.next = new ListNode(key, value); // dummy headNode(-1,-1) -> New Node
        } else {
            // same key node already existing, so override the update the existing node value
            prev.next.val = value;
        }
    }

    public T get(T key) {
        int index = hashcode(key);
        if (nodes[index] == null) return null;

        ListNode node = findNode(index, key);
        return node.next == null ? null : (T)node.next.val;
    }

    public T remove(T key) {
        int index = hashcode(key);
        if (nodes[index] == null) return null;

        ListNode prev = findNode(index, key);
        if (prev.next == null) return null;

        T removed = (T)prev.next.val;
        prev.next = prev.next.next;
        return removed;
    }

    // 1. return "previous" node of target if node exists
    // 2. return the tail node if target doesn't exist
    // 3. return the dummy head node
    private ListNode findNode(int index, T key) {
        if (nodes[index] == null) {
            // if bucket is empty, create a dummy head node
            return nodes[index] = new ListNode(-1, -1);
        }
        ListNode prev = nodes[index];
        while (prev.next != null && !prev.next.key.equals(key)) {
            prev = prev.next;
        }
        return prev;
    }

    private int hashcode(T key) {
        return Integer.hashCode((Integer)key) % nodes.length;
    }
}

