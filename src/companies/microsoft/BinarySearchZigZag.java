package companies.microsoft;

import category.model.TreeNode;

import java.util.ArrayList;
import java.util.*;

public class BinarySearchZigZag {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        
        if(root == null) {
            return res;
        }
        
        queue.add(root);
        
        while(!queue.isEmpty()) {
            int nodes = queue.size();
            List<Integer> currLevel = new ArrayList();
            
            for(int i = 0; i<nodes; i++){
                TreeNode node = queue.pop();
                //res.size() = last level, so if res.size() = 1 (odd level)
                //means current level = 2 (even level), we should store the value as reverse order
                if(res.size() % 2 != 0){
                    currLevel.add(0, node.val);
                }else{
                    currLevel.add(node.val);
                }
                
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            }
            
            res.add(currLevel);
        }
        return res;
     }
}