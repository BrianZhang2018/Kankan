package category.linkedList.swapNodes.template;

import category.model.ListNode;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * https://leetcode.com/problems/reverse-nodes-in-k-group/discuss/11423/Short-but-recursive-Java-code-with-comments
 *
 * Discovery phone screening
 * Created by brianzhang on 9/27/20.
 */
public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2; n2.next=n3; n3.next=n4; n4.next= n5;
        ListNode head = reverseKGroup(n1, 2);
        while(head != null) {System.out.println(head.val); head = head.next;}
    }

    // recursive solution - template solution
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the (k+1)th node which is the head of direct group (need to be reversed)
            curr = curr.next;
            count++;
        }
        // 解题思路: recursively reversing a linkedList, so the reversal direction is from tail to head
        if (count == k) { // if k+1 node is available
            ListNode prev = reverseKGroup(curr, k); // reverse list with (k+1)th node as head
            // prev - head-pointer to reversed part
            // head - head-pointer to direct part (need to be reversed)
            // 解题思路: reverse current k-group (standard reverse linkedList way, but "prev" node is head of last reversed part)
            while (count-- > 0) {
                ListNode next = head.next;
                head.next = prev;
                // shift the pointer after reverse the link
                prev = head;
                head = next;
            }
            head = prev; // perv point to the tail of direct part which will be the head for next group reversal
        }
        return head;
    }

    // non-recursive
    public ListNode reverseKGroup1(ListNode head, int k) {
        int n = 0;
        for (ListNode i = head; i != null; n++, i = i.next);

        ListNode dmy = new ListNode(0);
        dmy.next = head;
        for(ListNode prev = dmy, tail = head; n >= k; n -= k) {
            for (int i = 1; i < k; i++) {
                ListNode next = tail.next.next;
                tail.next.next = prev.next;
                prev.next = tail.next;
                tail.next = next;
            }

            prev = tail;
            tail = tail.next;
        }
        return dmy.next;
    }
}
