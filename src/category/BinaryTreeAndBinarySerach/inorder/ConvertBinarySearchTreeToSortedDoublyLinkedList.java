package category.BinaryTreeAndBinarySerach.inorder;

import category.model.TreeNode;
import java.util.Stack;

/**
 * check the problem description chart from below link
 * https://www.lintcode.com/problem/convert-binary-search-tree-to-sorted-doubly-linked-list/description
 * 
 * key point:
 * 1. sorted -> inorder traversal 
 * 2. reconnect head node with last node
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList{

    public TreeNode treeToDoublyList(TreeNode root) {
        
        if(root == null)
            return root;
            
        Stack<TreeNode> stack = new Stack<>();
        //dummy head node
        TreeNode head = new TreeNode(0);
        TreeNode prevNode = head;
        TreeNode currNode = root;
        
        //inorder iterative traversal - classic
        while (!stack.isEmpty() || currNode != null) {
                    
            while (currNode != null) {
                stack.push(currNode);
                currNode = currNode.left;
            }
            
            currNode  = stack.pop();
            
            prevNode.right = currNode;
            currNode.left = prevNode;
            prevNode = currNode;
            
            currNode = currNode.right;
        }
        // 最后一个和第一个相连
        prevNode.right = head.right;
        head.right.left = prevNode;
        // 注意这个题目需要返回最小节点，而不是head
        return head.right;
    }
}