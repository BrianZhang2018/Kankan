package category.linkedList;

import category.model.ListNode;

import java.util.*;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 */
public class MergeTwoSortedList {
    public static void main(String[] args) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(1);
        dq.add(2);
        dq.add(3);
        dq.add(4);

        System.out.println(dq.contains(3));
        System.out.println(dq.peekFirst());

        ArrayList<Integer>[][] origin = new ArrayList[][]{{new ArrayList(1)}};
        ArrayList<Integer>[][] res = new ArrayList[origin.length][];

        for(int i = 0; i < origin.length; i++)
            res[i] = origin[i].clone();


        System.out.println(res[0][0]);
        origin[0][0].add(0,9);
        System.out.println(res[0][0]);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null)
            return l1 != null? l1 : (l2 !=null ? l2 : null);

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while(l1 != null || l2 != null){
            if(l1 == null || l2 == null){
                curr.next = (l2 ==null? l1 : l2);
                break;
            }
            if(l1.val<=l2.val){
                curr.next = l1;
                l1 = l1.next;
            }else{
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        return dummy.next;
    }
}