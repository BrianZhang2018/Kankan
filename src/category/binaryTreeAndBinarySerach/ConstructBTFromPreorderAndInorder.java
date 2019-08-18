package category.binaryTreeAndBinarySerach;

import category.model.TreeNode;

public class ConstructBTFromPreorderAndInorder{

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length != inorder.length)
            return null;
        
        return traveral(0, 0, inorder.length-1, preorder, inorder); 
    }
    
    //inStart- inOrderStart
    public TreeNode traveral(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder){
        
        if(preStart > preorder.length -1 || inStart > inEnd)
            return null;
        
        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = 0;
        //find the preorder node from inorder array of nodes which is the root for left and right subtree.
        for(int i= 0; i < inorder.length; i++){
            if(root.val == inorder[i]){
                inRoot = i;
                break;
            }
        }
        
        //recursively build the tree.  
        //(instart, inRoot-1) -> left subtree, (inRoot+1, inEnd) -> right subtree
        //(preStart+1) -> root of left subtree, (preStart + inRoot-inStart+1) -> root of right subtree
        root.left = traveral(preStart + 1, inStart, inRoot-1, preorder, inorder);
        root.right = traveral(preStart + inRoot - inStart + 1, inRoot + 1, inEnd, preorder, inorder);
        
        return root;
    }
}