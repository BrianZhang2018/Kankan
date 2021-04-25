package category.tree.BinaryTree.ConstructBinaryTree;

import category.model.TreeNode;
import java.util.*;
/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ConstructBinaryTreeFromPreorderAndInorder {

    public static void main(String[] args) {
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};
        TreeNode root = new ConstructBinaryTreeFromPreorderAndInorder().buildTree(preorder, inorder);
        root.preOrderTraversal(root);
    }

    Map<Integer, Integer> inorderMap = new HashMap<>();
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length != inorder.length) return null;
        
        for(int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return traverse(0, 0, inorder.length-1, preorder);
    }

    // preIndex is used to track the left/right root node in inorder array from preorder array
    public TreeNode traverse(int preIndex, int inStart, int inEnd, int[] preorder){
        
        if(preIndex == preorder.length || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preIndex]);
        int inRoot = inorderMap.get(root.val);

        // 解题思路
       /** (inStart, inRoot-1) ->left subtree, (inRoot+1, inEnd) ->right subtree,
         * (preIndex+1) -> is the root of left subtree, (preIndex + inRoot-inStart+1) -> is the root of right subtree,
         * (inRoot-inStart) is the number of nodes in root's left subtree, so (preIndex + inRoot-inStart + 1) means
         * the first node for right subtree which is the root of right subtree also
        */
        root.left = traverse(preIndex+1, inStart, inRoot-1, preorder);
        root.right = traverse(preIndex + inRoot-inStart + 1, inRoot + 1, inEnd, preorder);

        return root;
    }
}