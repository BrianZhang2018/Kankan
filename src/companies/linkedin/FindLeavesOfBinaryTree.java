package companies.linkedin;

import java.util.*;

/**
 * use the tree height as the index to collect the nodes
 * and the leave node's height = 0
 * 
 * https://www.lintcode.com/problem/find-leaves-of-binary-tree/note/191875
 */
public class FindLeavesOfBinaryTree{

    public List<List<Integer>> findLeaves(TreeNode root) {
        // write your code here
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        dfs(root, res);
        return res;
    }
    
    public int dfs(TreeNode root,  List<List<Integer>> res){
        if(root == null)
            return -1;
            
        int left  = dfs(root.left, res);
        int right = dfs(root.right, res);
        
        int height = Math.max(left, right) + 1;
        // the first time this code is reached is when curr==0,
        //since the tree is bottom-up processed
        if(res.size() == height){
            res.add(new ArrayList<Integer>());
        }
        res.get(height).add(root.val);
        
        return height;
    }
}