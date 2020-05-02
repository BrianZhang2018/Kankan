package category.BinaryTreeAndBinarySerach;

import category.model.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * 
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/discuss/39875/Elegant-Java-solution
 * 
 * you can imagine the dfs() function goes from the bottom of the binaryTree to the top, it's in post-order manner.
   At every node, we need to make a decision, if the sum comes from the left path larger than the right path, 
   we pick the left path and plus the current node's value, this recursion goes all the way up to the root node (it return the 
   value to the invoker, the understood is `recursive call` pop out from stack. It's the recursive machnisim, first add into the stack,
   then pop out.
 */
public class BinaryTreeMaximumPathSum{
    public static void main(String[] args){}

    int max = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        if(root == null)
            return 0;
        
        dfs(root);
        return max;
    }
    
    public int dfs(TreeNode root){
        
        if(root == null)
            return 0;
        
        int left = Math.max(dfs(root.left), 0); //avoid the negative value
        int right = Math.max(dfs(root.right), 0);
        
        max = Math.max(max, root.val + left + right);
        
        return root.val + Math.max(left, right);
    }

}