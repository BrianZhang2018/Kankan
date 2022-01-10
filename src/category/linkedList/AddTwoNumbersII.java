package category.linkedList;

import category.model.ListNode;
/**
 * https://leetcode.com/problems/add-two-numbers-ii/
 *
 * Solution: reverse the LinkedList + carry the number
 * Stack can be another solution
 */
public class AddTwoNumbersII {

    public static void main(String[] args) {
        ListNode a = new ListNode(7), b = new ListNode(2), c = new ListNode(4), d = new ListNode(3);
        a.next = b; b.next = c; c.next = d;
        ListNode e = new ListNode(5), f = new ListNode(6), g = new ListNode(4);
        e.next =f; f.next=g;

        ListNode res = addTwoNumbers(a, e);
        while(res != null){
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        ListNode r1 = reverse(l1), r2 = reverse(l2);
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;
        while (r1 != null || r2 != null) {
            int sum = 0;
            if(r1 != null) {
                sum += r1.val;
                r1 = r1.next;
            }
            if(r2 != null) {
                sum += r2.val;
                r2 = r2.next;
            }
            sum += carry;
            carry = sum/10;
            curr.next = new ListNode(sum%10);
            curr = curr.next;
        }
        if (carry != 0) {
            curr.next = new ListNode(carry);
        }
        return reverse(dummy.next);
    }

    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
