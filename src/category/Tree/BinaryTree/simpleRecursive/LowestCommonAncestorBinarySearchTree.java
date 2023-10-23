package category.Tree.BinaryTree.simpleRecursive;

import category.model.TreeNode;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * Find the "Split" point
 * Created by brianzhang on 4/8/20.
 */

public class LowestCommonAncestorBinarySearchTree {
    public static void main(String[] args) {}

    // dfs
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int pVal = p.val, qVal = q.val, parentVal = root.val;

        if (pVal > parentVal && qVal > parentVal) { // both p and q are greater than parent node
            return lowestCommonAncestor(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) { // both p and q are lesser than parent node
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root; // found the "Split" point, i.e. the LCA node
        }
    }

    // bfs
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;
        while(root != null) {
            if(p.val > root.val && q.val > root.val) {
                root = root.right;
            }else if (p.val < root.val && q.val < root.val) {
                root = root.left;
            }else{
                return root;
            }
        }

        return null;
    }
}