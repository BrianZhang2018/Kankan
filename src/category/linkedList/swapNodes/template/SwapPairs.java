package category.linkedList.swapNodes.template;

import category.model.ListNode;

/**
 * Created by brianzhang on 1/13/21.
 */
public class SwapPairs {

    // Solution -1: template solution for swapping nodes in LinkedList
    int k = 2;
    public ListNode swapPairs(ListNode head) {
        ListNode curr = head;
        int count = 0;
        while(curr !=null && count !=k){
            curr = curr.next;
            count++;
        }

        if(count == k){
            ListNode prev = swapPairs(curr);

            while(count-- >0){
                ListNode next = head.next;
                head.next = prev;
                // shift the pointer after reverse the link
                prev = head;
                head = next;
            }

            head = prev;

        }

        return head;

    }

    // solution - 2
    public ListNode swapPairs1(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode curr = head;
        while(curr.next != null){
            int temp = curr.val;
            curr.val = curr.next.val;
            curr.next.val = temp;

            if(curr.next.next != null)
                curr = curr.next.next;
            else
                break;
        }


        return head;
    }
}
