package category.tree.bondary;

import java.util.*;
import java.util.TreeMap;
import java.util.ArrayList;
import category.model.TreeNode;

/**
 * https://www.lintcode.com/problem/binary-tree-vertical-order-traversal/
 * 
 * 对于一棵树，我们设其根节点的位置为0
 * For each node, its left child's degree is -1 and is right child's degree is +1. 
 * We can do a level order traversal and save the degree information.
 * 
 * https://www.programcreek.com/2014/04/leetcode-binary-tree-vertical-order-traversal-java/
 */
//level order traverse
public class BinaryTreeVerticalOrderTraversal{

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(19);
        pq.add(10);

        Integer[] temp = new Integer[pq.size()];
        pq.toArray(temp);
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        helper(root, map);
        return new ArrayList<>(map.values());
    }
     
    private void helper(TreeNode t, TreeMap<Integer, List<Integer>> map) {
        if (t == null) {
            return;
        }
        //queue for binaryTree node
        LinkedList<TreeNode> q1 = new LinkedList<>();
        //queue for binaryTree node's degree
        LinkedList<Integer> q2 = new LinkedList<>();
        q1.offer(t);
        q2.offer(0);
     
        while (!q1.isEmpty()) {
            TreeNode node = q1.poll();
            int level = q2.poll();
     
            //add to map
            List<Integer> list = map.get(level);
            if (list == null) {
                list = new ArrayList<>();
                map.put(level, list);
            }
            list.add(node.val);
     
            if (node.left != null) {
                q1.offer(node.left);
                q2.offer(level - 1);
            }
     
            if (node.right != null) {
                q1.offer(node.right);
                q2.offer(level + 1);
            }
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, List<Integer>> tm = new TreeMap();
        helper(root, tm);
        return new ArrayList<>(tm.values());
    }

    public void helper(TreeNode root, Map<Integer, List<Integer>> map){

        LinkedList<TreeNode> q1 = new LinkedList();
        LinkedList<Integer> q2 = new LinkedList();

        q1.add(root); q2.add(0);

        while(!q1.isEmpty()){
            TreeNode node = q1.poll();
            int level = q2.poll();

            List<Integer> pq = map.get(level);
            if(pq == null){
                pq = new ArrayList<>();
                map.put(level, pq);
            }
            pq.add(node.val);

            if(node.left != null){
                q1.add(node.left);
                q2.add(level-1);
            }

            if(node.right != null){
                q1.add(node.right);
                q2.add(level+1);
            }
        }
    }
}