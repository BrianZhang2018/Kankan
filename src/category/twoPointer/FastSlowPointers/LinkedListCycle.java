package category.twoPointer.FastSlowPointers;

import category.model.ListNode;

/**
 * https://leetcode.com/problems/linked-list-cycle/solution/
 *
 * two pointers at different speed - a slow pointer and a fast pointer. The slow pointer moves one step at a time
 * while the fast pointer moves two steps at a time.
 * Created by brianzhang on 4/5/20.
 */
public class LinkedListCycle {

    public static void main(String[] args) {

    }

    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null)
            return false;

        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null  && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(fast.val == slow.val){
                return true;
            }
        }

        return false;
    }
}
