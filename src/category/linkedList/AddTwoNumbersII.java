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

        AddTwoNumbersII test = new AddTwoNumbersII();
        ListNode res = test.addTwoNumbers(a, e);

        while(res != null){
            System.out.println(res.val);
            res = res.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;

        ListNode rel1 = reverseLinkedList(l1);
        ListNode rel2 = reverseLinkedList(l2);

        ListNode dummyNode = new ListNode(0);
        ListNode curr = dummyNode;

        int carry = 0;
        while (rel1 != null || rel2 != null) {
            int sum = carry + (rel1 == null ? 0 : rel1.val) + (rel2 == null ? 0 : rel2.val);
            carry = sum / 10;
            int digit = sum % 10;
            rel1 = (rel1 == null ? null : rel1.next);
            rel2 = (rel2 == null ? null : rel2.next);
            curr.next = new ListNode(digit);
            curr = curr.next;
        }

        if (carry != 0) {
            curr.next = new ListNode(carry);
        }

        return reverseLinkedList(dummyNode.next);
    }

    public ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode tempNext = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tempNext;
        }
        return prev;
    }
}
