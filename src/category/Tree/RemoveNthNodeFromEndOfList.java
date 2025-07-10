package category.Tree;

import category.model.ListNode;


/**
 * two pointers solution: 
 * 1. advance first pointer
 * 2. the gap between first and second should be n nodes apart
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
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode first = dummy, second = dummy;
        // advance first pointer: the gap between first and second should be n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        // remove the nth node from the end
        second.next = second.next.next;
        return head;
    }
   
}
