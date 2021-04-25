package category.tree.BinaryTree.ConstructBinaryTree;

import category.model.TreeNode;
import java.util.*;
/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/submissions/
 *
 * Same solution with ConstructBinaryTreeFromPreorderAndInorder.java
 */
public class ConstructBinaryTreeFromPostorderAndInorder {

    public static void main(String[] args) {
        ConstructBinaryTreeFromPostorderAndInorder test = new ConstructBinaryTreeFromPostorderAndInorder();
        TreeNode root = test.buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3});
        // print out the tree
        root.preOrderTraversal(root);
    }

    Map<Integer, Integer> inorderMap = new HashMap<>();
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        
        for(int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        // postIndex: start from the "end" of postorder array as it's the root of binary tree
        return helper(postorder.length - 1, 0, inorder.length-1, postorder);
    }

    // postIndex is used to track the left/right root node in inorder array from postorder array
    public TreeNode helper(int postIndex, int inStart, int inEnd, int[] postorder){
        
        if(postIndex == postorder.length || postIndex < 0 || inStart > inEnd) return null;
        
        TreeNode root = new TreeNode(postorder[postIndex]);
        int inRoot = inorderMap.get(root.val);
        
        // (inEnd-inRoot) calculate the number of nodes in right subtree
        root.left = helper(postIndex - (inEnd-inRoot) - 1, inStart, inRoot-1, postorder);
        root.right = helper(postIndex-1, inRoot+1, inEnd, postorder);
        
        return root;
    }
}