package category.linkedList;

/**
 * https://leetcode.com/problems/odd-even-linked-list/
 *
 * Created by brianzhang on 3/2/20.
 */
public class OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {

        if(head == null)
            return head;

        ListNode odd = head, even = head.next, evenHead = even;

        while(even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }

    public ListNode oddEvenList1(ListNode head) {

        ListNode oddDummy = new ListNode(-1);
        ListNode evenDummy = new ListNode(-1);
        ListNode oddLastNode = oddDummy;
        ListNode evenLastNode = evenDummy;
        boolean isOdd = true;

        while (head != null) {

            if (isOdd) {
                // append to odd list
                oddLastNode.next = head;
                oddLastNode = oddLastNode.next;

            } else {
                // append to even list
                evenLastNode.next = head;
                evenLastNode = evenLastNode.next;
            }

            isOdd = !isOdd;     // toggle
            head = head.next;
        }

        // connect end of odd list to start of even list
        oddLastNode.next = evenDummy.next;
        evenLastNode.next = null;

        // return start of odd list
        return oddDummy.next;
    }

}
