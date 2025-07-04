package category.Tree.BinaryTree.simpleRecursive;

import category.model.TreeNode;
import java.util.*;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * input are all unique value
 * Created by brianzhang on 5/9/20.
 */
public class LowestCommonAncestorBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode p = new TreeNode(5), q = new TreeNode(6);
        System.out.println(lowestCommonAncestor(root, p, q).val);
        System.out.println(lowestCommonAncestorBFS(root, p, q).val);
    }

    // DFS solution - Once we reach the desired nodes p and q, we can backtrack and find the lowest common ancestor.
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || root.val == p.val || root.val == q.val)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null) return root;

        return left != null ? left : right;
    }

    // BFS solution
    public static TreeNode lowestCommonAncestorBFS(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parentMap = new HashMap(); // need override the equals and hasCode method for the Object TreeNode which is a key in map
        LinkedList<TreeNode> queue = new LinkedList();
        queue.add(root);

        while(!queue.isEmpty() && (!parentMap.containsKey(p) || !parentMap.containsKey(q))){
            TreeNode node = queue.pop();
            if(node.left != null){
                parentMap.put(node.left, node);
                queue.add(node.left);
            }
            if(node.right != null){
                parentMap.put(node.right, node);
                queue.add(node.right);
            }
        }

        Set<TreeNode> ancestor = new HashSet();
        while(p != null){
            ancestor.add(p);
            p = parentMap.get(p);
        }

        while(!ancestor.contains(q)){
            q = parentMap.get(q);
        }

        return q;
    }
}
