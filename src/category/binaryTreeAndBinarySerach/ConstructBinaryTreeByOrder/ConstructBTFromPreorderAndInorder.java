package category.binaryTreeAndBinarySerach.ConstructBinaryTreeByOrder;

import category.model.TreeNode;
/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ConstructBTFromPreorderAndInorder{

    Map<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length != inorder.length)
            return null;
        
        for(int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return traveral(0, 0, inorder.length-1, preorder, inorder); 
    }
    
    public TreeNode traveral(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder){
        
        if(preStart > preorder.length -1 || inStart > inEnd)
            return null;
        
        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = inorderMap.get(root.val);
        
        //recursively build the tree.  
        //(instart, inRoot-1) -> left subtree, (inRoot+1, inEnd) -> right subtree
        //(preStart+1) -> root of left subtree, (preStart + inRoot-inStart+1) -> root of right subtree
        root.left = traveral(preStart+1, inStart, inRoot-1, preorder, inorder);
        root.right = traveral(preStart + inRoot-inStart + 1, inRoot + 1, inEnd, preorder, inorder);
        //(inRoot-inStart) is the number of nodes in root's left subtree, so (preStart + inRoot-inStart + 1) means 
        //the first node for right subtree which is actually the root
        
        return root;
    }
}