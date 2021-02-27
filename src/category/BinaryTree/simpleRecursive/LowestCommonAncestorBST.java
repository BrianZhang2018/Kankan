package category.BinaryTree.simpleRecursive;

import category.model.TreeNode;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * Binary Search Tree
 * Created by brianzhang on 4/8/20.
 */

public class LowestCommonAncestorBST {

    public static void main(String[] args) {}

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        int pVal = p.val;
        int qVal = q.val;
        int parentVal = root.val;

        if (pVal > parentVal && qVal > parentVal) {// both p and q are greater than parent node
            return lowestCommonAncestor(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) { //both p and q are lesser than parent node
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // We have found the split point, i.e. the LCA node.
            return root;
        }
    }
}