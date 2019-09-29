package category.tree.bondary;

import java.util.*;
import java.util.Queue;
import java.util.ArrayList;
import companies.amazon.model.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 */
public class BinaryTreeRightSideView{

//solution-1
    //dfs - recursive
    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null)
            return res;
        
        int level = 0;
        dfs(root, level, res);
        return res;
    }
    
    
    public void dfs(TreeNode root, int level, List<Integer> res){
        System.out.println(level);
        
        if(root == null)
            return;
        
        if(res.size() == level)
            res.add(root.val);
        
        //not ++level as it will impact the level value in second dfs
        dfs(root.right, level + 1, res);
        dfs(root.left, level +1 , res);
    }

//solution-2
    //level order traverse - iterative 
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null)
            return Collections.emptyList();
        
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while(!queue.isEmpty()){
            
            int size = queue.size();
            
            for(int i=0; i<size; i++){

                TreeNode node = queue.poll();
                //key point, record the right most node's value
                if(i == size -1){
                    res.add(node.val);
                }
                
                if(node.left != null){
                    queue.add(node.left);
                }
                
                if(node.right != null){
                    queue.add(node.right);
                }
                
            }
        }
        return res;
    }

}