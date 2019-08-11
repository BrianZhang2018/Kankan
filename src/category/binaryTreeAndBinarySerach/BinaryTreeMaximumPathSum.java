package category.binaryTreeAndBinarySerach;

import category.model.TreeNode;
import java.util.*;
/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * 
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/discuss/39875/Elegant-Java-solution
 * 
 * you can imagine the helper( ) function goes from the bottom of the tree to the top, it's in post-order manner.
   At every node, we need to make a decision, if the sum comes from the left path larger than the right path, 
   we pick the left path and plus the current node's value, this recursion goes all the way up to the root node.
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