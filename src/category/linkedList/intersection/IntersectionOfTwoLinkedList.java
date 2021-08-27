package category.linkedList.intersection;

import category.linkedList.ListNode;
import java.util.*;

/**
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 *
 * https://leetcode.com/problems/intersection-of-two-linked-lists/discuss/49785/Java-solution-without-knowing-the-difference-in-len!
 *
 * Created by brianzhang on 8/18/21.
 */
public class IntersectionOfTwoLinkedList {

    // solution-1
    /*Traverse list B and store the "address/reference" of each node in a hash table.

    The one thing we need to be careful of is that we're comparing objects of type Node. We don't want to compare the values within the nodes;
    doing this would cause our code to break when two different nodes have the same value.
    */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> nodesInB = new HashSet<>();

        while (headB != null) {
            nodesInB.add(headB);
            headB = headB.next;
        }

        while (headA != null) {
            if (nodesInB.contains(headA)) {
                return headA;
            }
            headA = headA.next;
        }

        return null;
    }

    // solution-2
    // switch the head when reach the tail which will help to counteract the difference between two linkedList which will lead to find the interaction node
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)return null;
        ListNode curA = headA;
        ListNode curB = headB;

        while(curA != curB){
            curA = curA == null? headB : curA.next;
            curB = curB == null? headA : curB.next;
        }

        return curA;
    }
}
