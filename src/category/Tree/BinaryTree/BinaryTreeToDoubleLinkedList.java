package category.Tree.BinaryTree;

import java.util.Stack;

/**
 * Bloomberg OA
 * Created by brianzhang on 5/25/20.
 */
public class BinaryTreeToDoubleLinkedList {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(12);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(25);
        root.left.right = new TreeNode(30);
        root.right.left = new TreeNode(36);

        BinaryTreeToDoubleLinkedList tree = new BinaryTreeToDoubleLinkedList();
        TreeNode head = tree.convert(root);
        tree.printList(head);
    }

    TreeNode head;
    TreeNode dummy;

    public TreeNode convert(TreeNode root){
        if(root == null) return null;
        //inOrderTraversal(root);
        inOrderTraversalIterative(root);
        return head;
    }
    // recursive
    public void inOrderTraversal(TreeNode node){
        if(node == null)
            return;
        inOrderTraversal(node.left);
        if(head == null) {head = node; dummy = head;} else {dummy.right = node; node.left = dummy; dummy = node;}
        inOrderTraversal(node.right);
    }

    // iterative
    public void inOrderTraversalIterative(TreeNode root){
        if(root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(!stack.isEmpty() || curr != null){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }else{
                curr = stack.pop();
                if(head == null) {head = curr; dummy = head;} else {dummy.right = curr; curr.left = dummy; dummy = curr;}
                curr = curr.right;
            }
        }
    }

    void printList(TreeNode node)
    {
        while (node != null)
        {
            System.out.print(node.val + " ");
            node = node.right;
        }
    }
}
