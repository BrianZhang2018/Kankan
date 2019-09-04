package category.binaryTreeAndBinarySerach.ConstructBinaryTreeByOrder;

import category.model.TreeNode;
/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/submissions/
 */
public class ConstructBTFromPostorderAndInorder{

    Map<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        
        for(int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        //poststart = postorder.length - 1 -> start from the end of postorder array as it's the root
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
}