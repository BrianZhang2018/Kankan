package category.Tree.BinaryTree.convertToLinkedList;

import category.model.TreeNode;
import java.util.Stack;

/**
 * https://www.lintcode.com/problem/convert-binary-search-tree-to-sorted-doubly-linked-list/description
 * 
 * key point:
 * 1. sorted -> inorder traversal 
 * 2. reconnect head node with last node
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList{

    public TreeNode treeToDoublyList(TreeNode root) {
        if(root == null) return root;
            
        Stack<TreeNode> stack = new Stack<>();
        TreeNode head = new TreeNode(-1);  //dummy head node
        TreeNode prev = head;
        TreeNode curr = root;
        
        //inorder iterative traversal
        while (!stack.isEmpty() || curr != null) {
                    
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            
            curr  = stack.pop();
            
            prev.right = curr;
            curr.left = prev;
            prev = curr;
            
            curr = curr.right;
        }

        // 最后一个和第一个相连
        prev.right = head.right;
        head.right.left = prev;

        return head.right; // 注意这个题目需要返回最小节点，而不是head
    }
}