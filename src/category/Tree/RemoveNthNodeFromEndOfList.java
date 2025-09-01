package category.Tree;

import category.model.ListNode;

/**
 * two pointers 快慢指针 solution: 
 * 1. advance fast pointer
 * 2. the gap between fast and slow should be n nodes apart
 *
 * Created by brianzhang on 9/16/18.
 */
public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode result = removeNthFromEnd(head, 2);
        System.out.println(result.val);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        for(int i = 0; i < n; i++)  fast = fast.next;
        if(fast == null) return head.next;
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
