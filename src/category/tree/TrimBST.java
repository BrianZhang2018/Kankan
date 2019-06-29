package category.tree;

import category.model.TreeNode;
/**
 * https://leetcode.com/problems/trim-a-binary-search-tree/
 */
public class TrimBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(2);

        System.out.println(trimBST(root, 1, 2));
    }

    //recursive solution
    public static TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null) 
            return null;
        if(root.val > R)
            return trimBST(root.left, L, R);
        if(root.val < L)
            return trimBST(root.right, L, R);
        
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        
        return root;
    }

    //iterative solution
    public static TreeNode trimBSTIterativeWay(TreeNode root, int L, int R) {
        if (root == null) {
            return root;
        }
        //Find a valid root which is used to return.
        while (root.val < L || root.val > R) {
            if (root.val < L) {
                root = root.right;
            }
            if (root.val > R) {
                root = root.left;
            }
        }
        TreeNode dummy = root;
        // Remove the invalid nodes from left subtree.
        while (dummy != null) {
            while (dummy.left != null && dummy.left.val < L) {
                dummy.left = dummy.left.right; 
                // If the left child is smaller than L, then we just keep the right subtree of it. 
            }
            dummy = dummy.left;
        }
        dummy = root;
        // Remove the invalid nodes from right subtree
        while (dummy != null) {
            while (dummy.right != null && dummy.right.val > R) {
                dummy.right = dummy.right.left;
                // If the right child is biggrt than R, then we just keep the left subtree of it. 
            }
            dummy = dummy.right;
        }
        return root;
    }    
    
}