package category.PriorityQueue;


import category.model.ListNode;

import java.util.PriorityQueue;

public class MergeKSortedList {

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq =
                new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode l : lists) {
            if (l != null)
                pq.add(l);
        }

        if (pq.size() == 0) return null;

        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        while (!pq.isEmpty()) {
            curr.next = pq.poll();
            curr = curr.next;

            if (curr.next != null) pq.add(curr.next);
        }

        return dummy.next;
    }
}
