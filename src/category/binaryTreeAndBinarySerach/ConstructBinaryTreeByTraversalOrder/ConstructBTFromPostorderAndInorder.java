package category.binaryTreeAndBinarySerach.ConstructBinaryTreeByTraversalOrder;

import category.model.TreeNode;
import java.util.*;
/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/submissions/
 */
public class ConstructBTFromPostorderAndInorder{

    public static void main(String[] args) {
        ConstructBTFromPostorderAndInorder test = new ConstructBTFromPostorderAndInorder();
        TreeNode root = test.buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3});

        preOrderTraversal(root);
    }

    Map<Integer, Integer> inorderMap = new HashMap<>();
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        
        for(int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        //poststart = postorder.length - 1 -> start from the end of postorder array as it's the root of binary tree
        return helper(postorder.length - 1, 0, inorder.length -1, inorder, postorder);
    }
    
    public TreeNode helper(int postStart, int inStart, int inEnd, int[] inorder, int[] postorder){
        
        if(postStart > postorder.length || postStart < 0 || inStart > inEnd){
            return null;
        }
        
        TreeNode root = new TreeNode(postorder[postStart]);
        int inRoot = inorderMap.get(root.val);
        
        //(inEnd-inRoot) calculate the number of nodes in left subtree
        root.left = helper(postStart - (inEnd-inRoot) - 1, inStart, inRoot-1, inorder, postorder);
        root.right = helper(postStart-1, inRoot+1, inEnd, inorder, postorder);
        
        return root;
    }

    public static void preOrderTraversal(TreeNode node){
        if(node == null){
            System.out.println("leaf");
            return;
        }
        System.out.println(node.val);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }
}