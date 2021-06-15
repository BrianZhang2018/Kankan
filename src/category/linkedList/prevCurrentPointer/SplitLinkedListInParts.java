package category.linkedList.prevCurrentPointer;

import category.model.ListNode;

/**
 * https://leetcode.com/problems/split-linked-list-in-parts/
 *
 * Created by brianzhang on 6/6/21.
 */
public class SplitLinkedListInParts {

    public static void main(String[] args) {}

    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        if(root == null) return res;

        int length = 0;
        // get length of linkedList
        for(ListNode node=root; node != null; node = node.next)
            length++;

        int standard = length/k, addition = length % k;
        ListNode prev = null;
        for(int i=0; root!=null && i<k; i++, addition--){
            res[i] = root;

            for(int j=0; j<standard + (addition >0 ? 1 : 0); j++){
                prev = root;
                root = root.next;
            }

            prev.next = null;
        }

        return res;
    }
}
