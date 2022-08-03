package category.Tree.BinaryTree.BinarySearchTree.lowerUpperLimit;

import companies.amazon.model.TreeNode;
import java.util.*;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 * 这题可以有以下两种解法:
 * 1. valid range
 * 2. inorder traversal
 *
 * Created by brianzhang on 4/15/19.
 */
public class ValidateBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        ValidateBinarySearchTree test = new ValidateBinarySearchTree();
        test.isValidBST(root);
        test.isValidBSTBFS(root);
    }

    // Solution-1: DFS, lower/upperLimit solution
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        return validateTree(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean validateTree(TreeNode root, long lowerLimit, long upperLimit){
        if(root == null) return true;

        if(root.val <= lowerLimit || root.val >= upperLimit) return false;

        boolean lTree = validateTree(root.left, lowerLimit, root.val);
        boolean rTree = validateTree(root.right, root.val, upperLimit);
        return lTree && rTree;
    }

    // Solution-2: BFS - inOrder traversal
    public boolean isValidBSTBFS(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        Integer prev = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();

            if (prev!=null && root.val <= prev) return false;

            prev = root.val;
            root = root.right;
        }
        return true;
    }
}
