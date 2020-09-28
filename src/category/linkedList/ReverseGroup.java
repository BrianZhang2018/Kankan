package category.linkedList;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * 1. Singly linkedList
 * 2. reverse group
 *
 * Created by brianzhang on 9/27/20.
 */
public class ReverseGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        for(int i=1; i<k && temp!=null; temp=temp.next, i++);
        if(temp == null) return head;

        ListNode prev = reverseKGroup(temp.next, k);
        temp = head;

        while(k-- > 0){
            ListNode post = temp.next;
            temp.next = prev;
            prev = temp;
            temp = post;
        }

        return prev;

    }
}
