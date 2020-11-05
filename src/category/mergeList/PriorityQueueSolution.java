package category.mergeList;

import java.util.PriorityQueue;

/**
 * Complexity: 由于priority queue的大小为始终为k，而每次插入的复杂度是log k，一共插入过nk个节点。时间复杂度为O(nk logk)，空间复杂度为O(k)
 *
 * Extend knowledge:
 * Heaps:
 * A heap is a specific binaryTree based data structure in which all the nodes of binaryTree are in a specific order.
 * https://www.hackerearth.com/practice/notes/heaps-and-priority-queues/
 * this is binaryTree structure, so the complexity is O(log n)
*/

public class PriorityQueueSolution {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null || lists.length==0) return null;

        PriorityQueue<ListNode> queue= new PriorityQueue<>((a,b)-> a.val-b.val);

        ListNode dummy = new ListNode(0);
        ListNode tail=dummy;

        for (ListNode node:lists)
            if (node!=null)
                queue.add(node);

        while (!queue.isEmpty()){
            tail.next=queue.poll();
            tail=tail.next;

            if (tail.next!=null)
                queue.add(tail.next);
        }
        return dummy.next;
    }
}

class ListNode {
    public int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
