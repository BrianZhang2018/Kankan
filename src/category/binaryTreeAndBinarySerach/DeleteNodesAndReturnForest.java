package category.binaryTreeAndBinarySerach;

import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import category.model.TreeNode;

/**
 * For binaryTree problem, consider to use the recursion first.
 * https://leetcode.com/problems/delete-nodes-and-return-forest/discuss/328853/JavaPython-Recursion-Solution
 * https://leetcode.com/problems/delete-nodes-and-return-forest/
 */
public class DeleteNodesAndReturnForest {
    Set<Integer> set = new HashSet<>();
    List<TreeNode> res = new ArrayList<>();
    
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if(root == null)
            return res;
        
        for(int delete : to_delete){
            set.add(delete);
        }
        
        helper(root, true);
        return res;
    }
    
    //递归 is the same logic which will be run again and again,
    //so, the logic is the same for root and child node as the child node
    //also can be considered
    public TreeNode helper(TreeNode node, boolean isRoot){
       if(node == null)
           return null;
        
        boolean isDelete = set.contains(node.val);
        //if the parents is deleted, the current node is root
        if(isRoot && !isDelete) 
            res.add(node);
        
        //isDelete is used to notify the child node whether the parent is deleted
        node.left = helper(node.left, isDelete);
        node.right = helper(node.right, isDelete);
        
        //return to its parent
        return isDelete ? null : node;
    }

}