package category.linkedList.fastslowpointer;

import category.model.*;

/**
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 *
 * Time Complexity: O(NlogN)
 * Space Complexity: O(logN)
 */
public class ConvertSortedLinkedListToBinarySearchTree {

    public static void main(String[] args) {}

    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;

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
        ListNode left = null; // left pointer used to disconnect the left half from the mid node.
        ListNode slow = head, fast = head;

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