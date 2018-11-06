package category.mergeList;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by brianzhang on 7/8/18.
 */
//Complexity: 由于priority queue的大小为始终为k，而每次插入的复杂度是log k，一共插入过nk个节点。时间复杂度为O(nk logk)，空间复杂度为O(k)。
/*
  Extend knowledge:
  Heaps:
  A heap is a specific tree based data structure in which all the nodes of tree are in a specific order.
    https://www.hackerearth.com/practice/notes/heaps-and-priority-queues/
  this is tree structure, so the complexity is O(log n)
 */
public class PriorityQueueSolution {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        PriorityQueue<ListNode> queue = new PriorityQueue(new Comparator<ListNode>() {
            public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val;
            }
        });

        //单链表 需要这个，如果是Tree 就不要了，因为有root
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (ListNode list : lists) {
            if (list != null)
                queue.offer(list);
        }

        //sort the lists
        while (!queue.isEmpty()) {
            ListNode n = queue.poll();
            curr.next = n;
            curr = n;

            if (n.next != null)
                queue.offer(n.next);
        }
        return head.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
