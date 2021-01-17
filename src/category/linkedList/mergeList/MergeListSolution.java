package category.linkedList.mergeList;

/**
 * Java PriorityQueue (Java Doc)
 * O(log n) time for the enqueing and dequeing methods (offer, poll, remove() and add)
 * O(n) for the remove(Object) and contains(Object) methods
 * O(1) for the retrieval methods (peek, element, and size)
 */
//complexity: O(nk (log n))

public class MergeListSolution {

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) return null;
        ListNode prevMergedList = null;

        for (int i = 0; i < lists.length; i++) {
            // key point
            prevMergedList = mergeTwoList(prevMergedList, lists[i]);
        }
        return prevMergedList;
    }

    // Common solution for merge tow lists, like merge two sorted list
    public ListNode mergeTwoList(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head = new ListNode(0);
        ListNode curr = head;

        while (l1 != null || l2 != null) {

            if (l1 == null || l2 == null) {
                curr.next = l1 == null ? l2 : l1;
                break;
            }

            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;

        }
        return head.next;
    }
}
