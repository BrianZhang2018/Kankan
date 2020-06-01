package category.linkedList;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 *
 * Created by brianzhang on 5/27/20.
 */
public class RemoveDupFromSortedListII {

    public ListNode deleteDuplicates(ListNode head) {
        // use two pointers, slow - track the node before the dup nodes, fast - to find the last node of dups
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = head, slow =dummy;

        while(fast!= null){
            // Key approach: 滚轮滚起来，一直滚到最后一个duplicate number
            while(fast.next != null &&  fast.val == fast.next.val){
                fast = fast.next;  //while loop to find the last node of the dups
            }

            if(slow.next != fast){ //duplicates detected
                slow.next = fast.next; //remove the dups
                fast = slow.next;  //reposition the fast pointer
            }else{ //no dup, move down both pointer
                slow = slow.next;
                fast = fast.next;
            }
        }

        return dummy.next;
    }
}
