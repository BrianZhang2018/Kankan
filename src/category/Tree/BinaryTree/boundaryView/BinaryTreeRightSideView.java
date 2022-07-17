package category.Tree.BinaryTree.boundaryView;

import java.util.*;
import java.util.Queue;
import java.util.ArrayList;
import companies.amazon.model.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 */
public class BinaryTreeRightSideView{
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(40);
        root.left.right = new TreeNode(60);

        BinaryTreeRightSideView btr = new BinaryTreeRightSideView();
        for(Integer i : btr.rightSideView(root)) System.out.println(i);

        for(Integer i : btr.rightSideViewDFS(root)) System.out.println(i);
    }

    //solution-1: dfs
    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        
        int level = 0;
        dfs(root, level, res);
        return res;
    }
    
    public void dfs(TreeNode root, int level, List<Integer> res){
        System.out.println("Tree level is: " + level);
        
        if(root == null) return;
        
        if(res.size()==level) res.add(root.val);
        
        //not ++level as it will impact the level value in second dfsHelper
        dfs(root.right, level + 1, res); // root.right first
        dfs(root.left, level +1 , res);
    }

    //solution-2: level order traverse - iterative
    public static List<Integer> rightSideView(TreeNode root) {
        if(root == null) return Collections.emptyList();
        
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while(!queue.isEmpty()){
            
            int size = queue.size();
            
            for(int i=0; i<size; i++){

                TreeNode node = queue.poll();

                if(i==size -1) res.add(node.val); //key point, record the right most node's value

                if(node.left != null)
                    queue.add(node.left);

                if(node.right != null)
                    queue.add(node.right);
            }
        }
        return res;
    }

}