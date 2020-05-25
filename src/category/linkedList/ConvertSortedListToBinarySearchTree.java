package category.linkedList;

import category.model.TreeNode;
/**
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 *
 * Time Complexity: O(NlogN)
 * Space Complexity: O(logN)
 */
class Solution {

    public TreeNode sortedListToBST(ListNode head) {
        if(head == null)
            return null;

        ListNode mid = findMiddleNode(head);
        TreeNode root = new TreeNode(mid.val);

        // Base case when there is just one element in the linked list
        if (head == mid) {
            return root;
        }

        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);

        return root;
    }

    public ListNode findMiddleNode(ListNode head) {

        // The pointer used to disconnect the left half from the mid node.
        ListNode left = null;
        ListNode slow = head;
        ListNode fast = head;

        // Iterate until fastPr doesn't reach the end of the linked list.
        while (fast != null && fast.next != null) {
            left = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if (left != null) {
            left.next = null;
        }

        return slow;
    }
}