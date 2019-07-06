package category.linkedList;

import java.util.BitSet;
import category.model.ListNode;
/**
 * https://leetcode.com/problems/add-two-numbers-ii/
 * Solution: reverse the linkedlist + carry the number
 * stack can be another solution
 */
public class AddTwoNumbersII {

    public static void main(String[] args) {
        BitSet bitSet = new BitSet(10);
        bitSet.set(0);

        int[] test = new int[12];
        System.out.println(test[0]);

        System.out.println(bitSet.get(0));

        //System.out.println(bitSet.length());
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;

        ListNode rel1 = reverse(l1);
        ListNode rel2 = reverse(l2);

        ListNode res = new ListNode(0);
        ListNode currP = res;

        int carry = 0;
        while (rel1 != null || rel2 != null) {
            int sum = carry + (rel1 == null ? 0 : rel1.val) + (rel2 == null ? 0 : rel2.val);
            carry = sum / 10;
            int digit = sum % 10;
            rel1 = (rel1 == null ? null : rel1.next);
            rel2 = (rel2 == null ? null : rel2.next);
            ListNode curr = new ListNode(digit);
            currP.next = curr;
            currP = curr;
        }

        if (carry != 0) {
            ListNode curr = new ListNode(carry);
            currP.next = curr;
        }

        return reverse(res.next);

    }

    public ListNode reverse(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        ListNode next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
        return head;
    }

}
