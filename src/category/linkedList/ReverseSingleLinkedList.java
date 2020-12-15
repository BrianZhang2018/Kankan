package category.linkedList;

import category.model.ListNode;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 *
 * Created by brianzhang on 7/22/18.
 */
public class ReverseSingleLinkedList {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2; n2.next=n3; n3.next=n4;
        ListNode head = reverseList(n1);
        while(head != null) {System.out.println(head.val);head = head.next;}
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while(head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
