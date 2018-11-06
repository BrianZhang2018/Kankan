package category.model;

/**
 * Created by brianzhang on 7/22/18.
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static void main(String[] args){

        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);

        a.next = b;
        b.next = c;

        ListNode prev = null;
        ListNode current = a;
        ListNode next = null;

        next = current.next;
        prev = current;
        //java is pass by value, prevNode now points to what currNode pointing now in heap
        // which is actually the value of currNode
        current = next;

        System.out.println(prev.val);
        System.out.println(current.val);
    }
}
