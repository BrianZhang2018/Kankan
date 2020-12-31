package category.BinaryTree.ConstructBinaryTree.BinarySearchTree.lowerUpperLimit;

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

    // Solution-2: BFS, converted from above DFS
    private Deque<TreeNode> stack = new LinkedList();
    private Deque<Integer> upperLimits = new LinkedList();
    private Deque<Integer> lowerLimits = new LinkedList();

    public void update(TreeNode root, Integer low, Integer high) {
        stack.add(root);
        lowerLimits.add(low);
        upperLimits.add(high);
    }

    public boolean isValidBSTBFS(TreeNode root) {
        Integer low = null, high = null, val;
        update(root, low, high);

        while (!stack.isEmpty()) {
            root = stack.poll();
            low = lowerLimits.poll();
            high = upperLimits.poll();

            if (root == null) continue;
            val = root.val;
            if (low != null && val <= low) {
                return false;
            }
            if (high != null && val >= high) {
                return false;
            }

            update(root.left, low, val);
            update(root.right, val, high);
        }
        return true;
    }
}
