package category.DivideAndConquer;

import category.model.TreeNode;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 *
 * Created by brianzhang on 8/5/18.
 */
public class MaximumDepthBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(2);
        root.right = new TreeNode(11);
        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(1);

        System.out.println(new MaximumDepthBinarySearchTree().maxDepth(root));
    }

    public int maxDepth(TreeNode root) {
        return helper(root);
    }

    private int helper(TreeNode root) {

        if (root == null)
            return 0;

        int left = helper(root.left);
        int right = helper(root.right);

        return Math.max(left, right) + 1;
    }
}
