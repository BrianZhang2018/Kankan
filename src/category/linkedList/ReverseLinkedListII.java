package category.linkedList;

/**
 * https://leetcode.com/problems/reverse-linked-list-ii/
 */
public class ReverseLinkedListII{
    public ListNode reverseBetween(ListNode head, int m, int n) {
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode pre1 = dummy;
        //first part
        for(int i=0; i<m-1; i++){
            pre1 = pre1.next;
        }
        
        ListNode curr1 = pre1.next;
        
        //reverse
        ListNode curr2 = curr1;
        ListNode pre2 = pre1;
        ListNode q2 = null;
        for(int i=m; i<=n; i++){
            
            q2 = curr2.next;
            curr2.next = pre2;
            pre2 =curr2;
            curr2 = q2;
        }
        
        //reconnect
        pre1.next = pre2;
        curr1.next = curr2;
        
        return dummy.next;
    }

}