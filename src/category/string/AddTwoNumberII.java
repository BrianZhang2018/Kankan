package category.string;

/**
 *
 */
public class AddTwoNumberII {
    public static void main(String[] args) {
        System.out.println(Integer.valueOf('a'));
        addTwoNumbers();
    }
    public static ListNode addTwoNumbers() {
       
        char[] c = String.valueOf("7804").toCharArray();
        System.out.println(c[1]);

        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int i = 1; i < c.length; i++) {
            ListNode node = new ListNode(c[i]);
            curr.next = node;
            curr = node;
        }

        return head.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}