package category.tree.morrisTraversal;

import category.model.TreeNode;
import category.tree.BinaryTree.BinaryTreeUtil;

/**
 * https://leetcode.com/problems/recover-binary-search-tree
 *
 * Refer MorrisInOrderTraversal.java to understand the algorithm
 *
 * Created by brianzhang on 9/26/21.
 */
public class RecoverBinarySearchTreeO1Space {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        System.out.println("Before"); BinaryTreeUtil.inOrderPrint(root);
        recoverTree(root);
        System.out.println("After");BinaryTreeUtil.inOrderPrint(root);
    }

    public static void recoverTree(TreeNode root) {
        TreeNode prev = null;
        TreeNode first = null, second = null; // wrong order nodes

        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                if(prev != null && prev.val > curr.val){
                    if(first==null) first = prev;  // first used to recode the large one, second record small one
                    second = curr;
                }
                prev = curr;
                curr = curr.right;
            } else {
                TreeNode predecessor = curr.left;
                // predecessor is just used to traverse current subtree, it's not inorder.
                // "prev" is used to track the inorder sequence. so, line 50 use "prev" compare with "curr" val.
                while (predecessor.right != curr && predecessor.right != null) {
                    // tow situation exit this while loop:
                    // 1. predecessor.right == null means hasn't construct the threading
                    // 2. predecessor.right != curr means threading already exist, then go to line 52
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) { // construct the threading
                    predecessor.right = curr;
                    curr = curr.left;
                } else { // threading already exist
                    predecessor.right = null;
                    if(prev != null && prev.val > curr.val){  // curr is the successor of prev
                        if(first==null) first = prev;
                        second = curr;
                    }
                    prev = curr;
                    curr = curr.right;
                }
            }
        }

        if(first!= null && second != null){
            int t = first.val;
            first.val = second.val;
            second.val = t;
        }
    }
}