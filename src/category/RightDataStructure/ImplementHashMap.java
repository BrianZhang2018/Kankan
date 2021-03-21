package category.RightDataStructure;

/**
 * https://leetcode.com/problems/design-hashmap/discuss/152746/Java-Solution
 *
 * Apple phone screen interview
 *
 * Created by brianzhang on 11/3/19.
 */
public class ImplementHashMap {

    public static void main(String[] args) {
        ImplementHashMap test = new ImplementHashMap();
        test.put(1, 1);
        System.out.println(test.get(1));

        test.put(2,2);
        test.remove(2);

        System.out.println();
    }

    static class ListNode {
        int key, val;
        ListNode next;

        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    final ListNode[] nodes = new ListNode[10000];

    public void put(int key, int value) {
        int i = hashcode(key);
        if (nodes[i] == null) {
            nodes[i] = new ListNode(-1, -1); // dummy head node
        }

        ListNode prev = findParent(nodes[i], key);
        if (prev.next == null){
            prev.next = new ListNode(key, value); // append node
        }
        else{  // Update the value if the key same, map.put will override the value if the key is same
            prev.next.val = value;
        }
    }

    public int get(int key) {
        int i = hashcode(key);
        if (nodes[i] == null) return -1;

        ListNode node = findParent(nodes[i], key);
        return node.next == null ? -1 : node.next.val;
    }

    public void remove(int key) {
        int i = hashcode(key);
        if (nodes[i] == null) return;

        ListNode prev = findParent(nodes[i], key);
        if (prev.next == null) return;
        prev.next = prev.next.next;
    }

    // generate hash key
    private int hashcode(int key) {
        return Integer.hashCode(key) % nodes.length;
    }

    // return the previous node of the target node if existing, otherwise will return the tail node
    private ListNode findParent(ListNode bucket, int key) {
        ListNode node = bucket, prev = null;
        while (node != null && node.key != key) {
            prev = node;
            node = node.next;
        }
        return prev;
    }
}

