package category.DivideAndConquerRecursive;

import category.model.ListNode;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 *
 * time complexity: O(nlogn)
 */
public class MergeKSortedList {

    public static void main(String[] args) {
       // new MergeKSortedList().mergeKLists()
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        return mergeSort(lists, 0, lists.length - 1);
    }
    
    public ListNode mergeSort(ListNode[] lists, int low, int high) {
        if(low == high) return lists[low];
        
        int mid = low + (high - low) / 2;
        ListNode left = mergeSort(lists, low, mid);
        ListNode right = mergeSort(lists, mid + 1, high);
        
        return merge(left, right);
    }
    
    public ListNode merge(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        
        if(l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
}