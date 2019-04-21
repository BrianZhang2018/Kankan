package category.linkedList;

import category.model.ListNode;

/**
 * Created by brianzhang on 7/22/18.
 */
public class ReverseSingleLinkedList {

    public ListNode reverseList(ListNode head) {
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
